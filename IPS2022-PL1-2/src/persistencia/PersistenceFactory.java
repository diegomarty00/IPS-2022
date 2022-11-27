package persistencia;

import persistencia.admin.AdminGateway;
import persistencia.admin.impl.AdminGatewayImpl;
import persistencia.cita.CitaGateway;
import persistencia.cita.MedicoCitaGateway;
import persistencia.cita.impl.CitaGatewayImpl;
import persistencia.cita.impl.MedicoCitaGatewayImpl;
import persistencia.enfermero.EnfermeroCitaGateway;
import persistencia.enfermero.EnfermeroGateway;
import persistencia.enfermero.impl.EnfermeroCitaGatewayImpl;
import persistencia.enfermero.impl.EnfermeroGatewayImpl;
import persistencia.medico.MedicoGateway;
import persistencia.medico.impl.MedicoGatewayImpl;
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
    
    public static MedicoGateway forMedico() {
    	return new MedicoGatewayImpl();
    }
    
    public static MedicoCitaGateway forMedicoCita() {
    	return new MedicoCitaGatewayImpl();
    }
    
    public static EnfermeroGateway forEnfermero() {
    	return new EnfermeroGatewayImpl();
    }
    
    public static EnfermeroCitaGateway forEnfermeroCita() {
    	return new EnfermeroCitaGatewayImpl();
    }
    
}
