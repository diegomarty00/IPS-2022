package persistencia.especialidad.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.especialidad.EspecialidadCitaGateway;
import persistencia.especialidad.EspecialidadCitaRecord;

import util.jdbc.Jdbc;

public class EspecialidadCitaGatewayImpl implements EspecialidadCitaGateway {

	private static String ADD_CITA= "INSERT INTO ESPECIALIDADCITA values (?,?,?)";

	@Override
	public void add(EspecialidadCitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(ADD_CITA);
			pst.setString(1,  t.idCita);
			pst.setString(2, t.idEspecialidad);
			pst.setInt(3, t.nMedicos);
			
			

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
	public void update(EspecialidadCitaRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<EspecialidadCitaRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EspecialidadCitaRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static String REMOVEEC= "DELETE* FROM ESPECIALIDADCITA WHERE IDCITA = ?";

	@Override
	public void removeEspecialidadC(String idCita) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(REMOVEEC);
			pst.setString(1,  idCita);
			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	

}
