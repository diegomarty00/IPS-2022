package persistencia.cita;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import persistencia.Gateway;

public interface CitaGateway extends Gateway<CitaRecord> {

    void asignarHoraEntrada(String idCita, LocalTime horaEntrada);

    void asignarHoraSalida(String idCita, LocalTime horaSalida);

    void setPacienteAcudido(String idCita);

    List<CitaRecord> getCitasDelDia(Date dia);

    List<CitaRecord> getCitasProximas(Date dia);

    void modificarCita(String idCita, String correo, int telefono);

}
