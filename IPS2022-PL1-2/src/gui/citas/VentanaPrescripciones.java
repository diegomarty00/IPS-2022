package gui.citas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import business.BusinessFactory;
import business.cita.CitaService;
import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
import util.BusinessException;
import javax.swing.JList;

public class VentanaPrescripciones extends JFrame {

	private JPanel contentPane;
	private JLabel lblPrescripciones;
	private JButton btnSave;
	private JTextField txtBuscar;
	private JButton btnBuscar;

	Object[][] dataTable;
	String[] columnNames;
	private JButton btnReset;

	CitaRecord cita;
	CitaService citaService = BusinessFactory.forCitaService();
	
	ArrayList<String> selectedCausas = new ArrayList<String>();
	private JLabel lblSetHoraFecha;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnYear;
	private JLabel lblFormato;
	private JSpinner spnHora;
	private JLabel lblDosPuntos;
	private JSpinner spnMinutos;
	private JScrollPane scrollPanePosiblesPrescs;
	private JList<PrescripcionRecord> listPosiblesPrescs;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrescripciones(CitaRecord cita) {
		this.cita = cita;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPrescripciones());
		contentPane.add(getBtnSave());
		contentPane.add(getTxtBuscar());
		contentPane.add(getBtnBuscar());
		contentPane.add(getBtnReset());
		contentPane.add(getLblSetHoraFecha());
		contentPane.add(getSpnDia());
		contentPane.add(getSpnMes());
		contentPane.add(getSpnYear());
		contentPane.add(getLblFormato());
		contentPane.add(getSpnHora());
		contentPane.add(getLblDosPuntos());
		contentPane.add(getSpnMinutos());
		contentPane.add(getScrollPane_1());
	}

	private JLabel getLblPrescripciones() {
		if (lblPrescripciones == null) {
			lblPrescripciones = new JLabel("Prescripciones para el paciente");
			lblPrescripciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPrescripciones.setBounds(20, 11, 298, 42);
		}
		return lblPrescripciones;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Guardar Selecci\u00F3n");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveSelection();
				}
			});
			btnSave.setBounds(445, 467, 149, 23);
		}
		return btnSave;
	}
	private JTextField getTxtBuscar() {
		if (txtBuscar == null) {
			txtBuscar = new JTextField();
			txtBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			txtBuscar.setBounds(20, 66, 265, 20);
			txtBuscar.setColumns(10);
		}
		return txtBuscar;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			btnBuscar.setBounds(394, 65, 89, 23);
		}
		return btnBuscar;
	}
	
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("Limpiar");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getTxtBuscar().setText("");
					search();
				}
			});
			btnReset.setBounds(295, 65, 89, 23);
		}
		return btnReset;
	}
	
	private void search() {
		String text = getTxtBuscar().getText().toLowerCase();
		DefaultListModel<PrescripcionRecord> modelFiltered = new DefaultListModel<PrescripcionRecord>();
		DefaultListModel<PrescripcionRecord> defaultModel = getModelPosiblesPrescs();
		for (int i = 0; i < defaultModel.getSize(); i++) {
			String titulo = defaultModel.getElementAt(i).getTitulo().toLowerCase();
			if (titulo.contains(text))
				modelFiltered.addElement(defaultModel.getElementAt(i));
		}
		getListPosiblesPrescs().setModel(modelFiltered);
	}


	private void saveSelection() {
		try {
			LocalDate fecha = LocalDate.of((Integer)getSpnYear().getValue(), (Integer)getSpnMes().getValue(), (Integer)getSpnDia().getValue());
			LocalTime hora = LocalTime.of((Integer)getSpnHora().getValue(), (Integer)getSpnMinutos().getValue());
			BusinessFactory.forCitaService().updateCausas(cita.idCita, selectedCausas, fecha, hora);
			VentanaCita ventanaCita = new VentanaCita(cita);
			ventanaCita.setVisible(true);
			this.dispose();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	private JLabel getLblSetHoraFecha() {
		if (lblSetHoraFecha == null) {
			lblSetHoraFecha = new JLabel("Establecer fecha y hora de causas a\u00F1adidas");
			lblSetHoraFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSetHoraFecha.setBounds(20, 359, 308, 29);
		}
		return lblSetHoraFecha;
	}
	private JSpinner getSpnDia() {
		if (spnDia == null) {
			spnDia = new JSpinner(new SpinnerNumberModel(LocalDate.now().getDayOfMonth(),1,31,1));
			spnDia.setBounds(20, 423, 41, 20);
		}
		return spnDia;
	}
	private JSpinner getSpnMes() {
		if (spnMes == null) {
			spnMes = new JSpinner(new SpinnerNumberModel(LocalDate.now().getMonthValue(),1,12,1));
			spnMes.setBounds(71, 423, 43, 20);
		}
		return spnMes;
	}
	private JSpinner getSpnYear() {
		if (spnYear == null) {
			spnYear = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(),1900,2100,1));
			spnYear.setBounds(124, 423, 64, 20);
		}
		return spnYear;
	}
	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("dd              mm             yyyy");
			lblFormato.setBounds(28, 399, 173, 20);
		}
		return lblFormato;
	}
	private JSpinner getSpnHora() {
		if (spnHora == null) {
			spnHora = new JSpinner(new SpinnerNumberModel(LocalTime.now().getHour(),0,23,1));
			spnHora.setBounds(220, 423, 41, 20);
		}
		return spnHora;
	}
	private JLabel getLblDosPuntos() {
		if (lblDosPuntos == null) {
			lblDosPuntos = new JLabel(":");
			lblDosPuntos.setBounds(265, 426, 4, 14);
		}
		return lblDosPuntos;
	}
	private JSpinner getSpnMinutos() {
		if (spnMinutos == null) {
			spnMinutos = new JSpinner(new SpinnerNumberModel(LocalTime.now().getMinute(),0,59,1));
			spnMinutos.setBounds(271, 423, 43, 20);
		}
		return spnMinutos;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPanePosiblesPrescs == null) {
			scrollPanePosiblesPrescs = new JScrollPane();
			scrollPanePosiblesPrescs.setBounds(20, 97, 323, 211);
			scrollPanePosiblesPrescs.setViewportView(getListPosiblesPrescs());
		}
		return scrollPanePosiblesPrescs;
	}
	private JList<PrescripcionRecord> getListPosiblesPrescs() {
		if (listPosiblesPrescs == null) {
			listPosiblesPrescs = new JList<PrescripcionRecord>(getModelPosiblesPrescs());
		}
		return listPosiblesPrescs;
	}

	private DefaultListModel<PrescripcionRecord> getModelPosiblesPrescs() {
		String[] posiblesPrescs = BusinessFactory.forCitaService().getPosiblesPrescripciones();
		DefaultListModel<PrescripcionRecord> model = new DefaultListModel<PrescripcionRecord>(); 
		for (int i = 0; i < posiblesPrescs.length; i+=2) {
			String titulo = posiblesPrescs[i];
			String tipo = posiblesPrescs[i+1];
			model.addElement(new PrescripcionRecord(titulo, tipo));
		}
		return model;
	}
}
