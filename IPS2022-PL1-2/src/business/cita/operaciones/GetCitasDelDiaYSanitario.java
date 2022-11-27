package business.cita.operaciones;

import java.sql.Date;
import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.cita.MedicoCitaRecord;
import persistencia.enfermero.EnfermeroRecord;
import persistencia.medico.MedicoRecord;
import util.BusinessException;
import util.command.Command;

public class GetCitasDelDiaYSanitario implements Command<List<CitaRecord>> {

	private Date dia;
	private MedicoRecord medico;
	private EnfermeroRecord enfermero;
	
	public GetCitasDelDiaYSanitario(Date dia, MedicoRecord medico, EnfermeroRecord enfermero) {
		super();
		this.dia = dia;
		this.medico = medico;
		this.enfermero = enfermero;
	}

	@Override
	public List<CitaRecord> execute() throws BusinessException {
		if (medico==null && enfermero==null)
			return PersistenceFactory.forCita().getCitasDelDia(dia);
		return PersistenceFactory.forCita().findBySanitarioId(medico, enfermero, dia);

	}

}
