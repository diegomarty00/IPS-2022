package persistencia.admin;

public class MedicoRecord {
    public int id;
    public String nombre;
    public String apellidos;
    public String correo;

    public String toString() {
	return "Lic: " + id + " - " + nombre + " " + apellidos;
    }
}
