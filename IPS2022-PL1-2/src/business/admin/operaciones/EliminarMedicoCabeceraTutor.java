package business.admin.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class EliminarMedicoCabeceraTutor implements Command<Void> {

    private String dniTutor;
    private String name;
    private String surname;

    public EliminarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname) {
	this.dniTutor = dniTutor;
	this.name = name;
	this.surname = surname;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().eliminarMedicoCabeceraTutor(dniTutor,
		name, surname);
	return null;
    }

}
