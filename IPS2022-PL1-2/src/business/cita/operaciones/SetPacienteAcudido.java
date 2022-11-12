package business.cita.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class SetPacienteAcudido implements Command<Void>{

	private String idCita;
	private String estadoAsistenicia;
	
	public SetPacienteAcudido(String idCita, String estadoAsistenicia) {
		super();
		this.idCita = idCita;
		this.estadoAsistenicia = estadoAsistenicia;
	}

	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().setPacienteAcudido(idCita, estadoAsistenicia);
		return null;
	}

}
