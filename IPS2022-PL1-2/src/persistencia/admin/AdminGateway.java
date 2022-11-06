package persistencia.admin;

import java.util.List;

import persistencia.Gateway;

public interface AdminGateway extends Gateway<MedicoRecord> {

    void añadirJornadas(JornadaRecord medico);

    void asignarInformacionContactoCitas();

    List<JornadaRecord> findByMedico(String idMedico);

}
