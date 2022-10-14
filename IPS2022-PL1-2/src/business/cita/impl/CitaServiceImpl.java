  package business.cita.impl;

import java.time.LocalTime;

import business.cita.CitaService;
import business.cita.operaciones.AsignarHoraEntrada;
import business.cita.operaciones.AsignarHoraSalida;
import util.BusinessException;
import util.command.CommandExecutor;


public class CitaServiceImpl implements CitaService{

	@Override
	public void asignarHoraEntrada(String idCita, int horaEntrada, int minEntrada) throws BusinessException{
		CommandExecutor c = new CommandExecutor();
		c.execute(new AsignarHoraEntrada(idCita, LocalTime.of(horaEntrada, minEntrada)));
		
	}

	@Override
	public void asignarHoraSalida(String idCita, int horaSalida, int minSalida)  throws BusinessException {
		CommandExecutor c = new CommandExecutor();
		c.execute(new AsignarHoraSalida(idCita, LocalTime.of(horaSalida, minSalida)));
	}

}
