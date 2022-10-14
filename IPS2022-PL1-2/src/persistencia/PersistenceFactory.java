package persistencia;

import persistencia.cita.CitaGateway;
import persistencia.cita.impl.CitaGatewayImpl;

public class PersistenceFactory {
	
	public static CitaGateway forCita() {
		return new CitaGatewayImpl();
	}
}
