package persistencia.cita;

import persistencia.medico.MedicoGateway;
import persistencia.medico.impl.MedicoGatewayImpl;

public class MedicoCitaRecord  {
	
	@Override
	public String toString() {
		MedicoGatewayImpl mg = new MedicoGatewayImpl();
		;
		return  mg.findById(String.valueOf(idMedico)).get().toString();
	}
	public int idMedico ;
	public String idCita;
}
