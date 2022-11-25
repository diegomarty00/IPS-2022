package business.admin.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class AsignarMedicoCabeceraTutor implements Command<Void> {

    private String dniPaciente;
    private int idMedico;

    public AsignarMedicoCabeceraTutor(String dniPaciente, int idMedico) {
	this.dniPaciente = dniPaciente;
	this.idMedico = idMedico;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().asignarMedicoCabeceraDni(dniPaciente,
		idMedico);
	return null;
    }

}
