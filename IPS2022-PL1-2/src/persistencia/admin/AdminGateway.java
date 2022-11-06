package persistencia.admin;

import java.util.List;

import persistencia.Gateway;

public interface AdminGateway extends Gateway<MedicoRecord> {

    void a�adirJornadas(JornadaRecord medico);

    void asignarInformacionContactoCitas();

    void crearJornadas(JornadaComunRecord jornada);

    List<JornadaRecord> findByMedico(String idMedico);
}
