package business.admin.operaciones;

import java.util.Optional;

import persistencia.PersistenceFactory;
import persistencia.admin.JornadaComunRecord;
import util.BusinessException;
import util.command.Command;

public class BuscarJornadaComun
	implements Command<Optional<JornadaComunRecord>> {

    private String nombre;

    public BuscarJornadaComun(String nombre) {
	this.nombre = nombre;
    }

    @Override
    public Optional<JornadaComunRecord> execute() throws BusinessException {
	Optional<JornadaComunRecord> jornada = PersistenceFactory.forAdmin()
		.findJornadaNombre(nombre);
	return jornada;
    }

}
