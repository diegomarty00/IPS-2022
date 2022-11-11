package persistencia.paciente;

import java.time.LocalDate;

public class HistorialRecord {

	private String idHistorial;
	private String titulo;
	private String descripcion;
	private String dniPaciente;
	private int idMedico;
	private LocalDate fecha;
	
	public String getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(String idHistorial) {
		this.idHistorial = idHistorial;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String toString() {
		return getTitulo()+": "+getDescripcion()+". Fecha: "+getFecha().toString();
	}
	
}
