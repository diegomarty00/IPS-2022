package persistencia.enfermero.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.enfermero.EnfermeroCitaGateway;
import persistencia.enfermero.EnfermeroCitaRecord;
import util.jdbc.Jdbc;

public class EnfermeroCitaGatewayImpl implements EnfermeroCitaGateway {

	private static String ADD= "INSERT INTO ENFERMEROCITA values (?,?,?)";
	@Override
	public void add(EnfermeroCitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(ADD);
			pst.setString(1,  t.idCita);
			pst.setInt(2, t.idEnfermero);
			pst.setInt(3, t.nEnfermeros);
			
			

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
	public void update(EnfermeroCitaRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<EnfermeroCitaRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnfermeroCitaRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
