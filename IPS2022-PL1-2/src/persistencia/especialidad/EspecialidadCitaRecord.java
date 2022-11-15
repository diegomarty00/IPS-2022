package persistencia.especialidad;

public class EspecialidadCitaRecord {
	@Override
	public String toString() {
		if(nMedicos == 0) return idEspecialidad;
		else {
			return  idEspecialidad + "	" + nMedicos ;
		}
	}
	public String idEspecialidad;
	public String idCita;
	public int nMedicos;
}
