package business.admin.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarPacienteTutor implements Command<Optional<PacienteRecord>> {

    private String dniTutor;
    private String name;
    private String surname;

    public BuscarPacienteTutor(String dniTutor, String name, String surname) {
	this.dniTutor = dniTutor;
	this.name = name;
	this.surname = surname;
    }

    @Override
    public Optional<PacienteRecord> execute() throws BusinessException {
	return PersistenceFactory.forAdmin().findByPacienteTutor(dniTutor, name,
		surname);
    }

}
