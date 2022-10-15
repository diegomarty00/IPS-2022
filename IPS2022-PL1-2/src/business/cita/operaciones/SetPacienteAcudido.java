package business.cita.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class SetPacienteAcudido implements Command<Void>{

	private String idCita;
	
	public SetPacienteAcudido(String idCita) {
		this.idCita=idCita;
	}
	
	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().setPacienteAcudido(idCita);
		return null;
	}

}
