package business.admin.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarPacientes implements Command<List<PacienteRecord>> {

    @Override
    public List<PacienteRecord> execute() throws BusinessException {
	List<PacienteRecord> pacientes = PersistenceFactory.forAdmin()
		.findAllPacientes();
	return pacientes;
    }

}
