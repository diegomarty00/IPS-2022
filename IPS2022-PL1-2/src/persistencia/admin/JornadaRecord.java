package persistencia.admin;

import java.sql.Timestamp;

public class JornadaRecord {
    public int idJornada;
    public int idMedico;
    public String dia;
    public Timestamp inicio;
    public Timestamp fin;

    public String toString() {
	return ("Lic: " + idMedico + " - Inicio Jornada: " + inicio
		+ " - Fin Jornada: " + fin + " - Días: " + dia);
    }
}
