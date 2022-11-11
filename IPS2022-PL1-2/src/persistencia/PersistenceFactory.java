package persistencia;

import persistencia.admin.AdminGateway;
import persistencia.admin.impl.AdminGatewayImpl;
import persistencia.cita.CitaGateway;
import persistencia.cita.impl.CitaGatewayImpl;
import persistencia.paciente.PacienteGateway;
import persistencia.paciente.impl.PacienteGatewayImpl;

public class PersistenceFactory {

    public static CitaGateway forCita() {
	return new CitaGatewayImpl();
    }

    public static PacienteGateway forPaciente() {
	return new PacienteGatewayImpl();
    }

    public static AdminGateway forAdmin() {
	return new AdminGatewayImpl();
    }
}
