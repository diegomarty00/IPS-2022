package persistencia.admin;

import java.util.List;

import persistencia.Gateway;

public interface AdminGateway extends Gateway<MedicoRecord> {

    void aniadirJornadas(JornadaRecord medico);

    void asignarInformacionContactoCitas();

    void crearJornadas(JornadaComunRecord jornada);

    List<JornadaRecord> findByMedico(String idMedico);

    void asignarMedicoCabeceraDni(String dniPaciente, int idMedico);

    void asignarMedicoCabeceraTutor(String dniTutor, int idMedico);
}
