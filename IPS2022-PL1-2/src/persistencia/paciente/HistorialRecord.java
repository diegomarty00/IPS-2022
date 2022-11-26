package persistencia.paciente;

import java.util.ArrayList;
import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;

public class HistorialRecord {

	private int idHistorial;
	private String dniPaciente;
	private List<CitaRecord> citas;
	private List<CausaRecord> causas;
	private List<PrescripcionRecord> prescripciones;
	
	public int getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}
	
	public List<CitaRecord> getCitas() {
		if (citas==null) {
			return PersistenceFactory.forCita().findByHistorialId(idHistorial);
		}
		return citas;
	}

	public List<CausaRecord> getCausas() {
		if (causas==null) {
			List<CausaRecord> myCausas = new ArrayList<>();
			for (CitaRecord cita : getCitas()) {
				myCausas.addAll(PersistenceFactory.forCita().getCausas(cita.idCita));
			}
			return myCausas;
		}
		return causas;
	}

	public List<PrescripcionRecord> getPrescripciones() {
		if (prescripciones==null) {
			List<PrescripcionRecord> myPrescripciones = new ArrayList<>();
			for (CitaRecord cita : getCitas()) {
				myPrescripciones.addAll(PersistenceFactory.forCita().getPrescripciones(cita.idCita));
			}
			return myPrescripciones;
		}
		return prescripciones;
	}

	
}
