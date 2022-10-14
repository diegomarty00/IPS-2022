package business.cita;

import java.time.LocalTime;

import util.BusinessException;

public interface CitaService {
	
	void asignarHoraEntrada(String idCita, LocalTime horaEntrada) throws BusinessException;

	void asignarHoraSalida(String idCita, LocalTime horaSalida) throws BusinessException;
	
}
