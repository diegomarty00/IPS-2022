package business.admin.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaComunRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarJornadasComunes
	implements Command<List<JornadaComunRecord>> {

    @Override
    public List<JornadaComunRecord> execute() throws BusinessException {
	List<JornadaComunRecord> jornadas = PersistenceFactory.forAdmin()
		.findAllJornadasComunes();
	return jornadas;
    }

}
