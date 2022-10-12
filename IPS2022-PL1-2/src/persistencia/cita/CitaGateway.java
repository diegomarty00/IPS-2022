package persistencia.cita;

import java.util.Optional;

import persistencia.Gateway;

public interface CitaGateway extends Gateway<CitaRecord>{
	Optional<CitaRecord> findById(String id);
}
