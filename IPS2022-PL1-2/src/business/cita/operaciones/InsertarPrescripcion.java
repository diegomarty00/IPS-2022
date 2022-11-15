package business.cita.operaciones;

import persistencia.PersistenceFactory;
import persistencia.cita.PrescripcionRecord;
import util.BusinessException;
import util.command.Command;

public class InsertarPrescripcion implements Command<Void> {
	
	private PrescripcionRecord presc;
	
	public InsertarPrescripcion(PrescripcionRecord presc) {
		super();
		this.presc = presc;
	}

	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().insertarPrescripcion(presc);
		return null;
	}

}
