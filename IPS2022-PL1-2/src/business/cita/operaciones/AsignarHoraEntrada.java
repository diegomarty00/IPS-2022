package business.cita.operaciones;

import java.sql.Date;

import util.BusinessException;
import util.command.Command;

public class AsignarHoraEntrada implements Command<Void>{

	
	public String idCita;
	public Date horaEntrada;
	
	public AsignarHoraEntrada(String idCita, Date horaEntrada) {
		this.idCita=idCita;
		this.horaEntrada=horaEntrada;
	}
	
	@Override
	public Void execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
