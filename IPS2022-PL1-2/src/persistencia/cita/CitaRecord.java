package persistencia.cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaRecord {
	
	public String idCita;
	public String dniPaciente;
	public boolean urgente;
	public LocalTime horaEntradaEstimada;
	public LocalTime horaSalidaEstimada;
	public boolean pacienteAcudido;
	public LocalTime horaEntradaReal;
	public LocalTime horaSalidaReal;
	public LocalDate fecha;
	public String correoPaciente; 
	public String telefonoPaciente; 
	
	public String toString() {
		return horaEntradaEstimada.toString();
	}
}
