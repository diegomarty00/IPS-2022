package business.cita.operaciones;

import java.time.LocalTime;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class AsignarHoraEntrada implements Command<Void>{
	
	private String idCita;
	private LocalTime horaEntrada;
	
	public AsignarHoraEntrada(String idCita, LocalTime horaEntrada) {
		this.idCita=idCita;
		this.horaEntrada=horaEntrada;
	}
	
	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().asignarHoraEntrada(idCita, horaEntrada);
		return null;
	}
	
}
