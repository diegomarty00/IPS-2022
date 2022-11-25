package persistencia.enfermero;

public class EnfermeroRecord {

	public int idEnfermero;
	public String nombre;
	public String apellidos;
	
	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public String toString() {
		return ""  + nombre + " "+ apellidos + " "+idEnfermero  ;
	}
}
