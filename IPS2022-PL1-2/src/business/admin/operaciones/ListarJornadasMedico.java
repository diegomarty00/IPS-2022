package business.admin.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaRecord;
import util.BusinessException;
import util.command.Command;

public class ListarJornadasMedico implements Command<List<JornadaRecord>> {

    String idMedico;

    public ListarJornadasMedico(String idMedico) {
	this.idMedico = idMedico;
    }

    @Override
    public List<JornadaRecord> execute() throws BusinessException {
	List<JornadaRecord> jornadas = PersistenceFactory.forAdmin()
		.findByMedico(idMedico);
	return jornadas;
    }

}
