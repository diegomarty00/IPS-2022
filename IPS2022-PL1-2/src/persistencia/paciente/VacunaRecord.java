package persistencia.paciente;

import java.time.LocalDate;
import java.time.LocalTime;

public class VacunaRecord {

	private int idVacuna;
	private int idHistorial;
	private String idCita;
	private LocalDate fechaReal;
	private LocalDate fechaAproximada;
	private LocalTime hora;
	private String dosis;
	private boolean refuerzo;
	
	public VacunaRecord(int idVacuna, int idHistorial, String idCita, LocalDate fechaReal, LocalDate fechaAproximada,
			LocalTime hora, String dosis, boolean refuerzo) {
		super();
		this.idVacuna = idVacuna;
		this.idHistorial = idHistorial;
		this.idCita = idCita;
		this.fechaReal = fechaReal;
		this.fechaAproximada = fechaAproximada;
		this.hora = hora;
		this.dosis = dosis;
		this.refuerzo = refuerzo;
	}
	
	@Override
	public String toString() {
		if (hora!=null)
			return dosis+(refuerzo ? "(refuerzo)" : "")+" "+fechaReal.toString()+" a las "+hora.toString();
		else 
			return dosis+(refuerzo ? "(refuerzo)" : "")+" "+fechaAproximada.toString();
	}



	public void setIdVacuna(int idVacuna) {
		this.idVacuna = idVacuna;
	}

	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	public void setIdCita(String idCita) {
		this.idCita = idCita;
	}

	public void setFechaReal(LocalDate fechaReal) {
		this.fechaReal = fechaReal;
	}

	public void setFechaAproximada(LocalDate fechaAproximada) {
		this.fechaAproximada = fechaAproximada;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public void setRefuerzo(boolean refuerzo) {
		this.refuerzo = refuerzo;
	}


	public int getIdVacuna() {
		return idVacuna;
	}

	public int getIdHistorial() {
		return idHistorial;
	}

	public String getIdCita() {
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
