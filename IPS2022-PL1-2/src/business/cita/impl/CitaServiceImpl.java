package business.cita.impl;

import java.sql.Date;
import java.time.LocalTime;

import business.cita.CitaService;
import business.cita.operaciones.AsignarHoraEntrada;
import util.BusinessException;
import util.command.CommandExecutor;


public class CitaServiceImpl implements CitaService{

	@Override
	public void asignarHoraEntrada(String idCita, LocalTime horaEntrada) throws BusinessException{
		CommandExecutor c = new CommandExecutor();
		c.execute(new AsignarHoraEntrada(idCita, horaEntrada));
		
	}

	@Override
	public void asignarHoraSalida(String idCita, LocalTime horaSalida)  throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
