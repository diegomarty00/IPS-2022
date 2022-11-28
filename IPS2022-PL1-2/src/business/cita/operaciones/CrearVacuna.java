package business.cita.operaciones;

import persistencia.PersistenceFactory;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;
import util.command.Command;

public class CrearVacuna implements Command<Void> {

    VacunaRecord vacuna;

    public CrearVacuna(VacunaRecord vacuna) {
	this.vacuna = vacuna;
    }

    @Override
    public Void execute() throws BusinessException {
	PersistenceFactory.forMedico().crearVacuna(vacuna);
	return null;
    }
}
