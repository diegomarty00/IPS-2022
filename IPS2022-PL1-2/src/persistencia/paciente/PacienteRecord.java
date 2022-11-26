package persistencia.paciente;

import java.util.Objects;

public class PacienteRecord {
	
	private String dniPaciente;
	private String nombre;
	private String apellidos;
	private String correo;
	
	
	public String getDniPaciente() {
		return dniPaciente;
	}
	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dniPaciente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacienteRecord other = (PacienteRecord) obj;
		return Objects.equals(dniPaciente, other.dniPaciente);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int telefono;


	@Override
	public String toString() {
		return "" + nombre + " " + apellidos + " " + dniPaciente;
	}
	
}
