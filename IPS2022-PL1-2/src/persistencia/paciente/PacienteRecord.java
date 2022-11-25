package persistencia.paciente;

import java.sql.Date;

public class PacienteRecord {

    private String dniPaciente;
    private String nombre;
    private String apellidos;
    private String correo;
    private Date fechaNac;
    private String tutorLegal;
    private String DniTutorLegal;
    private String tarjetaSanitaria;
    private int idMedicoCabecera;

    public String getDniPaciente() {
	return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
	this.dniPaciente = dniPaciente;
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

    public Date getFechaNac() {
	return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
	this.fechaNac = fechaNac;
    }

    public String getTutorLegal() {
	return tutorLegal;
    }

    public void setTutorLegal(String tutorLegal) {
	this.tutorLegal = tutorLegal;
    }

    public String getDniTutorLegal() {
	return DniTutorLegal;
    }

    public void setDniTutorLegal(String dniTutorLegal) {
	DniTutorLegal = dniTutorLegal;
    }

    public String getTarjetaSanitaria() {
	return tarjetaSanitaria;
    }

    public void setTarjetaSanitaria(String tarjetaSanitaria) {
	this.tarjetaSanitaria = tarjetaSanitaria;
    }

    public int getIdMedicoCabecera() {
	return idMedicoCabecera;
    }

    public void setIdMedicoCabecera(int idMedicoCabecera) {
	this.idMedicoCabecera = idMedicoCabecera;
    }

}
