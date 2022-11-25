package persistencia.enfermero;

public class EnfermeroRecord {

	public int idEnfermero;
	public String nombre;
	public String apellidos;
	public String correo;
	@Override
	public String toString() {
		return ""  + nombre + " "+ apellidos + " "+idEnfermero  ;
	}
}
