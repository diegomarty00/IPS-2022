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

    public boolean comprobarHora(int hora, int minuto, String dia,
	    boolean inicial) {
	String[] franja;
	String[] inicio;
	String[] fin;
	if (dia == "lunes") {
	    if (!compribacion(hora, minuto, lunes, inicial))
		return false;

	}
	if (dia == "martes") {
	    if (!compribacion(hora, minuto, martes, inicial))
		return false;
	}
	if (dia == "miercoles") {
	    if (!compribacion(hora, minuto, miercoles, inicial))
		return false;
	}
	if (dia == "jueves") {
	    if (!compribacion(hora, minuto, jueves, inicial))
		return false;
	}
	if (dia == "viernes") {
	    if (!compribacion(hora, minuto, viernes, inicial))
		return false;
	}
	if (dia == "sabado") {
	    if (!compribacion(hora, minuto, sabado, inicial))
		return false;
	}
	if (dia == "domingo") {
	    if (!compribacion(hora, minuto, domingo, inicial))
		return false;
	}
	return true;
    }

    private boolean compribacion(int hora, int minuto, List<String> dia,
	    boolean inicial) {
	String[] franja;
	String[] inicio;
	String[] fin;
	for (String h : dia) {
	    franja = h.split("-");
	    inicio = franja[0].split(":");
	    fin = franja[1].split(":");

	    if (hora >= Integer.parseInt(inicio[0])
		    && hora <= Integer.parseInt(fin[0]))
		// Inicio
		if (inicial) {
		    if (minuto >= Integer.parseInt(inicio[1])
			    && minuto < Integer.parseInt(fin[1]))
			return false;
		} else {
		    if (minuto > Integer.parseInt(inicio[1])
			    && minuto <= Integer.parseInt(fin[1]))
			return false;
		}
	}
	return true;
    }

    public void transformar(String cadena, String dia) {
	if (dia == "lunes") {
	    tranformacion(cadena, lunes);
	}
	if (dia == "martes") {
	    tranformacion(cadena, martes);
	}
	if (dia == "miercoles") {
	    tranformacion(cadena, miercoles);
	}
	if (dia == "jueves") {
	    tranformacion(cadena, jueves);
	}
	if (dia == "viernes") {
	    tranformacion(cadena, viernes);
	}
	if (dia == "sabado") {
	    tranformacion(cadena, sabado);
	}
	if (dia == "domingo") {
	    tranformacion(cadena, domingo);
	}
    }

    private void tranformacion(String cadena, List<String> dia) {
	String[] horas = cadena.split(",");
	for (String hora : horas) {
	    dia.add(hora);
	}
    }

}
