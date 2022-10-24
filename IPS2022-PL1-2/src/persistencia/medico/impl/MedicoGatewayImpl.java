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

	private static final String FIN_BY_MEDICO_ID = "SELECT * from MEDICO where IDMEDICO = ?";
	@Override
	public Optional<MedicoRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(FIN_BY_MEDICO_ID);
			pst.setInt(1,Integer.parseInt(id) );

			rs = pst.executeQuery();
			
			return RecordAssembler.rsToMedicoO(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	private String findAll ="select * from PUBLIC.MEDICO";
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
