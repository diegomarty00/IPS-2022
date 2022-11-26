package business.cita.operaciones;

import java.util.List;

import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.cita.impl.CitaGatewayImpl;
import util.BusinessException;
import util.command.Command;

public class ModificarCita implements Command<List<CitaRecord>> {

    String idCita;
    String correo;
    int telefono;
    CitaRecord cita;
    

    public ModificarCita(String idCita, String correo, int telefono) {
	this.idCita = idCita;
	this.correo = correo;
	this.telefono = telefono;
    }
    
    public ModificarCita(CitaRecord cita) {
    	this.cita = cita;
    	modificarCita(cita);
        }

    
    private void  modificarCita(CitaRecord c) {
    	CitaGatewayImpl cg = new CitaGatewayImpl();
    	cg.ModificarTodo(c);
    }
    
    
    @Override
    public List<CitaRecord> execute() throws BusinessException {
	PersistenceFactory.forCita().modificarCita(idCita, correo, telefono);
	return null;
    }

}
