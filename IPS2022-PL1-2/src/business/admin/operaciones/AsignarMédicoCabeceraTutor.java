package business.admin.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class AsignarMédicoCabeceraTutor implements Command<Void> {

    private String dniTutor;
    private int idMedico;

    public AsignarMédicoCabeceraTutor(String dniTutor, int idMedico) {
	this.dniTutor = dniTutor;
	this.idMedico = idMedico;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().asignarMedicoCabeceraDni(dniTutor,
		idMedico);
	return null;
    }

}
