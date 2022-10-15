package persistencia.pacientes.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAsembler;
import persistencia.pacientes.PacienteGateway;
import persistencia.pacientes.PacientesRecord;
import util.jdbc.Jdbc;

public class PacientesGatewayImpl implements PacienteGateway{

	@Override
	public void add(PacientesRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PacientesRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PacientesRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private String findAll ="select * from PUBLIC.PACIENTE";
	@Override
	public List<PacientesRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAsembler.toPacienteList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
