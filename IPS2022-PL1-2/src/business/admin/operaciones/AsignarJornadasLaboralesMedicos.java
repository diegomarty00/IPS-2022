package business.admin.operaciones;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaRecord;
import util.BusinessException;
import util.command.Command;

public class AsignarJornadasLaboralesMedicos implements Command<Void> {

    JornadaRecord jornada;

    public AsignarJornadasLaboralesMedicos(JornadaRecord jornada) {
	this.jornada = jornada;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().añadirJornadas(jornada);
	return null;
    }

}
