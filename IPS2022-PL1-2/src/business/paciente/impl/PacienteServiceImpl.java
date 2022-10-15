package business.paciente.impl;

import java.util.Optional;

import business.paciente.PacienteService;
import business.paciente.operaciones.GetPacienteByDni;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;
import util.command.CommandExecutor;

public class PacienteServiceImpl implements PacienteService{

	@Override
	public Optional<PacienteRecord> getByDni(String dni) throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		return c.execute(new GetPacienteByDni(dni));
	}

}
