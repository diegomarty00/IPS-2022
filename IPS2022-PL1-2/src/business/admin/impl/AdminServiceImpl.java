package business.admin.impl;

import java.util.List;

import business.admin.AdminService;
import business.admin.operaciones.AsignarJornadasLaboralesComunes;
import business.admin.operaciones.AsignarJornadasLaboralesMedicos;
import business.admin.operaciones.AsignarMedicoCabeceraDni;
import business.admin.operaciones.AsignarMedicoCabeceraTutor;
import business.admin.operaciones.BuscarMedicos;
import business.admin.operaciones.CrearJornadasLaboralesComunes;
import business.admin.operaciones.EliminarMedicoCabeceraDni;
import business.admin.operaciones.EliminarMedicoCabeceraTutor;
import business.admin.operaciones.ListarJornadasMedico;
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
    public void crearJornadasLaboralesComunes(JornadaComunRecord jornada)
	    throws BusinessException {
	c.execute(new CrearJornadasLaboralesComunes(jornada));
    }

    @Override
    public void asignarJornadasLaboralesComunes(JornadaComunRecord comun,
	    JornadaRecord jornada, MedicoRecord medico)
	    throws BusinessException {
	c.execute(new AsignarJornadasLaboralesComunes(comun, jornada, medico));
    }

    @Override
    public List<JornadaRecord> listarJornadasMedico(String idMedico)
	    throws BusinessException {
	return c.execute(new ListarJornadasMedico(idMedico));

    }

    @Override
    public void asignarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname, int idMedico) throws BusinessException {
	c.execute(new AsignarMedicoCabeceraTutor(dniTutor, name, surname,
		idMedico));
    }

    @Override
    public void asignarMedicoCabeceraDni(String dni, int idMedico)
	    throws BusinessException {
	c.execute(new AsignarMedicoCabeceraDni(dni, idMedico));
    }

    @Override
    public void eliminarMedicoCabeceraDni(String dni) throws BusinessException {
	c.execute(new EliminarMedicoCabeceraDni(dni));
    }

    @Override
    public void eliminarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname) throws BusinessException {
	c.execute(new EliminarMedicoCabeceraTutor(dniTutor, name, surname));
    }

}
