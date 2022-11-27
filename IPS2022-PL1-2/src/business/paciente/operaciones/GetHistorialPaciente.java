package business.paciente.operaciones;

import persistencia.PersistenceFactory;
import persistencia.paciente.HistorialRecord;
import util.BusinessException;
import util.command.Command;

public class GetHistorialPaciente implements Command<HistorialRecord>{

	private String dniPaciente; 
	
	public GetHistorialPaciente(String dniPaciente) {
		super();
		this.dniPaciente = dniPaciente;
	}

	@Override
	public HistorialRecord execute() throws BusinessException {
		return PersistenceFactory.forPaciente().getHistorial(dniPaciente);
	}

}
