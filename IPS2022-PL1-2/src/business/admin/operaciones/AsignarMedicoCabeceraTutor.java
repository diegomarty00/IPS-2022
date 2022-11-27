package business.admin.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class AsignarMedicoCabeceraTutor implements Command<Void> {

    private String dniTutor;
    private int idMedico;
    private String name;
    private String surname;

    public AsignarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname, int idMedico) {
	this.dniTutor = dniTutor;
	this.name = name;
	this.surname = surname;
	this.idMedico = idMedico;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().asignarMedicoCabeceraTutor(dniTutor, name,
		surname, idMedico);
	return null;
    }

}
