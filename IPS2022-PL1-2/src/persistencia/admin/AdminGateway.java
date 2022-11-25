package persistencia.admin;

import java.util.List;
import java.util.Optional;

import persistencia.Gateway;
import persistencia.paciente.PacienteRecord;

public interface AdminGateway extends Gateway<MedicoRecord> {

    void aniadirJornadas(JornadaRecord medico);

    void asignarInformacionContactoCitas();

    void crearJornadas(JornadaComunRecord jornada);

    List<JornadaRecord> findByMedico(String idMedico);

    void asignarMedicoCabeceraDni(String dniPaciente, int idMedico);

    void asignarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname, int idMedico);

    Optional<PacienteRecord> findByPacienteTutor(String dniTutor, String name,
	    String surname);

    Optional<PacienteRecord> findByPacienteDni(String dni);

}
