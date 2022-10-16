package persistencia.cita.impl;

import java.sql.Connection;
import java.sql.Date;
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
	private static final String CITAS_DEL_DIA = "SELECT * from CITA where FECHA = ?";
	private static String ASIGNAR_ENTRADA = "update CITA set HORA_ENTRADA_REAL = ? where idcita = ?";
	private static String ASIGNAR_SALIDA = "update CITA set HORA_SALIDA_REAL = ? where idcita = ?";
	private static String PACIENTE_ACUDIDO = "update CITA set PACIENTE_ACUDIDO = 1 where idcita = ?";
	private static String ADD_CITA= "INSERT INTO Cita values (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	@Override
	public void add(CitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(ADD_CITA);
			pst.setString(1,  t.idCita);
			pst.setNString(2, t.dniPaciente);
			pst.setBoolean(3, t.urgente);
			if(t.horaEntradaEstimada != null ) {
				pst.setTime(4,Time.valueOf(t.horaEntradaEstimada));
				
			}else {
				pst.setTime(4,null);
			}
			if (t.horaSalidaEstimada != null) {
				pst.setTime(5, Time.valueOf(t.horaSalidaEstimada));
			}else {
				pst.setTime(5, null);
			}
			
			
			pst.setBoolean(6, t.pacienteAcudido);
			pst.setTime(7,null);
			pst.setTime(8, null);
			if(t.fecha != null ) {
				pst.setDate(9, Date.valueOf(t.fecha));
			}else {
				pst.setDate(9, null);
			}
			
			pst.setString(10, t.correoPaciente);
			pst.setInt(11,Integer.parseInt(t.telefonoPaciente));
			pst.setString(12, t.lugar);

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

	private static String findAll = "SELECT * FROM PUBLIC.cita";
	@Override
	public List<CitaRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toCitaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
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

	@Override
	public List<CitaRecord> getCitasDelDia(Date dia) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(CITAS_DEL_DIA);
			pst.setDate(1,  dia);

			pst.execute();
			
			rs = pst.executeQuery();
			
			return RecordAssembler.toCitaList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
