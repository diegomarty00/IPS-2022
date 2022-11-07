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
import business.cita.operaciones.InsertarPrescripcion;
import business.cita.operaciones.DeleteCausa;
import business.cita.operaciones.DeletePrescripcion;
import business.cita.operaciones.ModificarCita;
import business.cita.operaciones.SetPacienteAcudido;
import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
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
	
	@Override
	public void updatePrescripciones(List<PrescripcionRecord> prescripciones, String idCita) throws BusinessException {
		CommandExecutor c;
		List<PrescripcionRecord> currentPrescs = PersistenceFactory.forCita().getPrescripciones(idCita);
		//Primero iteramos sobre las prescs que tenemos seleccionadas ahora
		for (PrescripcionRecord presc : prescripciones) {
			//Si no esta en las que ya teniamos, se anyade
			if (!currentPrescs.contains(presc)) {
				c = new CommandExecutor();
				c.execute(new InsertarPrescripcion(presc));
			}
		}
		//Ahora iteramos sobre las que teniamos de antes
		for (PrescripcionRecord presc : currentPrescs) {
			//Si no esta en las 1ue tenemos ahora seleccionadas se borra
			if (!prescripciones.contains(presc)) {
				//TODO DELETE
				c = new CommandExecutor();
				c.execute(new DeletePrescripcion(presc));
			}
		}
	}

	@Override
	public String[] getPosiblesPrescripciones() {
		return new String[] {"Paracetamol", "Medicamento",
				"Vendaje", "Tratamiento",
				"Penicilina", "Medicamento",
				"Resonancia Magnetica", "Prueba"};
	}

	@Override
	public String[] getPosiblesCausas() {
		return new String[] {"Fiebre","Vomitos","Hipotermia","Dolor de cabeza"};
	}

	@Override
	public PrescripcionRecord createPrescripcionRecord(String titulo, String tipo, String observaciones, LocalTime hora,
			LocalDate fecha, String idCita) {
		int id = PersistenceFactory.forCita().getLastId("PRESCRIPCION", "IDPRESCRIPCION");
		return new PrescripcionRecord(id, titulo, tipo, observaciones, hora, fecha, idCita);
	}

	@Override
	public PrescripcionRecord createPrescripcionRecord(String titulo, String tipo, String cantidad, String intervalo,
			String duracion, String observaciones, LocalTime hora, LocalDate fecha, String idCita) {
		int id = PersistenceFactory.forCita().getLastId("PRESCRIPCION", "IDPRESCRIPCION");
		return new PrescripcionRecord(id, titulo, tipo, cantidad, intervalo, duracion, observaciones, hora, fecha, idCita);
	}

}
