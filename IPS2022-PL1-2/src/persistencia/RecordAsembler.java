package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import persistencia.cita.CitaRecord;
import persistencia.medico.MedicoRecord;
import persistencia.pacientes.PacientesRecord;

public class RecordAsembler {

	public static MedicoRecord rsToMedico(ResultSet r) throws SQLException {
		MedicoRecord med = new MedicoRecord();
		med.idMedico = r.getInt("idmedico");
		med.nombre = r.getString("nombre");
		med.apellidos = r.getString("apellidos");
		med.correo = r.getString("correo");
		
		return med;
	}
	
	public static PacientesRecord rsToPacientes(ResultSet r) throws SQLException{
		PacientesRecord pa = new PacientesRecord();
		pa.dni = r.getString("dni");
		pa.nombre = r.getString("nombre");
		pa.apellidos = r.getString("apellidos");
		pa.correo = r.getString("correo");
		pa.telefono = r.getInt("telefono");
		return pa;
	}
	
	public static List<MedicoRecord> toMedicoList(ResultSet r) throws SQLException{
		List<MedicoRecord> list = new ArrayList<>();
		while(r.next()) {
			list.add(rsToMedico(r));
		}
		return list ;
	}

	public static List<PacientesRecord> toPacienteList(ResultSet r) throws SQLException {
		List<PacientesRecord> list = new ArrayList<>();
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

	public static Optional<CitaRecord> toCitaRecord(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToCitaRecord(rs));
		} else
			return Optional.ofNullable(null);
	}
}
