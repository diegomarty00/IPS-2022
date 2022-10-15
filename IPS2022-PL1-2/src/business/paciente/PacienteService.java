package business.paciente;

import java.util.List;
import java.util.Optional;

import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public interface PacienteService {

	Optional<PacienteRecord> getByDni(String dni) throws BusinessException;

	List<HistorialRecord> getHistorialPaciente(String dniPaciente) throws BusinessException;
}
