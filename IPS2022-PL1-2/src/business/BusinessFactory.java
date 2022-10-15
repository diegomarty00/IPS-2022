package business;

import business.cita.CitaService;
import business.cita.impl.CitaServiceImpl;
import business.paciente.PacienteService;
import business.paciente.impl.PacienteServiceImpl;

public class BusinessFactory {
	
	public static CitaService forCitaService() {
		return new CitaServiceImpl();
	}

	public static PacienteService forPacienteService() {
		return new PacienteServiceImpl();
	}
}
