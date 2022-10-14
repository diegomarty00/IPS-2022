package business.cita;

import util.BusinessException;

public interface CitaService {
	
	void asignarHoraEntrada(String idCita, int horaEntrada, int minEntrada) throws BusinessException;

	void asignarHoraSalida(String idCita, int horaSalida, int minSalida) throws BusinessException;
	
}
