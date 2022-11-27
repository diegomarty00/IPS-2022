package persistencia.admin;

import java.util.ArrayList;
import java.util.List;

public class JornadaComunRecord {
    public int idJornada;
    public String nombre;
    public String inicio;
    public String fin;
    public List<String> lunes = new ArrayList<>();
    public List<String> martes = new ArrayList<>();
    public List<String> miercoles = new ArrayList<>();
    public List<String> jueves = new ArrayList<>();
    public List<String> viernes = new ArrayList<>();
    public List<String> sabado = new ArrayList<>();
    public List<String> domingo = new ArrayList<>();

    @Override
    public String toString() {
	String result = "";
	if (nombre != "") {
	    result = "Jornada: " + nombre + "\n";
	    if (lunes.size() != 0)
		result = Jornadas(result, lunes, "lunes") + "\n";
	    if (martes.size() != 0)
		result = Jornadas(result, martes, "martes") + "\n";
	    if (miercoles.size() != 0)
		result = Jornadas(result, miercoles, "miercoles") + "\n";
	    if (jueves.size() != 0)
		result = Jornadas(result, jueves, "jueves") + "\n";
	    if (viernes.size() != 0)
		result = Jornadas(result, viernes, "viernes") + "\n";
	    if (sabado.size() != 0)
		result = Jornadas(result, sabado, "sabado") + "\n";
	    if (domingo.size() != 0)
		result = Jornadas(result, domingo, "domingo") + "\n";
	}
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
