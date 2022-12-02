package business.admin.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.medico.MedicoRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarMedico implements Command<Optional<MedicoRecord>> {

    private int licencia;

    public BuscarMedico(int licencia) {
	this.licencia = licencia;
    }

    @Override
    public Optional<MedicoRecord> execute() throws BusinessException {
	Optional<MedicoRecord> medico = PersistenceFactory.forAdmin()
		.findMedicoLic(licencia);
	return medico;
    }

}
