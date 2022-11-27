package business.admin.operaciones;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaComunRecord;
import persistencia.admin.JornadaRecord;
import persistencia.medico.MedicoRecord;
import util.BusinessException;
import util.command.Command;

public class AsignarJornadasLaboralesComunes implements Command<Void> {

    JornadaRecord jornada;
    JornadaComunRecord comun;
    MedicoRecord medico;

    public AsignarJornadasLaboralesComunes(JornadaComunRecord comun,
	    JornadaRecord jornada, MedicoRecord medico) {
	this.comun = comun;
	this.jornada = jornada;
	this.medico = medico;
    }

    @Override
    public Void execute() throws BusinessException {
	for (int i = 0; i < comun.lunes.size(); i++) {
	    calcularFechas(comun.lunes.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	for (int i = 0; i < comun.martes.size(); i++) {
	    calcularFechas(comun.martes.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	for (int i = 0; i < comun.miercoles.size(); i++) {
	    calcularFechas(comun.miercoles.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	for (int i = 0; i < comun.jueves.size(); i++) {
	    calcularFechas(comun.jueves.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	for (int i = 0; i < comun.viernes.size(); i++) {
	    calcularFechas(comun.viernes.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	for (int i = 0; i < comun.sabado.size(); i++) {
	    calcularFechas(comun.sabado.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	for (int i = 0; i < comun.domingo.size(); i++) {
	    calcularFechas(comun.domingo.get(i));
	    PersistenceFactory.forAdmin().aniadirJornadas(jornada);
	}
	return null;
    }

    @SuppressWarnings("deprecation")
    public void calcularFechas(String horas) {
	String[] franjas = horas.split("-");
	String[] hora = franjas[0].split(":");
	jornada.inicio.setHours(Integer.parseInt(hora[0]));
	jornada.inicio.setMinutes(Integer.parseInt(hora[1]));

	hora = franjas[1].split(":");
	jornada.fin.setHours(Integer.parseInt(hora[0]));
	jornada.fin.setMinutes(Integer.parseInt(hora[1]));
    }
}
