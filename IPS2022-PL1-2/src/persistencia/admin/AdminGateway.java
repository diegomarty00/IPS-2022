package persistencia.admin;

import persistencia.Gateway;

public interface AdminGateway extends Gateway<MedicoRecord> {

    void a�adirJornadas(JornadaRecord medico);

    void asignarInformacionContactoCitas();
}
