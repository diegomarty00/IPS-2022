package business.admin.operaciones;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaComunRecord;
import util.BusinessException;
import util.command.Command;

public class CrearJornadasLaboralesComunes implements Command<Void> {

    JornadaComunRecord jornada;

    public CrearJornadasLaboralesComunes(JornadaComunRecord jornada) {
	this.jornada = jornada;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().crearJornadas(jornada);
	return null;
    }
}
