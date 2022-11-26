package persistencia.paciente;

import java.util.List;

import persistencia.Gateway;

public interface PacienteGateway extends Gateway<PacienteRecord>{
	
	HistorialRecord getHistorial(String dniPaciente);
}
