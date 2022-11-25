package persistencia.enfermero;

import persistencia.Gateway;

public interface EnfermeroCitaGateway extends Gateway<EnfermeroCitaRecord> {
	public void removeEnfermeroCita(String idCita);

}
