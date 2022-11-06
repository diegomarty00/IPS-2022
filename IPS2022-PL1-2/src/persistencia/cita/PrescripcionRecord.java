package persistencia.cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrescripcionRecord {
	
	private int idPrescricpion;
	private String titulo;
	private String tipo;
	private String cantidad;
	private String intervaloDosis;
	private String duracion;
	private String observaciones;
	private LocalTime horaAsignacion;
	private LocalDate fechaAsignacion;
	private String idCita;
	
	
	
	public PrescripcionRecord(int idPrescricpion, String titulo, String tipo, String cantidad, String intervaloDosis,
			String duracion, String observaciones, LocalTime horaAsignacion, LocalDate fechaAsignacion, String idCita) {
		super();
		this.idPrescricpion = idPrescricpion;
		this.titulo = titulo;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.intervaloDosis = intervaloDosis;
		this.duracion = duracion;
		this.observaciones = observaciones;
		this.horaAsignacion = horaAsignacion;
		this.fechaAsignacion = fechaAsignacion;
		this.idCita = idCita;
	}
	
	
	
	public PrescripcionRecord(int idPrescricpion, String titulo, String tipo, String observaciones,
			LocalTime horaAsignacion, LocalDate fechaAsignacion, String idCita) {
		super();
		this.idPrescricpion = idPrescricpion;
		this.titulo = titulo;
		this.tipo = tipo;
		this.cantidad = null;
		this.intervaloDosis = null;
		this.duracion = null;
		this.observaciones = observaciones;
		this.horaAsignacion = horaAsignacion;
		this.fechaAsignacion = fechaAsignacion;
		this.idCita = idCita;
	}



	public int getIdPrescricpion() {
		return idPrescricpion;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getTipo() {
		return tipo;
	}
	public String getCantidad() {
		return cantidad;
	}
	public String getIntervaloDosis() {
		return intervaloDosis;
	}
	public String getDuracion() {
		return duracion;
	}
	public String getObservaciones() {
		return observaciones;
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
		if (tipo.equalsIgnoreCase("MEDICAMENTO"))
			return titulo+" ("+tipo+") "+cantidad+" "+intervaloDosis+" durante "+
				duracion+(!observaciones.equals("")?". "+observaciones:"")+ ". "+horaAsignacion.toString()+" del dia "+fechaAsignacion.toString();
		else 
			return titulo+" ("+tipo+") "+(!observaciones.equals("")?". "+observaciones:"")+ ". "+horaAsignacion.toString()+" del dia "+fechaAsignacion.toString();
	}
}
