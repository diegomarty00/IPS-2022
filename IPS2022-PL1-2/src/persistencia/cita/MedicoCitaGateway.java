package persistencia.cita;

import java.util.List;

import persistencia.Gateway;

public interface MedicoCitaGateway extends Gateway<MedicoCitaRecord> {
	void removeMedicocitas(String idCita);
}
