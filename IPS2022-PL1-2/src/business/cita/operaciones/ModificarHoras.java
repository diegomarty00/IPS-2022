package business.cita.operaciones;

import java.time.LocalTime;

import persistencia.cita.CitaRecord;
import persistencia.cita.impl.CitaGatewayImpl;
import util.mail.EnviarMail;

public class ModificarHoras {
	
	public static void ModificarhoraCita(CitaRecord cita, String hI, String hF) {
		LocalTime inicio = LocalTime.parse(hI);
		LocalTime fin = LocalTime.parse(hF);
		EnviarMail.enviaMail(cita.correoPaciente,"Su cita del dia "+ cita.fecha.toString() + " ha sido modifica , ahora el inicio de esta es a las " + hI );
		CitaGatewayImpl cg = new CitaGatewayImpl();
		cg.modificarHorario(cita.idCita, inicio, fin);
		
		
	}

}
