package persistencia.admin;

import persistencia.Gateway;

public interface AdminGateway extends Gateway<MedicoRecord> {

    void añadirJornadas(JornadaRecord medico);

    void asignarInformacionContactoCitas();
}
