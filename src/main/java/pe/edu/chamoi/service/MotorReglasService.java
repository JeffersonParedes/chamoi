package pe.edu.chamoi.service;

import org.springframework.stereotype.Service;
import pe.edu.chamoi.model.ReglaAnalisis;
import pe.edu.chamoi.model.enums.EstadoRegla;
import pe.edu.chamoi.model.enums.TipoActividadAmplio;
import pe.edu.chamoi.repository.ReglaAnalisisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MotorReglasService {

    private final ReglaAnalisisRepository reglaRepo;

    public MotorReglasService(ReglaAnalisisRepository reglaRepo) {
        this.reglaRepo = reglaRepo;
    }

    /**
     * Evalúa las reglas activas para un tipo de actividad.
     * Retorna la lista de reglas que aplican ordenadas por prioridad.
     */
    public List<ReglaAnalisis> evaluarReglas(String tipoActividad, Map<String, Object> contexto) {
        List<TipoActividadAmplio> tipos = List.of(
            TipoActividadAmplio.valueOf(tipoActividad),
            TipoActividadAmplio.AMBAS
        );
        List<ReglaAnalisis> reglas = new ArrayList<>(reglaRepo.findByTipoActividadInAndEstado(tipos, EstadoRegla.ACTIVA));
        
        // Ordena por prioridad (mayor primero)
        reglas.sort((a, b) -> {
            Integer prioA = a.getPrioridad() != null ? a.getPrioridad() : 0;
            Integer prioB = b.getPrioridad() != null ? b.getPrioridad() : 0;
            return prioB.compareTo(prioA);
        });
        
        List<ReglaAnalisis> aplicables = new ArrayList<>();
        for (ReglaAnalisis regla : reglas) {
            if (evaluarCondicion(regla.getCondicion(), contexto)) {
                aplicables.add(regla);
            }
        }
        return aplicables;
    }

    /**
     * Evalúa una condición tipo "temperatura < 5" o "humedad > 80".
     */
    private boolean evaluarCondicion(String condicion, Map<String, Object> contexto) {
        if (condicion == null || condicion.isBlank() || contexto == null) {
            return false;
        }

        try {
            String[] partes = condicion.trim().split("\\s+");
            if (partes.length != 3) return false;
            
            String campo = partes[0];
            String op = partes[1];
            double valor = Double.parseDouble(partes[2]);
            
            Object valorContexto = contexto.get(campo);
            if (valorContexto == null) return false;
            
            double valorReal = Double.parseDouble(valorContexto.toString());
            
            return switch (op) {
                case "<"  -> valorReal < valor;
                case ">"  -> valorReal > valor;
                case "<=" -> valorReal <= valor;
                case ">=" -> valorReal >= valor;
                case "==" -> valorReal == valor;
                default   -> false;
            };
        } catch (Exception e) {
            return false;
        }
    }
}

