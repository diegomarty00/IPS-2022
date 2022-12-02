package business.paciente.operaciones;

import persistencia.PersistenceFactory;
import persistencia.paciente.HistorialRecord;
import util.BusinessException;
import util.command.Command;

public class GetHistorialPaciente implements Command<HistorialRecord>{

	private int idPaciente; 
	
	public GetHistorialPaciente(int idPaciente) {
		super();
		this.idPaciente = idPaciente;
	}

	@Override
	public HistorialRecord execute() throws BusinessException {
		return PersistenceFactory.forPaciente().getHistorial(idPaciente);
	}

}
