package persistencia.paciente;

import java.util.List;

import persistencia.Gateway;

public interface PacienteGateway extends Gateway<PacienteRecord> {

    List<HistorialRecord> getHistorial(String dniPaciente);
}
