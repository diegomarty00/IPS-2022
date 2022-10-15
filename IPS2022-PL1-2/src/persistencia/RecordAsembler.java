package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.medico.MedicoRecord;

public class RecordAsembler {

	public static MedicoRecord rsToMedico(ResultSet r) throws SQLException {
		MedicoRecord med = new MedicoRecord();
		med.idMedico = r.getInt("idmedico");
		med.nombre = r.getString("nombre");
		med.apellidos = r.getString("apellidos");
		med.correo = r.getString("correo");
		
		return med;
	}
	
	public static List<MedicoRecord> toMedicoList(ResultSet r) throws SQLException{
		List<MedicoRecord> list = new ArrayList<>();
		while(r.next()) {
			list.add(rsToMedico(r));
		}
		return list ;
	}
}
