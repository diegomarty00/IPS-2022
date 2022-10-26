package business.cita.operaciones;


import persistencia.paciente.PacienteRecord;
import persistencia.paciente.impl.PacienteGatewayImpl;

public class ObtenerPacientes {

	public static PacienteRecord sacarDatosContacto(String dni){
		PacienteGatewayImpl pa = new PacienteGatewayImpl();
		PacienteRecord p = pa.findById(dni).get();
		
		return p ; 
	}
	
	public static String parsePaciente(String pa) {
		String dni = "";
		String[] partes = pa.split(" ");
		dni = partes[partes.length -1 ];
		return dni ;
	}
}
