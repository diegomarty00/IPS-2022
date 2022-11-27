package persistencia.cita.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAssembler;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaGateway;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
import persistencia.paciente.VacunaRecord;
import util.jdbc.Jdbc;

public class CitaGatewayImpl implements CitaGateway {

    private static final String FIN_BY_CITA_ID = "SELECT * from CITA where IDCITA = ? AND CONFIRMADA = true";
    private static final String PROXIMAS_CITAS = "SELECT * from CITA where fecha >= ? AND CONFIRMADA = true";
    private static final String CITAS_DEL_DIA = "SELECT * from CITA where FECHA = ? AND CONFIRMADA = true";
	private static final String FIND_CAUSAS_FROM_CITA = "SELECT * from CAUSA where IDCITA = ? ORDER BY FECHA_ASIGNACION ASC";
	private static final String FIND_PRESCRIPCIONES_FROM_CITA = "SELECT * from PRESCRIPCION where IDCITA = ? ORDER BY FECHA_ASIGNACION ASC";
	private static final String FIN_BY_HISTORIAL_ID = "SELECT * FROM CITA WHERE IDHISTORIAL = ?";
	private static final String FIND_VACUNAS_FROM_CITA = "SELECT * FROM VACUNA WHERE IDCITA = ? ORDER BY FECHAAPROXIMADA ASC";

    private static String ASIGNAR_ENTRADA = "update CITA set HORA_ENTRADA_REAL = ? where idcita = ?";
    private static String ASIGNAR_NUEVO_HORARIO = "update CITA set HORA_ENTRADA_ESTIMADA = ? , HORA_SALIDA_ESTIMADA = ?  where idcita = ?";
    private static String ASIGNAR_SALIDA = "update CITA set HORA_SALIDA_REAL = ? where idcita = ?";
    private static String PACIENTE_ACUDIDO = "update CITA set PACIENTE_ACUDIDO = ? where idcita = ?";
    private static String ADD_CAUSA= "INSERT INTO Causa values (?,?,?,?,?)";
    private static String DELETE_CAUSA= "DELETE FROM Causa WHERE IDCAUSA = ?";
    private static String ADD_PRESCRIPCION = "INSERT INTO PRESCRIPCION values (?,?,?,?,?,?,?,?,?,?)";
    private static String DELETE_PRESCRIPCION = "DELETE FROM PRESCRIPCION WHERE IDPRESCRIPCION = ?";
    private static String ADD_CITA= "INSERT INTO Cita values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String MODIFICAR_CONTACTO = "update CITA set CORREO_PACIENTE = ? , TELEFONO_PACIENTE = ? where idcita = ?";
    
    
	
    
    
	@Override
	public void add(CitaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(ADD_CITA);
			pst.setString(1,  t.idCita);
			pst.setNString(2, t.dniPaciente);
			pst.setInt(3, t.idHistorial);
			pst.setBoolean(4, t.urgente);
			if(t.horaEntradaEstimada != null ) {
				pst.setTime(5,Time.valueOf(t.horaEntradaEstimada));
				
			}else {
				pst.setTime(5,null);
			}
			if (t.horaSalidaEstimada != null) {
				pst.setTime(6, Time.valueOf(t.horaSalidaEstimada));
			}else {
				pst.setTime(6, null);
			}
			
			
			pst.setString(7, t.pacienteAcudido);
			pst.setTime(8,null);
			pst.setTime(9, null);
			if(t.fecha != null ) {
				pst.setDate(10, Date.valueOf(t.fecha));
			}else {
				pst.setDate(10, null);
			}
		  
			pst.setString(11, t.correoPaciente);
			pst.setInt(12,Integer.parseInt(t.telefonoPaciente));
			pst.setString(13, t.lugar);
			pst.setString(14, t.otros);
			pst.setBoolean(15, t.prioritario);
			pst.setBoolean(16, t.confirmada);

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

    private static String findAllNc = "SELECT * FROM PUBLIC.cita WHERE CONFIRMADA = false";
	@Override
	public List<CitaRecord> findAllNc() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAllNc);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toCitaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
    
    private static String findAll = "SELECT * FROM PUBLIC.cita WHERE CONFIRMADA = true";
	@Override
	public List<CitaRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAssembler.toCitaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

    @Override
    public Optional<CitaRecord> findById(String id) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.createThreadConnection();

	    pst = c.prepareStatement(FIN_BY_CITA_ID);
	    pst.setString(1, id);

	    rs = pst.executeQuery();

	    return RecordAssembler.toCitaRecord(rs);
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }
    
    @Override
    public List<CitaRecord> findByHistorialId(int idHistorial) {
    	Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(FIN_BY_HISTORIAL_ID);
			pst.setInt(1, idHistorial);
			
			rs = pst.executeQuery();			

			return RecordAssembler.toCitaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

    }
    
    @Override
    public void asignarHoraEntrada(String idCita, LocalTime horaEntrada) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(ASIGNAR_ENTRADA);
			pst.setString(2, idCita);
			pst.setTime(1, Time.valueOf(horaEntrada));

			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
    }

    @Override
    public void asignarHoraSalida(String idCita, LocalTime horaSalida) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(ASIGNAR_SALIDA);
	    pst.setString(2, idCita);
	    pst.setTime(1, Time.valueOf(horaSalida));

	    pst.execute();
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public void setPacienteAcudido(String idCita, String estadoAsistencia) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(PACIENTE_ACUDIDO);
	    pst.setString(1, estadoAsistencia);
	    pst.setString(2, idCita);

	    pst.execute();
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public List<CitaRecord> getCitasDelDia(Date dia) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(CITAS_DEL_DIA);
	    pst.setDate(1, dia);

	    pst.execute();

	    rs = pst.executeQuery();

	    return RecordAssembler.toCitaList(rs);
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public List<CitaRecord> getCitasProximas(Date dia) {
	Connection c = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(PROXIMAS_CITAS);
	    pst.setDate(1, dia);

	    pst.execute();

	    rs = pst.executeQuery();

	    return RecordAssembler.toCitaList(rs);
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
    }

    @Override
    public void modificarCita(String idCita, String correo, int telefono) {
	Connection c = null;
	PreparedStatement pst = null;

	try {
	    c = Jdbc.getCurrentConnection();

	    pst = c.prepareStatement(MODIFICAR_CONTACTO);
	    pst.setString(3, idCita);
	    pst.setString(1, correo);
	    pst.setInt(2, telefono);

	    pst.execute();
	} catch (SQLException e) {
	    throw new PersistenceException(e);
	} finally {
	    Jdbc.close(pst);
	}
    }

	@Override
	public void update(CitaRecord t) {
		// TODO Auto-generated method stub
		
	}

@Override
	public void modificarHorario(String idCita, LocalTime nHoraEntrada, LocalTime nHoraSalida) {
		Connection c = null;
		PreparedStatement pst = null;
    try {
		    c = Jdbc.createThreadConnection();
		    pst = c.prepareStatement(ASIGNAR_NUEVO_HORARIO);
		    pst.setString(3, idCita);
		    pst.setTime(1,Time.valueOf(nHoraEntrada));
		    pst.setTime(2, Time.valueOf(nHoraSalida));
		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(pst);
    }
}



	private static String MODIFICAR_CITA = "update CITA set DNIPACIENTE = ? , URGENTE = ?,HORA_ENTRADA_ESTIMADA = ?,"
		+ " HORA_SALIDA_ESTIMADA = ?,FECHA = ?,LUGAR_CITA = ?,PRIORITARIO = ?   where idcita = ?";
	@Override
	public void ModificarTodo(CitaRecord cit) {
		Connection c = null;
		PreparedStatement pst = null;
    try {
		    c = Jdbc.createThreadConnection();
		    pst = c.prepareStatement(MODIFICAR_CITA);
		    pst.setString(8, cit.idCita);
		    pst.setString(1,cit.dniPaciente);
		    pst.setBoolean(2, cit.urgente);
		    pst.setTime(3, Time.valueOf(cit.horaEntradaEstimada));
		    pst.setTime(4, Time.valueOf(cit.horaSalidaEstimada));
		    pst.setDate(5, Date.valueOf(cit.fecha));
		    pst.setString(6, cit.lugar);
		    pst.setBoolean(7, cit.prioritario);
		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(pst);
    }
	}
	
	@Override
	public void insertarCausa(String idCita, String titulo, Time hora, Date fecha) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(ADD_CAUSA);
			pst.setInt(1, getLastId("CAUSA", "IDCAUSA")+1);
		    pst.setString(2, titulo);
		    pst.setTime(3, hora);
		    pst.setDate(4, fecha);
		    pst.setString(5, idCita);

		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}

	@Override
	public int getLastId(String tabla, String columnName) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

    try {
		    c = Jdbc.createThreadConnection();

		    pst = c.prepareStatement("SELECT NVL(MAX("+columnName+"), 0) FROM "+tabla);

		    rs = pst.executeQuery();
		    rs.next();
		    return rs.getInt("C1");
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<CausaRecord> getCausas(String idCita) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

    try {
		    c = Jdbc.createThreadConnection();

		    pst = c.prepareStatement(FIND_CAUSAS_FROM_CITA);
		    pst.setString(1, idCita);

		    rs = pst.executeQuery();

		    return RecordAssembler.toCausaList(rs);
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<PrescripcionRecord> getPrescripciones(String idCita) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.createThreadConnection();
			
			pst = c.prepareStatement(FIND_PRESCRIPCIONES_FROM_CITA);
			pst.setString(1, idCita);
			
			rs = pst.executeQuery();
			
			return RecordAssembler.toPrescripcionList(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void removeCausa(int idCausa) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(DELETE_CAUSA);
		    pst.setInt(1, idCausa);
		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}

	@Override
	public void insertarPrescripcion(PrescripcionRecord presc) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(ADD_PRESCRIPCION);
			pst.setInt(1, getLastId("PRESCRIPCION", "IDPRESCRIPCION")+1);
		    pst.setString(2, presc.getTitulo());
		    pst.setString(3, presc.getTipo());
		    pst.setString(4, presc.getCantidad());
		    pst.setString(5, presc.getIntervaloDosis());
		    pst.setString(6, presc.getDuracion());
		    pst.setString(7, presc.getObservaciones());
		    pst.setTime(8, Time.valueOf(presc.getHoraAsignacion()));
		    pst.setDate(9, Date.valueOf(presc.getFechaAsignacion()));
		    pst.setString(10, presc.getIdCita());

		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}

	@Override
	public void deletePrescripcion(PrescripcionRecord presc) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
		    c = Jdbc.getCurrentConnection();

		    pst = c.prepareStatement(DELETE_PRESCRIPCION);
		    pst.setInt(1, presc.getIdPrescricpion());
		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}
	
	private static String REMOVEC= "DELETE FROM CITA WHERE IDCITA = ?";

	@Override
	public void removeCita(String idCita) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(REMOVEC);
			pst.setString(1,  idCita);
			pst.execute();
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<VacunaRecord> getVacunas(String idCita) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

    try {
		    c = Jdbc.createThreadConnection();

		    pst = c.prepareStatement(FIND_VACUNAS_FROM_CITA);
		    pst.setString(1, idCita);

		    rs = pst.executeQuery();

		    return RecordAssembler.toVacunaList(rs);
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(rs, pst);
		}
	}
	
	private static String CONFIRMAR_CITA = "update CITA set CONFIRMADA = TRUE   where idcita = ?";

	@Override
	public void ConfirCita(String idCita) {
		// TODO Auto-generated method stub
		Connection c = null;
		PreparedStatement pst = null;
    try {
		    c = Jdbc.createThreadConnection();
		    pst = c.prepareStatement(CONFIRMAR_CITA);
		    pst.setString(1,idCita);
		    pst.execute();
		} catch (SQLException e) {
		    throw new PersistenceException(e);
		} finally {
		    Jdbc.close(pst);
    }
	}
}
