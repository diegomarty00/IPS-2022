package business.cita.operaciones;

import persistencia.PersistenceFactory;
import util.BusinessException;
import util.command.Command;

public class DeleteCausa implements Command<Void>{

	private int idCausa;
	
	public DeleteCausa(int idCausa) {
		super();
		this.idCausa = idCausa;
	}

	@Override
	public Void execute() throws BusinessException {
		PersistenceFactory.forCita().removeCausa(idCausa);
		return null;
	}

}
