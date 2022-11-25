package persistencia.enfermero;

import java.util.Optional;

import persistencia.enfermero.impl.EnfermeroGatewayImpl;

public class EnfermeroCitaRecord {

	public int idEnfermero;
	public String idCita;
	public int nEnfermeros;
	@Override
	public String toString() {
		EnfermeroGatewayImpl enf = new EnfermeroGatewayImpl();
		EnfermeroRecord e =  enf.findById(String.valueOf(idEnfermero)).get();
		if(idEnfermero == 1 ) {
			return e.nombre + "  " + nEnfermeros;
		}
		return e.nombre ;
	}
}
