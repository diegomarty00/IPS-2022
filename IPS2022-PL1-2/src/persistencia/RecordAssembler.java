package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import persistencia.cita.CitaRecord;

public class RecordAssembler {
	
	private static CitaRecord resultSetToCitaRecord(ResultSet rs) throws SQLException{
		CitaRecord cita = new CitaRecord();
		
		cita.idCita = rs.getString("IDCITA");
		cita.dniPaciente = rs.getString("DNIPACIENTE");
		cita.urgente = rs.getBoolean("URGENTE");
		cita.horaEntradaEstimada = rs.getTime("HORA_ENTRADA_ESTIMADA").toLocalTime();
		cita.horaSalidaEstimada= rs.getTime("HORA_SALIDA_ESTIMADA").toLocalTime();
		cita.pacienteAcudido = rs.getBoolean("PACIENTE_ACUDIDO");
		cita.horaEntradaReal = rs.getTime("HORA_ENTRADA_REAL").toLocalTime();
		cita.horaSalidaReal= rs.getTime("HORA_SALIDA_REAL").toLocalTime();
		cita.correoPaciente = rs.getString("CORREO_PACIENTE");
		cita.telefonoPaciente = rs.getString("TELEFONO_PACIENTE");
		
		return cita;
	}
	
	public static Optional<CitaRecord> toCitaRecord(ResultSet rs) throws SQLException{
		if (rs.next()) {
			return Optional.of(resultSetToCitaRecord(rs));
		}
		else 
			return Optional.ofNullable(null);
	}
}
