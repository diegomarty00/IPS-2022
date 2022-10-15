package persistencia.pacientes;

public class PacientesRecord {
	public String dni ;
	public String nombre;
	public String apellidos;
	public String correo;
	public int telefono;
	@Override
	public String toString() {
		return ""+ nombre + " " + apellidos + " " + dni;
	}
	
}
