package persistencia.cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import business.BusinessFactory;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public class CitaRecord {
	
	public String idCita;
	public String dniPaciente;
	public boolean urgente;
	public LocalTime horaEntradaEstimada;
	public LocalTime horaSalidaEstimada;
	public String pacienteAcudido;
	public LocalTime horaEntradaReal;
	public LocalTime horaSalidaReal;
	public LocalDate fecha;
	public String correoPaciente; 
	public String telefonoPaciente; 
	public String lugar;
	public String otros;
	public boolean prioritario;
	public PacienteRecord getPacienteAsociado() {
		try {
			return BusinessFactory.forPacienteService().getByDni(dniPaciente).get();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		PacienteRecord pacienteAsociado = getPacienteAsociado();
		return pacienteAsociado.getNombre()+" "+pacienteAsociado.getApellidos()+" "+horaEntradaEstimada.toString()+" - "+horaSalidaEstimada.toString();
	}
}
