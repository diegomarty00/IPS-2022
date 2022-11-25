package business.admin.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class EliminarMedicoCabeceraDni implements Command<Void> {

    private String dniPaciente;

    public EliminarMedicoCabeceraDni(String dniPaciente) {
	this.dniPaciente = dniPaciente;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().eliminarMedicoCabeceraDni(dniPaciente);
	return null;
    }

}
