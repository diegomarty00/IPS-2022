package business.cita.operaciones;

import java.sql.Date;
import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import util.BusinessException;
import util.command.Command;

public class GetCitasDelDia implements Command<List<CitaRecord>>{

	private Date dia;
	
	public GetCitasDelDia(Date dia) {
		this.dia=dia;
	}
	
	@Override
	public List<CitaRecord> execute() throws BusinessException {
		List<CitaRecord> citas = PersistenceFactory.forCita().getCitasDelDia(dia);
		return citas;
	}

}
