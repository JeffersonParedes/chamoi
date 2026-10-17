package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.chamoi.dto.request.AnalisisRequest;
import pe.edu.chamoi.model.*;
import pe.edu.chamoi.model.enums.*;
import pe.edu.chamoi.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AnalisisService {

    private final AnalisisRepository analisisRepo;
    private final ResultadoAnalisisRepository resultadoRepo;
    private final RecomendacionRepository recomendacionRepo;
    private final SituacionAgricolaRepository situacionAgrRepo;
    private final SituacionPesqueraRepository situacionPesRepo;
    private final CultivoRepository cultivoRepo;
    private final EspecieRepository especieRepo;
    private final UsuarioRepository usuarioRepo;
    private final MotorReglasService motorReglas;
    private final AuditoriaService auditoriaService;

    public AnalisisService(AnalisisRepository analisisRepo,
                           ResultadoAnalisisRepository resultadoRepo,
                           RecomendacionRepository recomendacionRepo,
                           SituacionAgricolaRepository situacionAgrRepo,
                           SituacionPesqueraRepository situacionPesRepo,
                           CultivoRepository cultivoRepo,
                           EspecieRepository especieRepo,
                           UsuarioRepository usuarioRepo,
                           MotorReglasService motorReglas,
                           AuditoriaService auditoriaService) {
        this.analisisRepo = analisisRepo;
        this.resultadoRepo = resultadoRepo;
        this.recomendacionRepo = recomendacionRepo;
        this.situacionAgrRepo = situacionAgrRepo;
        this.situacionPesRepo = situacionPesRepo;
        this.cultivoRepo = cultivoRepo;
        this.especieRepo = especieRepo;
        this.usuarioRepo = usuarioRepo;
        this.motorReglas = motorReglas;
        this.auditoriaService = auditoriaService;
    }

    // ============ AGRICULTOR ============

    public Analisis solicitarAnalisisAgricola(Long usuarioId, AnalisisRequest req, String ip) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
        
        // 1. Guardar situación
        SituacionAgricola sit = new SituacionAgricola();
        sit.setUsuario(u);
        if (req.getCultivoId() != null) {
            sit.setCultivo(cultivoRepo.findById(req.getCultivoId()).orElse(null));
        }
        sit.setFechaRegistro(LocalDateTime.now());
        sit.setEtapaCultivo(req.getEtapaCultivo());
        sit.setAreaCultivada(req.getAreaCultivada());
        sit.setUnidadArea(req.getUnidadArea() != null && !req.getUnidadArea().isBlank() ? req.getUnidadArea() : "ha");
        sit.setTipoSuelo(req.getTipoSuelo());
        sit.setDisponibilidadAgua(req.getDisponibilidadAgua());
        sit.setProblemaPrincipal(req.getProblemaPrincipal());
        sit.setObservaciones(req.getObservaciones());
        sit = situacionAgrRepo.save(sit);
        
        // 2. Crear análisis
        Analisis a = new Analisis();
        a.setUsuario(u);
        a.setTipoActividad(TipoActividad.AGRICOLA);
        a.setSituacionAgricola(sit);
        a.setEstado(EstadoAnalisis.SOLICITADO);
        a.setResumenSolicitud(req.getPregunta());
        a.setFechaSolicitud(LocalDateTime.now());
        a = analisisRepo.save(a);
        
        // 3. Procesar síncronamente
        procesarAnalisis(a.getId());
        
        // 4. Auditoría
        auditoriaService.registrar(usuarioId, "SOLICITAR_ANALISIS", "Analisis", 
            a.getId(), "Análisis agrícola solicitado: " + req.getPregunta(), ip);
        
        return a;
    }

    // ============ PESCADOR ============

    public Analisis solicitarAnalisisPesquero(Long usuarioId, AnalisisRequest req, String ip) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
        
        SituacionPesquera sit = new SituacionPesquera();
        sit.setUsuario(u);
        if (req.getEspecieId() != null) {
            sit.setEspecie(especieRepo.findById(req.getEspecieId()).orElse(null));
        }
        sit.setFechaRegistro(LocalDateTime.now());
        sit.setZonaActividad(req.getZonaActividad());
        sit.setTipoPesca(req.getTipoPesca());
        sit.setCantidadEstimada(req.getCantidadEstimada());
        sit.setUnidadCantidad(req.getUnidadCantidad() != null && !req.getUnidadCantidad().isBlank() ? req.getUnidadCantidad() : "kg");
        sit.setCondicionesObservadas(req.getCondicionesObservadas());
        sit.setObjetivo(req.getObjetivo());
        sit.setObservaciones(req.getObservaciones());
        sit = situacionPesRepo.save(sit);
        
        Analisis a = new Analisis();
        a.setUsuario(u);
        a.setTipoActividad(TipoActividad.PESQUERA);
        a.setSituacionPesquera(sit);
        a.setEstado(EstadoAnalisis.SOLICITADO);
        a.setResumenSolicitud(req.getPregunta());
        a.setFechaSolicitud(LocalDateTime.now());
        a = analisisRepo.save(a);
        
        procesarAnalisis(a.getId());
        
        auditoriaService.registrar(usuarioId, "SOLICITAR_ANALISIS", "Analisis", 
            a.getId(), "Análisis pesquero solicitado: " + req.getPregunta(), ip);
        
        return a;
    }

    // ============ PROCESAMIENTO ============

    public void procesarAnalisis(Long analisisId) {
        Analisis a = analisisRepo.findById(analisisId)
                .orElseThrow(() -> new RuntimeException("Análisis no encontrado con ID: " + analisisId));
        a.setEstado(EstadoAnalisis.PROCESANDO);
        analisisRepo.save(a);
        
        try {
            // 1. Construir contexto (con datos mock, ya que aún no cargamos datos_fuente)
            Map<String, Object> contexto = construirContexto(a);
            
            // 2. Evaluar reglas
            List<ReglaAnalisis> reglasAplicadas = motorReglas.evaluarReglas(
                a.getTipoActividad().name(), contexto);
            
            // 3. Determinar nivel de riesgo
            NivelRiesgo riesgo = calcularRiesgo(reglasAplicadas, a.getTipoActividad());
            
            // 4. Generar resumen
            String resumen = generarResumen(a, reglasAplicadas, riesgo);
            String interpretacion = generarInterpretacion(a, contexto, reglasAplicadas);
            
            // 5. Guardar resultado
            ResultadoAnalisis res = new ResultadoAnalisis();
            res.setAnalisis(a);
            res.setResumen(resumen);
            res.setInterpretacionIa(interpretacion);
            res.setNivelRiesgo(riesgo);
            res.setNivelConfianza(new BigDecimal("75.00"));
            res.setLimitaciones("Análisis basado en reglas agroclimáticas y oceanográficas predefinidas. "
                + "En fases posteriores se integrará IA con streaming de datos de SENAMHI e IMARPE.");
            res.setFactoresJson(generarFactoresJson(reglasAplicadas, contexto));
            res.setFuentesUsadasJson("[]");
            res.setFechaGeneracion(LocalDateTime.now());
            res = resultadoRepo.save(res);
            
            // 6. Generar recomendaciones
            generarRecomendaciones(res, reglasAplicadas, a);
            
            // 7. Marcar completado
            a.setEstado(EstadoAnalisis.COMPLETADO);
            a.setFechaFinalizacion(LocalDateTime.now());
            analisisRepo.save(a);
            
        } catch (Exception e) {
            a.setEstado(EstadoAnalisis.ERROR);
            a.setMensajeError(e.getMessage());
            analisisRepo.save(a);
            throw new RuntimeException("Error al procesar análisis: " + e.getMessage(), e);
        }
    }

    /**
     * Construye el contexto con datos MOCK agroclimáticos / oceanográficos.
     */
    private Map<String, Object> construirContexto(Analisis a) {
        Map<String, Object> ctx = new HashMap<>();
        
        if (a.getTipoActividad() == TipoActividad.AGRICOLA) {
            ctx.put("temperatura", 4.5);       // activa regla 'temperatura < 5'
            ctx.put("humedad", 82.0);          // activa regla 'humedad > 80'
            ctx.put("precipitacion", 14.0);     // activa regla 'precipitacion < 20'
            ctx.put("altitud", 2500.0);
            ctx.put("ph_suelo", 6.5);
        } else {
            ctx.put("temperatura_mar", 17.5);  // activa regla 'temperatura_mar > 15'
            ctx.put("altura_ola", 3.2);        // activa regla 'altura_ola > 3'
            ctx.put("viento", 32.0);           // activa regla 'viento > 30'
            ctx.put("salinidad", 34.5);
        }
        return ctx;
    }

    private NivelRiesgo calcularRiesgo(List<ReglaAnalisis> reglas, TipoActividad tipo) {
        if (reglas.isEmpty()) return NivelRiesgo.BAJO;
        
        long criticas = reglas.stream().filter(r -> r.getPrioridad() != null && r.getPrioridad() >= 3).count();
        long altas = reglas.stream().filter(r -> r.getPrioridad() != null && r.getPrioridad() == 2).count();
        
        if (criticas >= 1) return NivelRiesgo.ALTO;
        if (altas >= 2) return NivelRiesgo.MEDIO;
        return NivelRiesgo.BAJO;
    }

    private String generarResumen(Analisis a, List<ReglaAnalisis> reglas, NivelRiesgo riesgo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Análisis de tipo ").append(a.getTipoActividad()).append(": ");
        
        if (reglas.isEmpty()) {
            sb.append("No se detectaron riesgos significativos con los datos actuales. ");
            sb.append("Las condiciones parecen favorables para continuar las labores.");
        } else {
            sb.append("Se detectaron ").append(reglas.size()).append(" factores relevantes para el sector. ");
            sb.append("Nivel de riesgo estimado: ").append(riesgo).append(". ");
        }
        return sb.toString();
    }

    private String generarInterpretacion(Analisis a, Map<String, Object> ctx, List<ReglaAnalisis> reglas) {
        StringBuilder sb = new StringBuilder();
        sb.append("Se evaluaron las variables operativas y ambientales con las reglas del sistema CHAMOI. ");
        
        if (!reglas.isEmpty()) {
            sb.append("Las alertas y reglas activadas indican: ");
            for (ReglaAnalisis r : reglas) {
                sb.append("\n• ").append(r.getNombre()).append(": ")
                  .append(r.getResultadoEsperado() != null ? r.getResultadoEsperado() : r.getDescripcion());
            }
        } else {
            sb.append("Ninguna regla de riesgo crítico se activó con las condiciones observadas.");
        }
        return sb.toString();
    }

    private String generarFactoresJson(List<ReglaAnalisis> reglas, Map<String, Object> ctx) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < reglas.size(); i++) {
            ReglaAnalisis r = reglas.get(i);
            if (i > 0) json.append(",");
            json.append("{")
                .append("\"nombre\":\"").append(escape(r.getNombre())).append("\",")
                .append("\"efecto\":\"").append(escape(r.getResultadoEsperado())).append("\",")
                .append("\"prioridad\":").append(r.getPrioridad() != null ? r.getPrioridad() : 1)
                .append("}");
        }
        json.append("]");
        return json.toString();
    }

    private void generarRecomendaciones(ResultadoAnalisis res, List<ReglaAnalisis> reglas, Analisis a) {
        if (reglas.isEmpty()) {
            Recomendacion rec = new Recomendacion();
            rec.setResultado(res);
            rec.setTitulo("Continuar con las condiciones actuales");
            rec.setDescripcion("No se detectaron riesgos agroclimáticos u oceanográficos adversos. Se recomienda mantener las prácticas habituales y monitorear periódicamente.");
            rec.setMotivo("Ninguna regla de riesgo se activó");
            rec.setPrioridad(NivelPrioridad.BAJA);
            rec.setOrigen(OrigenRecomendacion.REGLA);
            rec.setFechaGeneracion(LocalDateTime.now());
            recomendacionRepo.save(rec);
            return;
        }
        
        for (ReglaAnalisis r : reglas) {
            Recomendacion rec = new Recomendacion();
            rec.setResultado(res);
            rec.setTitulo(r.getNombre());
            rec.setDescripcion(r.getResultadoEsperado() != null 
                ? r.getResultadoEsperado() 
                : "Revisar la condición operativa: " + r.getCondicion());
            rec.setMotivo(r.getDescripcion());
            
            int p = r.getPrioridad() != null ? r.getPrioridad() : 1;
            if (p >= 3) rec.setPrioridad(NivelPrioridad.ALTA);
            else if (p == 2) rec.setPrioridad(NivelPrioridad.MEDIA);
            else rec.setPrioridad(NivelPrioridad.BAJA);
            
            rec.setOrigen(OrigenRecomendacion.REGLA);
            rec.setFechaGeneracion(LocalDateTime.now());
            recomendacionRepo.save(rec);
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"").replace("\n", " ").replace("\r", "");
    }

    // ============ CONSULTAS ============

    @Transactional(readOnly = true)
    public Analisis buscarPorId(Long id) {
        return analisisRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Análisis no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Analisis> listarPorUsuario(Long usuarioId) {
        return analisisRepo.findByUsuarioIdOrderByFechaSolicitudDesc(usuarioId);
    }

    @Transactional(readOnly = true)
    public List<Analisis> listarUltimos3PorUsuario(Long usuarioId) {
        return analisisRepo.findTop3ByUsuarioIdOrderByFechaSolicitudDesc(usuarioId);
    }

    @Transactional(readOnly = true)
    public ResultadoAnalisis buscarResultadoPorAnalisis(Long analisisId) {
        return resultadoRepo.findByAnalisisId(analisisId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Recomendacion> listarRecomendaciones(Long resultadoId) {
        return recomendacionRepo.findByResultadoId(resultadoId);
    }
}

