package business.cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
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

    void updateCausas(String idCita, ArrayList<String> causas, LocalDate fecha, LocalTime hora)
    	throws BusinessException;
    
    void updatePrescripciones(List<PrescripcionRecord> prescripciones, String idCita)
    		throws BusinessException;

	String[] getPosiblesPrescripciones();

	String[] getPosiblesCausas();

	PrescripcionRecord createPrescripcionRecord(String titulo, String tipo, String observaciones, LocalTime hora, LocalDate fecha, String idCita);

	PrescripcionRecord createPrescripcionRecord(String titulo, String tipo, String cantidad, String intervalo, String duracion,
			String observaciones, LocalTime hora, LocalDate fecha, String idCita);
    
}
