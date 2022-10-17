package business.cita.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import util.BusinessException;
import util.command.Command;

public class ModificarCita implements Command<List<CitaRecord>> {

    String idCita;
    String correo;
    int telefono;

    public ModificarCita(String idCita, String correo, int telefono) {
	this.idCita = idCita;
	this.correo = correo;
	this.telefono = telefono;
    }

    @Override
    public List<CitaRecord> execute() throws BusinessException {
	PersistenceFactory.forCita().modificarCita(idCita, correo, telefono);
	return null;
    }

}
