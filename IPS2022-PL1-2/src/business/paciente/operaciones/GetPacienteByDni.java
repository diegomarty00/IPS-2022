package business.paciente.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;
import util.command.Command;

public class GetPacienteByDni implements Command<Optional<PacienteRecord>> {

    private String dni;

    public GetPacienteByDni(int id) {
	this.dni = String.valueOf(id);
    }

    @Override
    public Optional<PacienteRecord> execute() throws BusinessException {
	return PersistenceFactory.forPaciente().findById(dni);
    }

}
