package persistencia.paciente;

import java.time.LocalDate;
import java.time.LocalTime;

public class VacunaRecord {

	private int idVacuna;
	private int idHistorial;
	private int idCita;
	private LocalDate fechaReal;
	private LocalDate fechaAproximada;
	private LocalTime hora;
	private String dosis;
	private boolean refuerzo;
	
	public VacunaRecord(int idVacuna, int idHistorial, int idCita, LocalDate fecha, LocalDate fechaAproximada,
			LocalTime hora, String dosis, boolean refuerzo) {
		super();
		this.idVacuna = idVacuna;
		this.idHistorial = idHistorial;
		this.idCita = idCita;
		this.fechaReal = fecha;
		this.fechaAproximada = fechaAproximada;
		this.hora = hora;
		this.dosis = dosis;
		this.refuerzo = refuerzo;
	}

	public int getIdVacuna() {
		return idVacuna;
	}

	public int getIdHistorial() {
		return idHistorial;
	}

	public int getIdCita() {
		return idCita;
	}

	public LocalDate getFechaReal() {
		return fechaReal;
	}

	public LocalDate getFechaAproximada() {
		return fechaAproximada;
	}

	public LocalTime getHora() {
		return hora;
	}

	public String getDosis() {
		return dosis;
	}

	public boolean isRefuerzo() {
		return refuerzo;
	}
	
	
}
