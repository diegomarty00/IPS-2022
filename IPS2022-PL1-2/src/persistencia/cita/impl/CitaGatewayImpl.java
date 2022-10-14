package persistencia.cita.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.cita.CitaGateway;
import persistencia.cita.CitaRecord;
import util.jdbc.Jdbc;

public class CitaGatewayImpl implements CitaGateway {

	private static final String FIN_BY_CITA_ID = "SELECT * from CITA where IDCITA = ?";
	private static String ASIGNAR_ENTRADA = "update CITA set HORA_ENTRADA_REAL = ? where idcita = ?";
	private static String ASIGNAR_SALIDA = "update CITA set HORA_SALIDA_REAL = ? where idcita = ?";
	private static String PACIENTE_ACUDIDO = "update CITA set PACIENTE_ACUDIDO = 1 where idcita = ?";
	private static String ADD_CITA= "INSERT INTO Cita";
	
	@Override
	public void add(CitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(ADD_CITA);
//			pst.setInt(1,  t.id_competicion);
//			pst.setString(2, t.nombre);
//			pst.setDate(3, Date.valueOf( t.fecha));
//			pst.setString(4, t.tipo);
//			pst.setInt(5, t.distancia);
//			pst.setDouble(6, t.cuota);
//			pst.setInt(7, t.numero_tramos);
//
//			pst.setDate(8, Date.valueOf( t.fecha_fin_inscripcion));
//			pst.setInt(9, t.plazas);
//			
//			pst.setDate(10, Date.valueOf( t.fecha_inicio_inscripcion));
//			pst.setInt(11, t.dorsalesReservados);

			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CitaRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CitaRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CitaRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(FIN_BY_CITA_ID);
			pst.setString(1,  id);

			rs = pst.executeQuery();
			
			return RecordAssembler.toCitaRecord(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void asignarHoraEntrada(String idCita, LocalTime horaEntrada) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(ASIGNAR_ENTRADA);
			pst.setString(2,  idCita);
			pst.setTime(1, Time.valueOf(horaEntrada));

			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void asignarHoraSalida(String idCita, LocalTime horaSalida) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(ASIGNAR_SALIDA);
			pst.setString(2,  idCita);
			pst.setTime(1, Time.valueOf(horaSalida));

			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void setPacienteAcudido(String idCita) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(PACIENTE_ACUDIDO);
			pst.setString(1,  idCita);

			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	


}
