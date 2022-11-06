package business.cita.operaciones;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class InsertarCausa implements Command<Void>{

	private String idCita;
	private String causa;
	
	public InsertarCausa(String idCita, String causa) {
		this.idCita=idCita;
		this.causa=causa;
	}
	
	@Override
	public Void execute() throws BusinessException {
		Time hora = Time.valueOf(LocalTime.now());
		Date fecha = Date.valueOf(LocalDate.now());
		PersistenceFactory.forCita().insertarCausa(idCita, causa, hora, fecha);
		return null;
	}

}
