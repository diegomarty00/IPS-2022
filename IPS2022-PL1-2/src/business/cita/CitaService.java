package business.cita;

import java.sql.Date;

import util.BusinessException;

public interface CitaService {
	
	void asignarHoraEntrada(String idCita, Date horaEntrada) throws BusinessException;

	void asignarHoraSalida(String idCita, Date horaSalida) throws BusinessException;
	
}
