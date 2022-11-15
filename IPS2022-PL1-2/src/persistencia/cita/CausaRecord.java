package persistencia.cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CausaRecord {
	private int idCausa;
	private String titulo;
	private LocalTime horaAsignacion;
	private LocalDate fechaAsignacion;
	private String idCita;
	
	
	public CausaRecord(int idCausa, String titulo, LocalTime horaAsignacion, LocalDate fechaAsignacion, String idCita) {
		super();
		this.idCausa = idCausa;
		this.titulo = titulo;
		this.horaAsignacion = horaAsignacion;
		this.fechaAsignacion = fechaAsignacion;
		this.idCita = idCita;
	}
	
	public int getIdCausa() {
		return idCausa;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalTime getHoraAsignacion() {
		return horaAsignacion;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public String getIdCita() {
		return idCita;
	}

	@Override
	public String toString() {
		return titulo+" ("+horaAsignacion.toString()+" del dia "+fechaAsignacion.toString()+")";
	}
}
