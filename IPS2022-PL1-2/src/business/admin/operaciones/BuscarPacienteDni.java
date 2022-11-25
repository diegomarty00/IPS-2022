package business.admin.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarPacienteDni implements Command<Optional<PacienteRecord>> {

    private String dni;

    public BuscarPacienteDni(String dni) {
	this.dni = dni;
    }

    @Override
    public Optional<PacienteRecord> execute() throws BusinessException {
	return PersistenceFactory.forAdmin().findByPacienteDni(dni);
    }

}
