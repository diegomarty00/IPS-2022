package business.admin.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarJornadasMedicos implements Command<List<JornadaRecord>> {

    @Override
    public List<JornadaRecord> execute() throws BusinessException {
	List<JornadaRecord> jornadas = PersistenceFactory.forAdmin()
		.findAllJornadasMedicos();
	return jornadas;
    }

}
