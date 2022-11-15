package business.cita.operaciones;

import persistencia.PersistenceFactory;
import persistencia.cita.PrescripcionRecord;
import util.BusinessException;
import util.command.Command;

public class DeletePrescripcion implements Command<Void> {

	private PrescripcionRecord presc;
	
	public DeletePrescripcion(PrescripcionRecord presc) {
		super();
		this.presc = presc;
	}

	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().deletePrescripcion(presc);
		return null;
	}

}
