package persistencia.paciente.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteGateway;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.jdbc.Jdbc;

public class PacienteGatewayImpl implements PacienteGateway {

	private static final String PACIENTE_DNI = "SELECT * from PACIENTE where DNI = ?";
	private static final String HISTORIAL_DNI = "SELECT * from HISTORIAL where DNIPACIENTE = ?";
	private static final String VACUNAR = "INSERT INTO VACUNA values (?,?,?,?,?,?,?,?)";
	private static final String FIND_VACUNAS_REALIZADAS = "SELECT * FROM VACUNA WHERE IDHISTORIAL = ?";

	@Override
	public void add(PacienteRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PacienteRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PacienteRecord> findById(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(PACIENTE_DNI);
			pst.setString(1, dni);

			pst.execute();
			
			rs = pst.executeQuery();
			
			return RecordAssembler.toPacienteRecord(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private static String findAll = "SELECT * FROM PUBLIC.PACIENTE";
	@Override
	public List<PacienteRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toPacienteList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public HistorialRecord getHistorial(String dniPaciente) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(HISTORIAL_DNI);
			pst.setString(1, dniPaciente);

			pst.execute();
			
			rs = pst.executeQuery();
			
			return RecordAssembler.toHistorialRecord(rs).get();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void vacunar(VacunaRecord vacuna) {
		Connection c = null;
		PreparedStatement pst = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(VACUNAR);
			pst.setInt(1, vacuna.getIdVacuna());
			pst.setInt(2, vacuna.getIdHistorial());
			pst.setString(3, vacuna.getIdCita());
			pst.setDate(4, Date.valueOf(vacuna.getFechaReal()));
			pst.setDate(5, Date.valueOf(vacuna.getFechaAproximada()));
			pst.setTime(6, Time.valueOf(vacuna.getHora()));
			pst.setString(7, vacuna.getDosis());
			pst.setBoolean(8, vacuna.isRefuerzo());

			pst.execute();
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(pst);
		}
	}
	
	@Override
	public List<VacunaRecord> getVacunas(int idHistorial) { 
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(FIND_VACUNAS_REALIZADAS);
			pst.setInt(1, idHistorial);
			
			rs = pst.executeQuery();
			
			return RecordAssembler.toVacunaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
