package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.admin.JornadaRecord;

public class JornadaAssembler {

    public static List<JornadaRecord> toJornadaList(ResultSet rs)
	    throws SQLException {
	List<JornadaRecord> list = new ArrayList<>();
	while (rs.next()) {
	    list.add(rsToJornada(rs));
	}
	return list;
    }

    public static JornadaRecord rsToJornada(ResultSet rs) throws SQLException {
	JornadaRecord jornada = new JornadaRecord();
	jornada.idMedico = rs.getInt("idmedico");
	jornada.idJornada = rs.getInt("idJornada");
	jornada.dia = rs.getString("dia");
	jornada.inicio = rs.getTimestamp("inicio");
	jornada.fin = rs.getTimestamp("fin");

	return jornada;
    }

}
