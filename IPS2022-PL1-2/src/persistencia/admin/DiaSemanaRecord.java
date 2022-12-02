package persistencia.admin;

import java.sql.Timestamp;

public class DiaSemanaRecord {
    public int idJornada;
    public int idMedico;
    public String dia;
    public Timestamp inicio;
    public Timestamp fin;

    public String toString() {
	return ("Lic: " + idMedico + " - Inicio Jornada: " + inicio
		+ " - Fin Jornada: " + fin + " - D�as: " + dia);
    }
}
