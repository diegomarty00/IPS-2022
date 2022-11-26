package persistencia.especialidad;

import persistencia.Gateway;

public interface EspecialidadCitaGateway extends Gateway<EspecialidadCitaRecord> {

	public void removeEspecialidadC(String idCita);
}
