package persistencia.paciente;

import java.util.List;

import persistencia.Gateway;

public interface PacienteGateway extends Gateway<PacienteRecord>{
	
	HistorialRecord getHistorial(String dniPaciente);

	void vacunar(VacunaRecord vacuna);
	
	List<VacunaRecord> getVacunas(int idHistorial);
}
