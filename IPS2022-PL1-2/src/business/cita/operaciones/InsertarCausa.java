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
	private LocalDate fecha;
	private LocalTime hora;
	
	public InsertarCausa(String idCita, String causa, LocalDate fecha, LocalTime hora) {
		super();
		this.idCita = idCita;
		this.causa = causa;
		this.fecha = fecha;
		this.hora = hora;
	}

	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().insertarCausa(idCita, causa, Time.valueOf(hora), Date.valueOf(fecha));
		return null;
	}

}
