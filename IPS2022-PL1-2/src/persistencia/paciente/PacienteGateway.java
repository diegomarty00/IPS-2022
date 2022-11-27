package persistencia.paciente;

import java.util.List;

import persistencia.Gateway;


public interface PacienteGateway extends Gateway<PacienteRecord>{
	
	HistorialRecord getHistorial(int idPaciente);

	void vacunar(VacunaRecord vacuna);
	
	List<VacunaRecord> getVacunas(int idHistorial);

}
