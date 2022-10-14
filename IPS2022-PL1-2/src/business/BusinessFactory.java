package business;

import business.cita.CitaService;
import business.cita.impl.CitaServiceImpl;

public class BusinessFactory {
	
	public static CitaService forCitaService() {
		return new CitaServiceImpl();
	}
}
