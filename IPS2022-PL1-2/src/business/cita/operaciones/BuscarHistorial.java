package business.cita.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.paciente.HistorialRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarHistorial implements Command<Optional<HistorialRecord>> {

    private int idPaciente;

    public BuscarHistorial(int idPaciente) {
	this.idPaciente = idPaciente;
    }

    @Override
    public Optional<HistorialRecord> execute() throws BusinessException {
	Optional<HistorialRecord> medico = PersistenceFactory.forMedico()
		.buscarHistorial(idPaciente);
	return medico;
    }

}
