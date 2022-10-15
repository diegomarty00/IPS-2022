package persistencia.cita.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAsembler;
import persistencia.cita.CitaGateway;
import persistencia.cita.CitaRecord;
import util.jdbc.Jdbc;

public class CitaGatewayImpl implements CitaGateway {

	private static String ADD_CITA= "INSERT INTO Cita";
	private static final String FIN_BY_CITA_ID = "SELECT * from CITA where IDCITA = ?";
	
	@Override
	public void add(CitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(ADD_CITA);
			pst.setString(1,  t.idCita);
			pst.setNString(2, t.dniPaciente);
			pst.setBoolean(3, t.urgente);
			pst.setDate(4,Date.valueOf(t.horaEntradaEstimada));
			

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
			
			return RecordAsembler.toCitaRecord(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}


}
