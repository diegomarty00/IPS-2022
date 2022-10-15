package business.paciente;

import java.util.Optional;

import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public interface PacienteService {

	Optional<PacienteRecord> getByDni(String dni) throws BusinessException;
}
