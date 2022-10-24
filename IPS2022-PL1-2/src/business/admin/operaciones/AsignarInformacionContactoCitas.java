package business.admin.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class AsignarInformacionContactoCitas implements Command<Void> {

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forAdmin().asignarInformacionContactoCitas();
	return null;
    }

}
