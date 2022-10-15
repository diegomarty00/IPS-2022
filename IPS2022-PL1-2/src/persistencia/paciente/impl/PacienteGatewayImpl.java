package persistencia.paciente.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.paciente.PacienteGateway;
import persistencia.paciente.PacienteRecord;
import util.jdbc.Jdbc;

public class PacienteGatewayImpl implements PacienteGateway {

	private static final String PACIENTE_DNI = "SELECT * from PACIENTE where DNI = ?";

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
			c = Jdbc.getCurrentConnection();
			
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

	@Override
	public List<PacienteRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}