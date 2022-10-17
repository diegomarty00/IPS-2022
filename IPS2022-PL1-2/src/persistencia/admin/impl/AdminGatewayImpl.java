package persistencia.admin.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.admin.AdminGateway;
import persistencia.admin.JornadaRecord;
import persistencia.admin.MedicoRecord;
import util.jdbc.Jdbc;

public class AdminGatewayImpl implements AdminGateway {

    private static final String ALL_MEDICOS = "SELECT * from MEDICO";
    private static final String A�ADIR_JORNADAS = "insert into JORNADA values (?, ?, ?, ?, ?)";
    private static final String CONTAR_JORNADAS = "SELECT count(*) from JORNADA";

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

    @Override
    public List<MedicoRecord> findAll() {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ArrayList<MedicoRecord> medicos = new ArrayList<>();
	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(ALL_MEDICOS);

	    rs = pst.executeQuery();
	    while (rs.next()) {
		MedicoRecord medico = new MedicoRecord();
		medico.nombre = rs.getString("nombre");
		medico.apellidos = rs.getString("apellidos");
		medico.id = rs.getInt("idmedico");
		medico.correo = rs.getString("correo");
		medicos.add(medico);
	    }
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
	return medicos;
    }

    @Override
    public void a�adirJornadas(JornadaRecord jornada) {

	Connection c = null;
	PreparedStatement pst = null;
	PreparedStatement pst_count = null;
	ResultSet rs = null;
	try {
	    System.out.println(jornada);
	    c = Jdbc.getCurrentConnection();
	    pst_count = c.prepareStatement(CONTAR_JORNADAS);

	    rs = pst_count.executeQuery();
	    pst = c.prepareStatement(A�ADIR_JORNADAS);
	    rs.next();
	    pst.setInt(1, rs.getInt(1));
	    pst.setInt(2, jornada.idMedico);
	    pst.setString(3, jornada.dia);
	    pst.setTimestamp(4, jornada.inicio);
	    pst.setTimestamp(5, jornada.fin);
	    pst.executeUpdate();
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(rs, pst_count);
	}

    }

    @Override
    public void asignarInformacionContactoCitas() {
	// TODO Auto-generated method stub

    }

}