package business.cita.operaciones;

import persistencia.cita.impl.CitaGatewayImpl;
import persistencia.cita.impl.MedicoCitaGatewayImpl;
import persistencia.enfermero.impl.EnfermeroCitaGatewayImpl;
import persistencia.especialidad.impl.EspecialidadCitaGatewayImpl;

public class EliminarCita {

	static public  void removeCita(String id) {
		MedicoCitaGatewayImpl md = new MedicoCitaGatewayImpl();
		md.removeMedicocitas(id);
		EnfermeroCitaGatewayImpl ec= new EnfermeroCitaGatewayImpl();
		ec.removeEnfermeroCita(id);
		EspecialidadCitaGatewayImpl epc = new EspecialidadCitaGatewayImpl();
		epc.removeEspecialidadC(id);
		CitaGatewayImpl c = new CitaGatewayImpl();
		c.removeCita(id);
	}
	
}
