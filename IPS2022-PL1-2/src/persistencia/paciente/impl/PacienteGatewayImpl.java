package persistencia.paciente.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteGateway;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.jdbc.Jdbc;

public class PacienteGatewayImpl implements PacienteGateway {

    private static final String PACIENTE_ID = "SELECT * from PACIENTE where ID = ?";
    private static final String HISTORIAL_DNI = "SELECT * from HISTORIAL where ID = ?";
    private static final String VACUNAR = "INSERT INTO VACUNA values (?,?,?,?,?,?,?,?)";
    private static final String FIND_VACUNAS_REALIZADAS = "SELECT * FROM VACUNA WHERE IDHISTORIAL = ? ORDER BY FECHAAPROXIMADA ASC";
    private static final String CHECK_IF_EXISTS_VACUNA = "SELECT * FROM VACUNA WHERE IDVACUNA = ?";
    private static final String UPDATE_VACUNA = "UPDATE VACUNA SET IDCITA = ?, FECHAREAL = ?, HORA = ?, DOSIS = ?, REFUERZO = ? WHERE IDVACUNA = ?";

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
    public Optional<PacienteRecord> findById(String id) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getConnection();

	    pst = c.prepareStatement(PACIENTE_ID);
	    pst.setInt(1, Integer.parseInt(id));

	    pst.execute();

	    rs = pst.executeQuery();

	    return RecordAssembler.toPacienteRecord(rs);
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    private static String findAll = "SELECT * FROM PUBLIC.PACIENTE";

    @Override
    public List<PacienteRecord> findAll() {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getConnection();

	    pst = c.prepareStatement(findAll);
	    rs = pst.executeQuery();

	    return RecordAssembler.toPacienteList(rs);

	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public HistorialRecord getHistorial(int idPaciente) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getConnection();

	    pst = c.prepareStatement(HISTORIAL_DNI);
	    pst.setInt(1, idPaciente);

	    pst.execute();

	    rs = pst.executeQuery();

	    return RecordAssembler.toHistorialRecord(rs).get();
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public void vacunar(VacunaRecord vacuna) {
	Connection c = null;
	PreparedStatement pst = null;

	try {
	    c = Jdbc.getConnection();

	    if (existsVacuna(vacuna.getIdVacuna())) {
		updateVacuna(vacuna);
		return;
	    }

	    pst = c.prepareStatement(VACUNAR);
	    pst.setInt(1, vacuna.getIdVacuna());
	    pst.setInt(2, vacuna.getIdHistorial());
	    pst.setString(3, vacuna.getIdCita());
	    pst.setDate(4, Date.valueOf(vacuna.getFechaReal()));
	    pst.setDate(5, Date.valueOf(vacuna.getFechaAproximada()));
	    pst.setTime(6, Time.valueOf(vacuna.getHora()));
	    pst.setString(7, vacuna.getDosis());
	    pst.setBoolean(8, vacuna.isRefuerzo());

	    pst.execute();

	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(pst);
	}
    }

    private void updateVacuna(VacunaRecord vacuna) {
	Connection c = null;
	PreparedStatement pst = null;

	try {
	    c = Jdbc.getConnection();

	    pst = c.prepareStatement(UPDATE_VACUNA);

	    pst.setString(1, vacuna.getIdCita());
	    pst.setDate(2, Date.valueOf(vacuna.getFechaReal()));
	    pst.setTime(3, Time.valueOf(vacuna.getHora()));
	    pst.setString(4, vacuna.getDosis());
	    pst.setBoolean(5, vacuna.isRefuerzo());
	    pst.setInt(6, vacuna.getIdVacuna());

	    pst.execute();

	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(pst);
	}
    }

    private boolean existsVacuna(int idVacuna) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getConnection();

	    pst = c.prepareStatement(CHECK_IF_EXISTS_VACUNA);
	    pst.setInt(1, idVacuna);

	    rs = pst.executeQuery();

	    return rs.isBeforeFirst();

	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public List<VacunaRecord> getVacunas(int idHistorial) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getConnection();

	    pst = c.prepareStatement(FIND_VACUNAS_REALIZADAS);
	    pst.setInt(1, idHistorial);

	    rs = pst.executeQuery();

	    return RecordAssembler.toVacunaList(rs);

	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}

    }

}
