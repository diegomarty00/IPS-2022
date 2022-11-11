package persistencia.medico;

public class MedicoRecord {
	public int idMedico;
	public String nombre;
	public String apellidos;
	public String correo;
	@Override
	public String toString() {
		return ""  + nombre + " "+ apellidos + " "+idMedico  ;
	}
	
	
}
