package business.cita;

import java.util.ArrayList;
import java.util.List;

import persistencia.cita.CitaRecord;
import util.BusinessException;

public interface CitaService {

    void asignarHoraEntrada(String idCita, int horaEntrada, int minEntrada)
	    throws BusinessException;

    void asignarHoraSalida(String idCita, int horaSalida, int minSalida)
	    throws BusinessException;

    void pacienteAcudido(String idCita) throws BusinessException;

    List<CitaRecord> getCitasProximas(int year, int month, int day)
	    throws BusinessException;

    List<CitaRecord> getCitasDelDia(int year, int month, int day)
	    throws BusinessException;

    void modificarCita(String idCita, String textCorreo, int textTelefono)
	    throws BusinessException;

    void updateCausas(String idCita, ArrayList<String> causas)
    	throws BusinessException;
    
}
