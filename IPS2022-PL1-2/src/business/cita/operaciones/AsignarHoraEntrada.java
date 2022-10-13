package business.cita.operaciones;

import java.time.LocalTime;

import persistencia.PersistenceFactory;
import persistencia.cita.CitaGateway;
import util.BusinessException;
import util.command.Command;

public class AsignarHoraEntrada implements Command<Void>{

	
	public String idCita;
	public LocalTime horaEntrada;
	
	public AsignarHoraEntrada(String idCita, LocalTime horaEntrada) {
		super();
		this.idCita=idCita;
		this.horaEntrada=horaEntrada;
	}
	
	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().asignarHoraEntrada(idCita, horaEntrada);
		return null;
	}
	
}
