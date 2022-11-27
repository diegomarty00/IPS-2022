package persistencia.cita;

import java.time.LocalDate;
import java.time.LocalTime;

import persistencia.medico.MedicoRecord;

public class DiagnosticoRecord {

	private int idDiagnostico;
	private String titulo;
	private String seccion;
	private LocalDate fechaAsignacion;
	private LocalTime horaAsginacion;
	private MedicoRecord medicoAsociado;
	
	public DiagnosticoRecord(String titulo, String seccion) {
		super();
		this.titulo = titulo;
		this.seccion = seccion;
	}

	public int getIdDiagnostico() {
		return idDiagnostico;
	}

	public void setIdDiagnostico(int idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public LocalTime getHoraAsginacion() {
		return horaAsginacion;
	}

	public void setHoraAsginacion(LocalTime horaAsginacion) {
		this.horaAsginacion = horaAsginacion;
	}

	public MedicoRecord getMedicoAsociado() {
		return medicoAsociado;
	}

	public void setMedicoAsociado(MedicoRecord medicoAsociado) {
		this.medicoAsociado = medicoAsociado;
	}
	
	public String getSeccion() {
		return seccion;
	}

	@Override
	public String toString() {
		if (medicoAsociado==null)
			return titulo;
		return titulo+", "+fechaAsignacion.toString()+" "+horaAsginacion.toString()+" (Dr. "+medicoAsociado.toString()+")";
	}
}
