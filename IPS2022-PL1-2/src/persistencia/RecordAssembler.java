package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import persistencia.cita.CitaRecord;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;

public class RecordAssembler {

	private static CitaRecord resultSetToCitaRecord(ResultSet rs) throws SQLException {
		CitaRecord cita = new CitaRecord();

		cita.idCita = rs.getString("IDCITA");
		cita.dniPaciente = rs.getString("DNIPACIENTE");
		cita.urgente = rs.getBoolean("URGENTE");
		cita.horaEntradaEstimada = rs.getTime("HORA_ENTRADA_ESTIMADA").toLocalTime();
		cita.horaSalidaEstimada = rs.getTime("HORA_SALIDA_ESTIMADA").toLocalTime();
		cita.pacienteAcudido = rs.getBoolean("PACIENTE_ACUDIDO");
		if (rs.getTime("HORA_ENTRADA_REAL") != null)
			cita.horaEntradaReal = rs.getTime("HORA_ENTRADA_REAL").toLocalTime();
		if (rs.getTime("HORA_SALIDA_REAL") != null)
			cita.horaSalidaReal = rs.getTime("HORA_SALIDA_REAL").toLocalTime();
		cita.correoPaciente = rs.getString("CORREO_PACIENTE");
		cita.telefonoPaciente = rs.getString("TELEFONO_PACIENTE");

		return cita;
	}
	
	private static PacienteRecord resultSetToPacienteRecord(ResultSet rs) throws SQLException {
		PacienteRecord paciente = new PacienteRecord();
		paciente.setDniPaciente(rs.getString("DNI"));
		paciente.setNombre(rs.getString("NOMBRE"));
		paciente.setApellidos(rs.getString("APELLIDOS"));
		paciente.setCorreo(rs.getString("CORREO"));
		paciente.setTelefono(rs.getInt("TELEFONO"));
		
		return paciente;
	}
	
	private static HistorialRecord resultSetToHistorialRecord(ResultSet rs) throws SQLException {
		HistorialRecord historial = new HistorialRecord();
		historial.setIdHistorial(rs.getString("IDHISTORIAL"));
		historial.setTitulo(rs.getString("TITULO"));
		historial.setDescripcion(rs.getString("DESCRIPCION"));
		historial.setDniPaciente(rs.getString("DNIPACIENTE"));
		historial.setIdMedico(rs.getInt("IDMEDICO"));
		historial.setFecha(rs.getDate("FECHA").toLocalDate());
		return historial;
	}

	public static Optional<CitaRecord> toCitaRecord(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToCitaRecord(rs));
		} else
			return Optional.ofNullable(null);
	}
	
	public static Optional<PacienteRecord> toPacienteRecord(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToPacienteRecord(rs));
		} else
			return Optional.ofNullable(null);
	}

	public static List<CitaRecord> toCitaList(ResultSet rs) throws SQLException {
		List<CitaRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToCitaRecord(rs));
		}
		return res;
	}

	public static Optional<HistorialRecord> toHistorialRecord(ResultSet rs) throws SQLException{
		if (rs.next()) {
			return Optional.of(resultSetToHistorialRecord(rs));
		} else
			return Optional.ofNullable(null);
	}

	public static List<HistorialRecord> toHistorialList(ResultSet rs) throws SQLException {
		List<HistorialRecord> list = new ArrayList<>();
		while (rs.next()) {
			list.add(resultSetToHistorialRecord(rs));
		}
		return list;
	}
}
