package gui.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.cita.operaciones.CrearCita;
import business.cita.operaciones.ObtenerPacientes;
import persistencia.paciente.PacienteRecord;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class DatosCita extends JFrame {

	private JPanel contentPane;
	private JLabel lbEmail;
	private JLabel lbTelf;
	private JTextField tfEmail;
	private JTextField tfTelf;
	private JLabel lbOtros;
	private JTextField tfOtros;
	private JButton btCancelar;
	private JButton btConfirmar;
	private String paciente;
	private String lugar ;
	private String anio;
	private String mes;
	private String dia ;
	private String hI;
	private String hF;
	private boolean urgente;
	private boolean prioritario;
	private DefaultListModel<String> modjlist;
	ProcesarAccion p = new ProcesarAccion();
	private PacienteRecord pa;
	/**
	 * Create the frame.
	 */
	public DatosCita(String paciente, boolean urgencia,String lugar, String anio , String mes , String dia, String horaE, String horaS,DefaultListModel<String> modjlist,boolean prioridad) {
		this.paciente = paciente;
		this.urgente = urgencia;
		this.lugar = lugar;
		this.anio = anio;
		this.mes = mes ;
		this.dia = dia ;
		this.hI = horaE;
		this.hF = horaS;
		this.modjlist = modjlist;
		this.prioritario = prioridad;
		pa =  ObtenerPacientes.sacarDatosContacto(ObtenerPacientes.parsePaciente(paciente));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbEmail());
		contentPane.add(getLbTelf());
		contentPane.add(getTfEmail());
		contentPane.add(getTfTelf());
		contentPane.add(getLbOtros());
		contentPane.add(getTfOtros());
		contentPane.add(getBtCancelar());
		contentPane.add(getBtConfirmar());
	}

	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("Correo electronico ");
			lbEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lbEmail.setBounds(29, 81, 114, 24);
		}
		return lbEmail;
	}
	private JLabel getLbTelf() {
		if (lbTelf == null) {
			lbTelf = new JLabel("Telefono");
			lbTelf.setFont(new Font("Times New Roman", Font.BOLD, 12));
			lbTelf.setBounds(29, 132, 114, 24);
		}
		return lbTelf;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setBounds(191, 83, 219, 20);
			tfEmail.setColumns(10);
			tfEmail.setText(pa.getCorreo());
		}
		return tfEmail;
	}
	private JTextField getTfTelf() {
		if (tfTelf == null) {
			tfTelf = new JTextField();
			tfTelf.setBounds(191, 134, 219, 22);
			tfTelf.setColumns(10);
			tfTelf.setText(String.valueOf(pa.getTelefono()));
		}
		return tfTelf;
	}
	private JLabel getLbOtros() {
		if (lbOtros == null) {
			lbOtros = new JLabel("Otros");
			lbOtros.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lbOtros.setBounds(29, 184, 114, 24);
		}
		return lbOtros;
	}
	private JTextField getTfOtros() {
		if (tfOtros == null) {
			tfOtros = new JTextField();
			tfOtros.setBounds(191, 186, 219, 38);
			tfOtros.setColumns(10);
			tfOtros.setText("");
		}
		return tfOtros;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBounds(177, 238, 89, 23);
		}
		return btCancelar;
	}
	private JButton getBtConfirmar() {
		if (btConfirmar == null) {
			btConfirmar = new JButton("Confimar cita");
			btConfirmar.addActionListener(p);
			btConfirmar.setBounds(276, 238, 148, 23);
		}
		return btConfirmar;
	}
	
	private void delete() {
		dispose();
	}
	
	class ProcesarAccion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			CrearCita cc = new CrearCita();
		    if(!prioritario || !cc.comprovarChoqueCitas(anio,dia,mes ,hI,hF)) {
		    	cc.crearCita(paciente,
					    urgente,
					    lugar,
					    anio,
					    mes,
					    dia,
					    hI,
					    hF,
					    tfEmail.getText(),
					    tfTelf.getText(),tfOtros.getText(),prioritario);
		    	if(modjlist != null) {
			    	int size = modjlist.size();
					for (int i = 0; i < size; i++) {
					    cc.crearCitaMedico(modjlist.get(i));
					}
			    }
		    	delete();
		    }else if(prioritario && !cc.comprovarChoqueCitas(anio,dia,mes ,hI,hF)) {
		    	cc.crearCita(paciente,
					    urgente,
					    lugar,
					    anio,
					    mes,
					    dia,
					    hI,
					    hF,
					    tfEmail.getText(),
					    tfTelf.getText(),tfOtros.getText(),prioritario);
		    	if(modjlist != null) {
			    	int size = modjlist.size();
					for (int i = 0; i < size; i++) {
					    cc.crearCitaMedico(modjlist.get(i));
					}
			    }
		    	delete();
		    }else {
		    	CitaPrioritaria cp = new CitaPrioritaria(cc,ob(),paciente,
					    urgente,
					    lugar,
					    anio,
					    mes,
					    dia,
					    hI,
					    hF,modjlist,
					    tfEmail.getText(),
					    tfTelf.getText(),tfOtros.getText(),prioritario);
		    	cp.setVisible(true);
		    }
		}
	}
	private DatosCita ob() {
		return this;
	}
}
