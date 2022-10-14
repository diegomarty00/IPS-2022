package business.cita.operaciones;

import java.time.LocalTime;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class AsignarHoraSalida implements Command<Void>{

	
	public String idCita;
	public LocalTime horaSalida;
	
	public AsignarHoraSalida(String idCita, LocalTime horaSalida) {
		super();
		this.idCita=idCita;
		this.horaSalida=horaSalida;
	}
	
	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().asignarHoraSalida(idCita, horaSalida);
		return null;
	}
}
