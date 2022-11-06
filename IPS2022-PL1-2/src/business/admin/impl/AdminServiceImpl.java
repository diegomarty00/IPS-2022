package business.admin.impl;

import java.util.List;

import business.admin.AdminService;
import business.admin.operaciones.AsignarJornadasLaboralesMedicos;
import business.admin.operaciones.BuscarMedicos;
import business.admin.operaciones.CrearJornadasLaboralesComunes;
import persistencia.admin.JornadaComunRecord;
import persistencia.admin.JornadaRecord;
import persistencia.admin.MedicoRecord;
import util.BusinessException;
import util.command.CommandExecutor;

public class AdminServiceImpl implements AdminService {

    CommandExecutor c = new CommandExecutor();

    @Override
    public void asignarJornadasLaboralesMedicos(JornadaRecord jornada)
	    throws BusinessException {
	c.execute(new AsignarJornadasLaboralesMedicos(jornada));
    }

    @Override
    public List<MedicoRecord> buscarMedicos() throws BusinessException {
	return c.execute(new BuscarMedicos());

    }

    @Override
    public void crearJornadasLaboralesComuens(JornadaComunRecord jornada)
	    throws BusinessException {
	c.execute(new CrearJornadasLaboralesComunes(jornada));
    }

}
