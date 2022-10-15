package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import persistencia.cita.CitaRecord;
import persistencia.cita.MedicoCitaRecord;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;

public class RecordAssembler {
	
	
	public static MedicoCitaRecord rsToMedicosCita(ResultSet r) throws SQLException{
		MedicoCitaRecord mc = new MedicoCitaRecord();
		mc.idCita = r.getString("idcita");
		mc.idMedico = r.getInt("idmedico");
		
		return mc;
	}
	
	public static List<MedicoCitaRecord> toMedicoCitaList(ResultSet r) throws SQLException{
		List<MedicoCitaRecord> list = new ArrayList<>();
		while(r.next()) {
			list.add(rsToMedicosCita(r));
		}
		return list ;
	}
	
	public static MedicoRecord rsToMedico(ResultSet r) throws SQLException {
		MedicoRecord med = new MedicoRecord();
		med.idMedico = r.getInt("idmedico");
		med.nombre = r.getString("nombre");
		med.apellidos = r.getString("apellidos");
		med.correo = r.getString("correo");
		
		return med;
	}
	
	public static PacienteRecord rsToPacientes(ResultSet r) throws SQLException{
		PacienteRecord pa = new PacienteRecord();
		pa.setDniPaciente( r.getString("dni"));
		pa.setNombre(r.getString("nombre"));
		pa.setApellidos(r.getString("apellidos"));
		pa.setCorreo(r.getString("correo"));
		pa.setTelefono(r.getInt("telefono"));
		return pa;
	}
	
	public static List<MedicoRecord> toMedicoList(ResultSet r) throws SQLException{
		List<MedicoRecord> list = new ArrayList<>();
		while(r.next()) {
			list.add(rsToMedico(r));
		}
		return list ;
	}

	public static List<PacienteRecord> toPacienteList(ResultSet r) throws SQLException {
		List<PacienteRecord> list = new ArrayList<>();
		while(r.next()) {
			list.add(rsToPacientes(r));
		}
		return list ;
	}

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
