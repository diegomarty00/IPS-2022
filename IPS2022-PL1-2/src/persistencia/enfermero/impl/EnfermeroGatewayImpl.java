package persistencia.enfermero.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.enfermero.EnfermeroGateway;
import persistencia.enfermero.EnfermeroRecord;
import util.jdbc.Jdbc;

public class EnfermeroGatewayImpl implements EnfermeroGateway {

	@Override
	public void add(EnfermeroRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EnfermeroRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<EnfermeroRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private String findAll ="select * from PUBLIC.ENFERMERO";
	@Override
	public List<EnfermeroRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toEnfermeroList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
