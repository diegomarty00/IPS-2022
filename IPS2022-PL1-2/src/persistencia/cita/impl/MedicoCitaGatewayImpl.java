package persistencia.cita.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.cita.MedicoCitaGateway;
import persistencia.cita.MedicoCitaRecord;
import util.jdbc.Jdbc;

public class MedicoCitaGatewayImpl implements MedicoCitaGateway {

	private static String ADD_CITA= "INSERT INTO MEDICOCITA values (?,?)";
	@Override
	public void add(MedicoCitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(ADD_CITA);
			pst.setString(1,  t.idCita);
			pst.setInt(2, t.idMedico);
			
			

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
	public void update(MedicoCitaRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<MedicoCitaRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private String findAll ="select * from PUBLIC.MEDICOCITA";
	@Override
	public List<MedicoCitaRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toMedicoCitaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
