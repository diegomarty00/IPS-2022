package business.admin;

import java.util.List;
import java.util.Optional;

import persistencia.admin.JornadaComunRecord;
import persistencia.admin.JornadaRecord;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public interface AdminService {
    void asignarJornadasLaboralesMedicos(JornadaRecord jornada)
	    throws BusinessException;

    List<MedicoRecord> buscarMedicos() throws BusinessException;

    void crearJornadasLaboralesComunes(JornadaComunRecord jornada)
	    throws BusinessException;

    void asignarJornadasLaboralesComunes(JornadaComunRecord comun,
	    JornadaRecord jornada, MedicoRecord medico)
	    throws BusinessException;

    List<JornadaRecord> listarJornadasMedico(String idMedico)
	    throws BusinessException;

    void asignarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname, int idMedico) throws BusinessException;

    void asignarMedicoCabeceraDni(String dni, int idMedico)
	    throws BusinessException;

    void eliminarMedicoCabeceraDni(String dni) throws BusinessException;

    void eliminarMedicoCabeceraTutor(String dniTutor, String name,
	    String surname) throws BusinessException;

    void buscarPacienteDni(String dni) throws BusinessException;

    void buscarPacienteTutor(String dniTutor, String name, String surname)
	    throws BusinessException;

    List<PacienteRecord> buscarPacientes() throws BusinessException;

    Optional<MedicoRecord> buscarMedico(int licencia) throws BusinessException;

    Optional<JornadaComunRecord> buscarJornadaComun(String nombre)
	    throws BusinessException;
}
