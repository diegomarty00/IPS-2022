package business.paciente;

import java.util.List;
import java.util.Optional;

import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;

public interface PacienteService {

	Optional<PacienteRecord> getById(int idPaciente) throws BusinessException;

	HistorialRecord getHistorialPaciente(int idPaciente) throws BusinessException;
	
	void vacunar(VacunaRecord vacuna) throws BusinessException;

}
