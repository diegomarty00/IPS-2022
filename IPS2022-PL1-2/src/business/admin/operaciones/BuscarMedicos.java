package business.admin.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.admin.MedicoRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarMedicos implements Command<List<MedicoRecord>> {

    @Override
    public List<MedicoRecord> execute() throws BusinessException {
	List<MedicoRecord> medicos = PersistenceFactory.forAdmin().findAll();
	return medicos;
    }

}
