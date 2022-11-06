package persistencia.medico.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.medico.MedicoEspecialidadGateway;
import persistencia.medico.MedicoEspecialidadRecord;
import util.jdbc.Jdbc;

public class MedicoEspecialidadGatewayImpl implements MedicoEspecialidadGateway {

	private static String ADD_CITA= "INSERT INTO MEDICOESPECIALIDAD values (?,?)";
	
	@Override
	public void add(MedicoEspecialidadRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(ADD_CITA);
			pst.setString(1,  t.idEspecialidad);
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
	public void update(MedicoEspecialidadRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<MedicoEspecialidadRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicoEspecialidadRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
