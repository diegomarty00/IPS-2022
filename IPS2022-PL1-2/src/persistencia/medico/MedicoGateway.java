package persistencia.medico;

import java.util.Optional;

import persistencia.Gateway;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.VacunaRecord;

public interface MedicoGateway extends Gateway<MedicoRecord> {

    void crearVacuna(VacunaRecord vacuna);

    Optional<HistorialRecord> buscarHistorial(int idPaciente);

}
