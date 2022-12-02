package business.paciente.impl;

import java.util.List;
import java.util.Optional;

import business.paciente.PacienteService;
import business.paciente.operaciones.GetHistorialPaciente;
import business.paciente.operaciones.GetPacienteById;
import persistencia.PersistenceFactory;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;
import util.command.CommandExecutor;

public class PacienteServiceImpl implements PacienteService{

	@Override
	public Optional<PacienteRecord> getById(int idPaciente) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new GetPacienteById(idPaciente));
	}

	@Override
	public HistorialRecord getHistorialPaciente(int idPaciente) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new GetHistorialPaciente(idPaciente));
	}

	@Override
	public void vacunar(VacunaRecord vacuna) throws BusinessException {
		PersistenceFactory.forPaciente().vacunar(vacuna);
	}

}
