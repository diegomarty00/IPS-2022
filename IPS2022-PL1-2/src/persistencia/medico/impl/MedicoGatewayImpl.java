package persistencia.medico.impl;

import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import util.jdbc.Jdbc;

import persistencia.medico.MedicoGateway;
import persistencia.medico.MedicoRecord;

public class MedicoGatewayImpl implements MedicoGateway {

	@Override
	public void add(MedicoRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MedicoRecord t) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public Optional<MedicoRecord> findById(String id) {
		return null;
		
	}

	private String findAll ="select * from PUBLIC.MEDICOS";
	@Override
	public List<MedicoRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toMedicoList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
