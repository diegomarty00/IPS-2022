package persistencia.cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CausaRecord {
	public int idCausa;
	public String titulo;
	public LocalTime horaAsignacion;
	public LocalDate fechaAsignacion;
	public String idCita;
	
	@Override
	public String toString() {
		return titulo+" ("+horaAsignacion.toString()+" del dia "+fechaAsignacion.toString()+")";
	}
}
