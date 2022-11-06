package business.cita.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import business.cita.CitaService;
import business.cita.operaciones.AsignarHoraEntrada;
import business.cita.operaciones.AsignarHoraSalida;
import business.cita.operaciones.GetCitasDelDia;
import business.cita.operaciones.GetCitasProximas;
import business.cita.operaciones.InsertarCausa;
import business.cita.operaciones.DeleteCausa;
import business.cita.operaciones.ModificarCita;
import business.cita.operaciones.SetPacienteAcudido;
import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import util.BusinessException;
import util.command.CommandExecutor;

public class CitaServiceImpl implements CitaService {

    @Override
    public void asignarHoraEntrada(String idCita, int horaEntrada,
	    int minEntrada) throws BusinessException {
	CommandExecutor c = new CommandExecutor();
	c.execute(new AsignarHoraEntrada(idCita,
		LocalTime.of(horaEntrada, minEntrada)));

    }

    @Override
    public void asignarHoraSalida(String idCita, int horaSalida, int minSalida)
	    throws BusinessException {
	CommandExecutor c = new CommandExecutor();
	c.execute(new AsignarHoraSalida(idCita,
		LocalTime.of(horaSalida, minSalida)));
    }

    @Override
    public void pacienteAcudido(String idCita) throws BusinessException {
	CommandExecutor c = new CommandExecutor();
	c.execute(new SetPacienteAcudido(idCita));
    }

    @Override
    public List<CitaRecord> getCitasDelDia(int year, int month, int day)
	    throws BusinessException {
	Date date = Date.valueOf(LocalDate.of(year, month, day));
	CommandExecutor c = new CommandExecutor();
	return c.execute(new GetCitasDelDia(date));
    }

    @Override
    public List<CitaRecord> getCitasProximas(int year, int month, int day)
	    throws BusinessException {
	Date date = Date.valueOf(LocalDate.of(year, month, day));
	CommandExecutor c = new CommandExecutor();
	return c.execute(new GetCitasProximas(date));
    }

    @Override
    public void modificarCita(String dniPaciente, String textCorreo,
	    int textTelefono) throws BusinessException {
	CommandExecutor c = new CommandExecutor();
	c.execute(new ModificarCita(dniPaciente, textCorreo, textTelefono));

    }

	@Override
	public void updateCausas(String idCita, ArrayList<String> causas, LocalDate fecha, LocalTime hora) throws BusinessException {
		CommandExecutor c;
		
		List<CausaRecord> causasRecord = PersistenceFactory.forCita().getCausas(idCita);
		
		for (CausaRecord causa : causasRecord) {
			if(!causas.contains(causa.getTitulo())) { //Si tenemos una causa que no ha sido seleccionada se borra
				c = new CommandExecutor();
				c.execute(new DeleteCausa(causa.getIdCausa()));
			} else { //Si tenemos causas que estan seleccionadas quiere decir que ya la habiamos anyadido antes. 
				//Por lo que la borramos de la lista de seleccionados
				causas.remove(causa.getTitulo());
			}
		}
		
		for (String causa: causas) {
			c = new CommandExecutor();
			c.execute(new InsertarCausa(idCita, causa, fecha, hora));
		}
		
		
	}

}
