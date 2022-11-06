package persistencia.especialidad.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.admin.MedicoRecord;
import persistencia.especialidad.EspecialidadGateway;
import persistencia.especialidad.EspecialidadRecord;
import util.jdbc.Jdbc;

public class EspecialidadGatewayImpl implements EspecialidadGateway {

	private static final String ALL_ESPECIALIDAD = "SELECT * from ESPECIALIDAD";
	
	@Override
	public void add(EspecialidadRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EspecialidadRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<EspecialidadRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EspecialidadRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<EspecialidadRecord> especialidades = new ArrayList<>();
		try {
		    c = Jdbc.getConnection();

		    pst = c.prepareStatement(ALL_ESPECIALIDAD);

		    rs = pst.executeQuery();
		    while (rs.next()) {
			EspecialidadRecord es = new EspecialidadRecord();
			es.idEspecialidad = rs.getString("idespecialidad");
			
			especialidades.add(es);
		    }
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
		return especialidades;
	    }
	

}
