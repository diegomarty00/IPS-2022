package gui.citas;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.enfermero.EnfermeroRecord;
import persistencia.medico.MedicoRecord;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaCalendarioVacunacion extends JFrame {

	private HistorialRecord historial;
	private PacienteRecord paciente;
	private CitaRecord cita;
	MedicoRecord medico;
	EnfermeroRecord enfermero;
	
	private JPanel contentPane;
	private JLabel lblTituloVentana;
	private JScrollPane scrollPaneCalendario;
	private JList<VacunaRecord> listCalendario;
	private JButton btnVacunar;
	private JButton btnCerrarCalendario;

	
	/**
	 * Create the frame.
	 */
	public VentanaCalendarioVacunacion(PacienteRecord paciente, CitaRecord cita, MedicoRecord medico, EnfermeroRecord enfermero) {
		setResizable(false);
		
		this.historial=PersistenceFactory.forPaciente().getHistorial(paciente.getId());
		this.paciente=paciente;
		this.cita=cita;
		this.medico=medico;
		this.enfermero=enfermero;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTituloVentana());
		contentPane.add(getScrollPaneCalendario());
		contentPane.add(getBtnVacunar());
		contentPane.add(getBtnCerrarCalendario());
	}
	private JLabel getLblTituloVentana() {
		if (lblTituloVentana == null) {
			lblTituloVentana = new JLabel("Calendario vacunacion "+paciente.getNombre()+" "+paciente.getApellidos());
			lblTituloVentana.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTituloVentana.setBounds(80, 21, 448, 45);
		}
		return lblTituloVentana;
	}
	private JScrollPane getScrollPaneCalendario() {
		if (scrollPaneCalendario == null) {
			scrollPaneCalendario = new JScrollPane();
			scrollPaneCalendario.setBounds(40, 117, 275, 261);
			scrollPaneCalendario.setViewportView(getListCalendario());
		}
		return scrollPaneCalendario;
	}
	private JList<VacunaRecord> getListCalendario() {
		if (listCalendario == null) {
			DefaultListModel<VacunaRecord> modelo = new DefaultListModel<VacunaRecord>();
			modelo.addAll(historial.getCalendarioVacunas());
			listCalendario = new JList<VacunaRecord>(modelo);
			listCalendario.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					vacunar(true);
				}
			});
			listCalendario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listCalendario;
	}
	private JButton getBtnVacunar() {
		if (btnVacunar == null) {
			btnVacunar = new JButton("Vacunar");
			btnVacunar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vacunar(false);
				}
			});
			btnVacunar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnVacunar.setBounds(226, 389, 89, 23);
		}
		return btnVacunar;
	}
	private void vacunar(boolean itemSelected) {
		VacunaRecord vacuna = null;
		if (itemSelected) {
			vacuna = getListCalendario().getSelectedValue();
		}
		
		VentanaVacunacion v = new VentanaVacunacion(paciente, cita, vacuna,medico,enfermero); 
		v.setVisible(true);
		dispose();
	}
	
	private JButton getBtnCerrarCalendario() {
		if (btnCerrarCalendario == null) {
			btnCerrarCalendario = new JButton("Cerrar calendario");
			btnCerrarCalendario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
			btnCerrarCalendario.setBounds(426, 436, 155, 31);
		}
		return btnCerrarCalendario;
	}
	
	private void cerrar() {
		if (cita==null) {
			VentanaHistorial v = new VentanaHistorial(paciente, medico, enfermero);
			v.setVisible(true);
		} else {
			VentanaCita v = new VentanaCita(cita, medico, enfermero);
			v.setVisible(true);
		}
		this.dispose();
	}
}
