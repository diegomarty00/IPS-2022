package business.paciente.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;
import util.command.Command;

public class GetPacienteById implements Command<Optional<PacienteRecord>> {

    private int idPaciente;

    public GetPacienteById(int idPaciente) {
	this.idPaciente =idPaciente;
    }

    @Override
    public Optional<PacienteRecord> execute() throws BusinessException {
	return PersistenceFactory.forPaciente().findById(String.valueOf(idPaciente));
    }

}
