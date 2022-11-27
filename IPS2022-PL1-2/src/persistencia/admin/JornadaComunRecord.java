package persistencia.admin;

import java.util.List;

public class JornadaComunRecord {
    public int idJornada;
    public String nombre;
    public String inicio;
    public String fin;
    public List<String> lunes;
    public List<String> martes;
    public List<String> miercoles;
    public List<String> jueves;
    public List<String> viernes;
    public List<String> sabado;
    public List<String> domingo;

    public String toString() {
	String result = "Jornada: " + nombre + "\n";
	result = Jornadas(result, lunes, "lunes") + "\n";
	result = Jornadas(result, martes, "martes") + "\n";
	result = Jornadas(result, miercoles, "miercoles") + "\n";
	result = Jornadas(result, jueves, "jueves") + "\n";
	result = Jornadas(result, viernes, "viernes") + "\n";
	result = Jornadas(result, sabado, "sabado") + "\n";
	result = Jornadas(result, domingo, "domingo") + "\n";
	return result;
    }

    private String Jornadas(String result, List<String> lista,
	    String diaSemana) {
	result += diaSemana + ": ";
	result = listado(result, lista);
	return result;
    }

    public String listado(String result, List<String> lista) {
	if (lista != null) {
	    for (int i = 0; i < lista.size(); i++) {
		if (i == 0)
		    result += lista.get(i);
		else
		    result += ", " + lista.get(i);
	    }
	} else {
	    result += "Descanso";
	}
	return result;
    }

}
