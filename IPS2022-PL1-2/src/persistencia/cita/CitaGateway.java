package persistencia.cita;

import java.time.LocalTime;
import java.util.Optional;

import persistencia.Gateway;

public interface CitaGateway extends Gateway<CitaRecord>{
	
	void asignarHoraEntrada(String idCita, LocalTime horaEntrada);
	
	void asignarHoraSalida(String idCita, LocalTime horaSalida);
}
