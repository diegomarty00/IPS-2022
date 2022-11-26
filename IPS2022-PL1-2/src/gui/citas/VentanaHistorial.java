package gui.citas;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaHistorial extends JFrame {

	PacienteRecord paciente;
	HistorialRecord historial;
	
	private JPanel contentPane;
	private JScrollPane scrollPaneCitas;
	private JLabel lblHistorial;
	private JList<CitaRecord> listCitas;
	private JLabel lblCitas;
	private JScrollPane scrollPaneCausas;
	private JLabel lblCausas;
	private JList<CausaRecord> listCausas;
	private JScrollPane scrollPanePrescripciones;
	private JLabel lblPrescricpiones;
	private JList<PrescripcionRecord> listPrescripciones;
	private JScrollPane scrollPaneDiagnosticos;
	private JLabel lblDiagnosticos;
	private JList<HistorialRecord> listDiagnosticos;
	private JScrollPane scrollPanelVacunas;
	private JLabel lblVacunas;
	private JList<VacunaRecord> listVacunas;
	private JButton btnVacunar;
	private JButton btnVerCalendarioVacunacion;

	/**
	 * Create the frame.
	 */
	public VentanaHistorial(PacienteRecord paciente) {
		this.paciente=paciente;
		historial=PersistenceFactory.forPaciente().getHistorial(paciente.getDniPaciente());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 709, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPaneCitas());
		contentPane.add(getLblHistorial());
		contentPane.add(getLblCitas());
		contentPane.add(getScrollPaneCausas());
		contentPane.add(getLblCausas());
		contentPane.add(getScrollPanePrescripciones());
		contentPane.add(getLblPrescricpiones());
//		contentPane.add(getScrollPaneDiagnosticos());
//		contentPane.add(getLblDiagnosticos());
		contentPane.add(getScrollPanelVacunas());
		contentPane.add(getLblVacunas());
		contentPane.add(getBtnVacunar());
		contentPane.add(getBtnVerCalendarioVacunacion());
		
	}
	
	private JScrollPane getScrollPaneCitas() {
		if (scrollPaneCitas == null) {
			scrollPaneCitas = new JScrollPane();
			scrollPaneCitas.setBounds(20, 109, 258, 135);
			scrollPaneCitas.setViewportView(getListCitas());
		}
		return scrollPaneCitas;
	}
	private JLabel getLblHistorial() {
		if (lblHistorial == null) {
			lblHistorial = new JLabel("Historial "+paciente.getNombre()+" "+paciente.getApellidos());
			lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblHistorial.setBounds(171, 11, 334, 27);
		}
		return lblHistorial;
	}
	private JList<CitaRecord> getListCitas() {
		if (listCitas == null) {
			DefaultListModel<CitaRecord> modelo = new DefaultListModel<CitaRecord>();
			modelo.addAll(historial.getCitas());
			listCitas = new JList<CitaRecord>(modelo);
		}
		return listCitas;
	}
	private JLabel getLblCitas() {
		if (lblCitas == null) {
			lblCitas = new JLabel("Citas");
			lblCitas.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCitas.setBounds(20, 59, 258, 38);
		}
		return lblCitas;
	}
	private JScrollPane getScrollPaneCausas() {
		if (scrollPaneCausas == null) {
			scrollPaneCausas = new JScrollPane();
			scrollPaneCausas.setBounds(416, 109, 258, 135);
			scrollPaneCausas.setViewportView(getListCausas());
		}
		return scrollPaneCausas;
	}
	private JLabel getLblCausas() {
		if (lblCausas == null) {
			lblCausas = new JLabel("Causas ");
			lblCausas.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCausas.setBounds(416, 59, 258, 38);
		}
		return lblCausas;
	}
	private JList<CausaRecord> getListCausas() {
		if (listCausas == null) {
			DefaultListModel<CausaRecord> modelo = new DefaultListModel<CausaRecord>();
			modelo.addAll(historial.getCausas());
			listCausas = new JList<CausaRecord>(modelo);
		}
		return listCausas;
	}
	private JScrollPane getScrollPanePrescripciones() {
		if (scrollPanePrescripciones == null) {
			scrollPanePrescripciones = new JScrollPane();
			scrollPanePrescripciones.setBounds(20, 329, 258, 135);
			scrollPanePrescripciones.setViewportView(getListPrescripciones());
		}
		return scrollPanePrescripciones;
	}
	private JLabel getLblPrescricpiones() {
		if (lblPrescricpiones == null) {
			lblPrescricpiones = new JLabel("Prescripciones");
			lblPrescricpiones.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPrescricpiones.setBounds(20, 279, 258, 38);
		}
		return lblPrescricpiones;
	}
	private JList<PrescripcionRecord> getListPrescripciones() {
		if (listPrescripciones == null) {
			DefaultListModel<PrescripcionRecord> modelo = new DefaultListModel<>();
			modelo.addAll(historial.getPrescripciones());
			listPrescripciones = new JList<PrescripcionRecord>(modelo);
		}
		return listPrescripciones;
	}
	private JScrollPane getScrollPaneDiagnosticos() {
		if (scrollPaneDiagnosticos == null) {
			scrollPaneDiagnosticos = new JScrollPane();
			scrollPaneDiagnosticos.setBounds(416, 329, 258, 135);
			scrollPaneDiagnosticos.setViewportView(getListDiagnosticos());
		}
		return scrollPaneDiagnosticos;
	}
	private JLabel getLblDiagnosticos() {
		if (lblDiagnosticos == null) {
			lblDiagnosticos = new JLabel("Diagnosticos");
			lblDiagnosticos.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDiagnosticos.setBounds(416, 279, 258, 38);
		}
		return lblDiagnosticos;
	}
	private JList<HistorialRecord> getListDiagnosticos() {
		if (listDiagnosticos == null) {
			listDiagnosticos = new JList();
		}
		return listDiagnosticos;
	}
	private JScrollPane getScrollPanelVacunas() {
		if (scrollPanelVacunas == null) {
			scrollPanelVacunas = new JScrollPane();
			scrollPanelVacunas.setBounds(203, 548, 258, 135);
			scrollPanelVacunas.setViewportView(getListVacunas());
		}
		return scrollPanelVacunas;
	}
	private JLabel getLblVacunas() {
		if (lblVacunas == null) {
			lblVacunas = new JLabel("Vacunas");
			lblVacunas.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblVacunas.setBounds(203, 498, 258, 38);
		}
		return lblVacunas;
	}
	private JList<VacunaRecord> getListVacunas() {
		if (listVacunas == null) {
			DefaultListModel<VacunaRecord> modelo = new DefaultListModel<>();
			modelo.addAll(historial.getVacunasRealizadas());
			listVacunas = new JList(modelo);
		}
		return listVacunas;
	}
	private JButton getBtnVacunar() {
		if (btnVacunar == null) {
			btnVacunar = new JButton("Vacunar");
			btnVacunar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vacunar();
				}
			});
			btnVacunar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnVacunar.setBounds(471, 546, 89, 23);
		}
		return btnVacunar;
	}
	private JButton getBtnVerCalendarioVacunacion() {
		if (btnVerCalendarioVacunacion == null) {
			btnVerCalendarioVacunacion = new JButton("Ver calendario vacunacion");
			btnVerCalendarioVacunacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verCalendarioVacunacion();
				}
			});
			btnVerCalendarioVacunacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnVerCalendarioVacunacion.setBounds(471, 580, 203, 23);
		}
		return btnVerCalendarioVacunacion;
	}
	
	private void verCalendarioVacunacion() {
		VentanaCalendarioVacunacion v = new VentanaCalendarioVacunacion(paciente, null);
		v.setVisible(true);
		dispose();
	}
	
	private void vacunar() {
		VentanaVacunacion v = new VentanaVacunacion(paciente, null, null);
		v.setVisible(true);
		this.dispose();
	}
}
