package persistencia.medico.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.PersistenceFactory;
import persistencia.RecordAssembler;
import persistencia.medico.MedicoGateway;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.VacunaRecord;
import util.jdbc.Jdbc;

public class MedicoGatewayImpl implements MedicoGateway {

    private static final String CREAR_VACUNA = "insert into VACUNA values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CONTAR_VACUNA = "SELECT count(*) from VACUNA";
    private static final String BUSCAR_HISTORIAL = "SELECT * from historial where idpaciente = ?";

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
	    pst.setInt(1, Integer.parseInt(id));

	    rs = pst.executeQuery();

	    return RecordAssembler.rsToMedicoO(rs);
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}

    }

    private String findAll = "select * from PUBLIC.MEDICO";

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

    @Override
    public void crearVacuna(VacunaRecord vacuna) {
	Connection c = null;
	PreparedStatement pst = null;
	PreparedStatement pst_count = null;
	ResultSet rs = null;
	String resultado = "";
	try {
	    System.out.println(vacuna);
	    c = Jdbc.getCurrentConnection();
	    pst_count = c.prepareStatement(CONTAR_VACUNA);

	    rs = pst_count.executeQuery();

	    pst = c.prepareStatement(CREAR_VACUNA);

	    rs.next();
	    pst.setInt(1,
		    PersistenceFactory.forCita().getLastId("VACUNA", "idvacuna")
			    + 1);
	    pst.setInt(2, vacuna.getIdHistorial());
	    pst.setString(3, null);
	    pst.setDate(4, null);
	    pst.setDate(5, Date.valueOf(vacuna.getFechaAproximada()));
	    pst.setTime(6, null);
	    pst.setString(7, vacuna.getDosis());
	    pst.setBoolean(8, vacuna.isRefuerzo());
	    pst.executeUpdate();
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(pst);
	    Jdbc.close(rs, pst_count);
	}

    }

    @Override
    public Optional<HistorialRecord> buscarHistorial(int idPaciente) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.createThreadConnection();

	    pst = c.prepareStatement(BUSCAR_HISTORIAL);
	    pst.setInt(1, idPaciente);

	    rs = pst.executeQuery();

	    return RecordAssembler.rsToHistorialO(rs);
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

}
