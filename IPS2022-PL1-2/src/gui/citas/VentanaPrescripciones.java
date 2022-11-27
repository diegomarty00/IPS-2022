package gui.citas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.cita.CitaService;
import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
import persistencia.enfermero.EnfermeroRecord;
import persistencia.medico.MedicoRecord;
import util.BusinessException;

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
	MedicoRecord medico;
	EnfermeroRecord enfermero;
	CitaService citaService = BusinessFactory.forCitaService();
	
	ArrayList<String> selectedCausas = new ArrayList<String>();
	private JLabel lblEditPrescripcion;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnYear;
	private JLabel lblFormato;
	private JSpinner spnHora;
	private JLabel lblDosPuntos;
	private JSpinner spnMinutos;
	private JScrollPane scrollPanePosiblesPrescs;
	private JList<PrescripcionRecord> listPosiblesPrescs;
	private JScrollPane scrollPanePrescsAsignadas;
	private JLabel lblBuscador;
	private JList<PrescripcionRecord> listPrescsAsignadas;
	private DefaultListModel<PrescripcionRecord> modelUserPrescs;
	private JLabel lblPrescripcionesAsignadas;
	private JLabel lblHhMm;
	private JLabel lblTitulo;
	private JTextField txtTitulo;
	private JLabel lblTipo;
	private JTextField txtTipo;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JTextField txtIntervalo;
	private JLabel lblIntervalo;
	private JTextField txtDuracion;
	private JLabel lblDuracion;
	private JTextField txtObservaciones;
	private JLabel lblObservaciones;
	private JButton btnAsignar;
	private JButton btnNuevaPresc;
	private JButton btnEliminarPresc;
	
	private PrescripcionRecord prescripcionSelected;
	private JButton btnCurrentDatetTime;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrescripciones(CitaRecord cita, MedicoRecord medico, EnfermeroRecord enfermero) {
		this.cita = cita;
		this.medico=medico;
		this.enfermero=enfermero;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 883, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPrescripciones());
		contentPane.add(getBtnSave());
		contentPane.add(getTxtBuscar());
		contentPane.add(getBtnBuscar());
		contentPane.add(getBtnReset());
		contentPane.add(getLblEditPrescripcion());
		contentPane.add(getSpnDia());
		contentPane.add(getSpnMes());
		contentPane.add(getSpnYear());
		contentPane.add(getLblFormato());
		contentPane.add(getSpnHora());
		contentPane.add(getLblDosPuntos());
		contentPane.add(getSpnMinutos());
		contentPane.add(getScrollPane_1());
		contentPane.add(getScrollPanePrescsAsignadas());
		contentPane.add(getLblBuscador());
		contentPane.add(getLblPrescripcionesAsignadas());
		contentPane.add(getLblHhMm());
		contentPane.add(getLblTitulo());
		contentPane.add(getTxtTitulo());
		contentPane.add(getLblTipo());
		contentPane.add(getTxtTipo());
		contentPane.add(getLblCantidad());
		contentPane.add(getTxtCantidad());
		contentPane.add(getTxtIntervalo());
		contentPane.add(getLblIntervalo());
		contentPane.add(getTxtDuracion());
		contentPane.add(getLblDuracion());
		contentPane.add(getTxtObservaciones());
		contentPane.add(getLblObservaciones());
		contentPane.add(getBtnAsignar());
		contentPane.add(getBtnNuevaPresc());
		contentPane.add(getBtnEliminarPresc());
		contentPane.add(getBtnCurrentDatetTime());
	}

	private JLabel getLblPrescripciones() {
		if (lblPrescripciones == null) {
			lblPrescripciones = new JLabel("Prescripciones para el paciente");
			lblPrescripciones.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblPrescripciones.setBounds(20, 11, 406, 42);
		}
		return lblPrescripciones;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Guardar Selecci\u00F3n");
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveSelection();
				}
			});
			btnSave.setBounds(699, 606, 149, 23);
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
			txtBuscar.setBounds(20, 132, 208, 20);
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
			btnBuscar.setBounds(337, 131, 89, 23);
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
			btnReset.setBounds(238, 131, 89, 23);
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
			List<PrescripcionRecord> prescripciones = new ArrayList<PrescripcionRecord>();
			for (int i = 0; i < modelUserPrescs.getSize(); i++) {
				prescripciones.add(modelUserPrescs.getElementAt(i));
			}
			BusinessFactory.forCitaService().updatePrescripciones(prescripciones, cita.idCita);
			VentanaCita ventanaCita = new VentanaCita(cita, medico, enfermero);
			ventanaCita.setVisible(true);
			this.dispose();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	private JLabel getLblEditPrescripcion() {
		if (lblEditPrescripcion == null) {
			lblEditPrescripcion = new JLabel("Crear/Editar prescripcion");
			lblEditPrescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEditPrescripcion.setBounds(20, 393, 308, 29);
		}
		return lblEditPrescripcion;
	}
	private JSpinner getSpnDia() {
		if (spnDia == null) {
			spnDia = new JSpinner(new SpinnerNumberModel(LocalDate.now().getDayOfMonth(),1,31,1));
			spnDia.setBounds(20, 608, 41, 20);
		}
		return spnDia;
	}
	private JSpinner getSpnMes() {
		if (spnMes == null) {
			spnMes = new JSpinner(new SpinnerNumberModel(LocalDate.now().getMonthValue(),1,12,1));
			spnMes.setBounds(71, 608, 43, 20);
		}
		return spnMes;
	}
	private JSpinner getSpnYear() {
		if (spnYear == null) {
			spnYear = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(),1900,2100,1));
			spnYear.setBounds(124, 608, 64, 20);
		}
		return spnYear;
	}
	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("dd              mm             yyyy");
			lblFormato.setBounds(28, 584, 173, 20);
		}
		return lblFormato;
	}
	private JSpinner getSpnHora() {
		if (spnHora == null) {
			spnHora = new JSpinner(new SpinnerNumberModel(LocalTime.now().getHour(),0,23,1));
			spnHora.setBounds(220, 608, 41, 20);
		}
		return spnHora;
	}
	private JLabel getLblDosPuntos() {
		if (lblDosPuntos == null) {
			lblDosPuntos = new JLabel(":");
			lblDosPuntos.setBounds(265, 611, 4, 14);
		}
		return lblDosPuntos;
	}
	private JSpinner getSpnMinutos() {
		if (spnMinutos == null) {
			spnMinutos = new JSpinner(new SpinnerNumberModel(LocalTime.now().getMinute(),0,59,1));
			spnMinutos.setBounds(271, 608, 43, 20);
		}
		return spnMinutos;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPanePosiblesPrescs == null) {
			scrollPanePosiblesPrescs = new JScrollPane();
			scrollPanePosiblesPrescs.setBounds(20, 163, 323, 211);
			scrollPanePosiblesPrescs.setViewportView(getListPosiblesPrescs());
		}
		return scrollPanePosiblesPrescs;
	}
	private JList<PrescripcionRecord> getListPosiblesPrescs() {
		if (listPosiblesPrescs == null) {
			listPosiblesPrescs = new JList<PrescripcionRecord>(getModelPosiblesPrescs());
			listPosiblesPrescs.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					PrescripcionRecord pressed = (PrescripcionRecord) listPosiblesPrescs.getModel().getElementAt(listPosiblesPrescs.locationToIndex(e.getPoint()));
					fillPosibPresc(pressed);
					getBtnEliminarPresc().setEnabled(false);
				}
			});
		}
		return listPosiblesPrescs;
	}

	private void fillPosibPresc(PrescripcionRecord presc) {
		prescripcionSelected=presc;
		getTxtTitulo().setText(presc.getTitulo());
		getTxtTipo().setText(presc.getTipo());
		isMedicamento(presc);
	}
	
	private void fillAsignedPresc(PrescripcionRecord presc) {
		prescripcionSelected=presc;
		getBtnEliminarPresc().setEnabled(true);
		
		getTxtTitulo().setText(presc.getTitulo());
		getTxtTipo().setText(presc.getTipo());
		getTxtObservaciones().setText(presc.getObservaciones());
		
		if (isMedicamento(presc)) {
			getTxtCantidad().setText(presc.getCantidad());
			getTxtDuracion().setText(presc.getDuracion());
			getTxtIntervalo().setText(presc.getIntervaloDosis());
		}
		
		getSpnYear().setValue((Integer)presc.getFechaAsignacion().getYear());
		getSpnMes().setValue(presc.getFechaAsignacion().getMonthValue());
		getSpnDia().setValue(presc.getFechaAsignacion().getDayOfMonth());
		
		getSpnHora().setValue(presc.getHoraAsignacion().getHour());
		getSpnMinutos().setValue(presc.getHoraAsignacion().getMinute());
	}
	
	private boolean isMedicamento(PrescripcionRecord presc) {
		if (!presc.getTipo().equalsIgnoreCase("medicamento")) {
			getTxtCantidad().setEnabled(false);
			getTxtDuracion().setEnabled(false);
			getTxtIntervalo().setEnabled(false);
			return false;
		}
		else {
			getTxtCantidad().setEnabled(true);
			getTxtDuracion().setEnabled(true);
			getTxtIntervalo().setEnabled(true);
			return true;
		}
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
	private JScrollPane getScrollPanePrescsAsignadas() {
		if (scrollPanePrescsAsignadas == null) {
			scrollPanePrescsAsignadas = new JScrollPane();
			scrollPanePrescsAsignadas.setBounds(540, 163, 308, 211);
			scrollPanePrescsAsignadas.setViewportView(getListPrescsAsignadas());
		}
		return scrollPanePrescsAsignadas;
	}
	private JLabel getLblBuscador() {
		if (lblBuscador == null) {
			lblBuscador = new JLabel("Buscador de prescripciones");
			lblBuscador.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblBuscador.setBounds(20, 91, 246, 29);
		}
		return lblBuscador;
	}
	private JList<PrescripcionRecord> getListPrescsAsignadas() {
		if (listPrescsAsignadas == null) {
			listPrescsAsignadas = new JList<PrescripcionRecord>(getModelUserPrescs());
			listPrescsAsignadas.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					PrescripcionRecord pressed = (PrescripcionRecord) listPrescsAsignadas.getModel().getElementAt(listPrescsAsignadas.locationToIndex(e.getPoint()));
					fillAsignedPresc(pressed);
				}
			});
		}
		return listPrescsAsignadas;
	}
	
	private DefaultListModel<PrescripcionRecord> getModelUserPrescs() {
		modelUserPrescs = new DefaultListModel<PrescripcionRecord>();
		List<PrescripcionRecord> prescripciones = PersistenceFactory.forCita().getPrescripciones(cita.idCita);
		for (PrescripcionRecord prescripcionRecord : prescripciones) {
			modelUserPrescs.addElement(prescripcionRecord);
		}
		return modelUserPrescs;
	}

	private JLabel getLblPrescripcionesAsignadas() {
		if (lblPrescripcionesAsignadas == null) {
			lblPrescripcionesAsignadas = new JLabel("Prescripciones asignadas");
			lblPrescripcionesAsignadas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPrescripcionesAsignadas.setBounds(540, 100, 294, 20);
		}
		return lblPrescripcionesAsignadas;
	}
	private JLabel getLblHhMm() {
		if (lblHhMm == null) {
			lblHhMm = new JLabel("HH            mm");
			lblHhMm.setBounds(232, 584, 100, 20);
		}
		return lblHhMm;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Titulo");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTitulo.setBounds(20, 428, 46, 14);
		}
		return lblTitulo;
	}
	private JTextField getTxtTitulo() {
		if (txtTitulo == null) {
			txtTitulo = new JTextField();
			txtTitulo.setBounds(20, 443, 115, 20);
			txtTitulo.setColumns(10);
		}
		return txtTitulo;
	}
	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTipo.setBounds(20, 474, 46, 14);
		}
		return lblTipo;
	}
	private JTextField getTxtTipo() {
		if (txtTipo == null) {
			txtTipo = new JTextField();
			txtTipo.setColumns(10);
			txtTipo.setBounds(20, 489, 115, 20);
		}
		return txtTipo;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad");
			lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCantidad.setBounds(20, 528, 89, 14);
		}
		return lblCantidad;
	}
	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setColumns(10);
			txtCantidad.setBounds(20, 543, 115, 20);
		}
		return txtCantidad;
	}
	private JTextField getTxtIntervalo() {
		if (txtIntervalo == null) {
			txtIntervalo = new JTextField();
			txtIntervalo.setColumns(10);
			txtIntervalo.setBounds(169, 543, 115, 20);
		}
		return txtIntervalo;
	}
	private JLabel getLblIntervalo() {
		if (lblIntervalo == null) {
			lblIntervalo = new JLabel("Intervalo");
			lblIntervalo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblIntervalo.setBounds(169, 528, 89, 14);
		}
		return lblIntervalo;
	}
	private JTextField getTxtDuracion() {
		if (txtDuracion == null) {
			txtDuracion = new JTextField();
			txtDuracion.setColumns(10);
			txtDuracion.setBounds(318, 543, 115, 20);
		}
		return txtDuracion;
	}
	private JLabel getLblDuracion() {
		if (lblDuracion == null) {
			lblDuracion = new JLabel("Duracion");
			lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDuracion.setBounds(318, 528, 89, 14);
		}
		return lblDuracion;
	}
	private JTextField getTxtObservaciones() {
		if (txtObservaciones == null) {
			txtObservaciones = new JTextField();
			txtObservaciones.setColumns(10);
			txtObservaciones.setBounds(169, 443, 263, 66);
		}
		return txtObservaciones;
	}
	private JLabel getLblObservaciones() {
		if (lblObservaciones == null) {
			lblObservaciones = new JLabel("Observaciones");
			lblObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblObservaciones.setBounds(169, 428, 89, 14);
		}
		return lblObservaciones;
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					asignarPrescripcion();
				}
			});
			btnAsignar.setBounds(442, 442, 89, 23);
		}
		return btnAsignar;
	}
	
	private void asignarPrescripcion() {
		
		if (!checkFields())
			return;
		
		getBtnEliminarPresc().setEnabled(false);
		
		LocalTime hora = LocalTime.of((Integer)getSpnHora().getValue(), (Integer)getSpnMinutos().getValue());
		LocalDate fecha = LocalDate.of((Integer)getSpnYear().getValue(), (Integer)getSpnMes().getValue(), (Integer)getSpnDia().getValue());
		
		String titulo = getTxtTitulo().getText();
		String tipo = getTxtTipo().getText();
		String observaciones = getTxtObservaciones().getText();
		
		PrescripcionRecord prescripcion;
		
		if (!getTxtTipo().getText().equalsIgnoreCase("medicamento")) {
			prescripcion = BusinessFactory.forCitaService().createPrescripcionRecord(titulo, tipo, observaciones, hora, fecha, cita.idCita);
		}
		else {
			String cantidad = getTxtCantidad().getText();
			String intervalo = getTxtIntervalo().getText();
			String duracion = getTxtDuracion().getText();
			
			prescripcion = BusinessFactory.forCitaService().createPrescripcionRecord(titulo, tipo, cantidad, intervalo, duracion, observaciones, hora, fecha, cita.idCita);
		}
		modelUserPrescs.addElement(prescripcion);
		getListPrescsAsignadas().setModel(modelUserPrescs);
	}
	
	private boolean checkFields() {
		String titulo = getTxtTitulo().getText();
		String tipo = getTxtTipo().getText();
		String cantidad = getTxtCantidad().getText();
		String intervalo = getLblIntervalo().getText();
		String duracion = getTxtDuracion().getText();
		if (titulo.equals("") || tipo.equals("")) {
			JOptionPane.showMessageDialog(this, "Hay campos sin rellenar. Por favor compruebe");
			return false;
		}
		else if (tipo.equalsIgnoreCase("medicamento")) {
			if (cantidad.equals("") || intervalo.equals("") || duracion.equals("")) {
				JOptionPane.showMessageDialog(this, "Hay campos sin rellenar. Por favor compruebe");
				return false;
			}
		}
		return true;
	}

	private JButton getBtnNuevaPresc() {
		if (btnNuevaPresc == null) {
			btnNuevaPresc = new JButton("Limpiar");
			btnNuevaPresc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clean();
				}
			});
			btnNuevaPresc.setBounds(442, 488, 89, 23);
		}
		return btnNuevaPresc;
	}
	
	private void clean() {
		prescripcionSelected=null;
		getTxtTitulo().setText("");
		getTxtTipo().setText("");
		getTxtObservaciones().setText("");
		getTxtCantidad().setEnabled(true);
		getTxtDuracion().setEnabled(true);
		getTxtIntervalo().setEnabled(true);
		getTxtCantidad().setText("");
		getTxtDuracion().setText("");
		getTxtIntervalo().setText("");
		getBtnEliminarPresc().setEnabled(false);
		currentDateTime();
	}
	
	private JButton getBtnEliminarPresc() {
		if (btnEliminarPresc == null) {
			btnEliminarPresc = new JButton("Eliminar");
			btnEliminarPresc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteSelectedPresc();
				}
			});
			btnEliminarPresc.setBounds(443, 542, 89, 23);
			btnEliminarPresc.setEnabled(false);
		}
		return btnEliminarPresc;
	}
	protected void deleteSelectedPresc() {
		modelUserPrescs.removeElement(prescripcionSelected);
		clean();
	}

	private JButton getBtnCurrentDatetTime() {
		if (btnCurrentDatetTime == null) {
			btnCurrentDatetTime = new JButton("Establecer fecha/hora actual");
			btnCurrentDatetTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentDateTime();
				}
			});
			btnCurrentDatetTime.setBounds(337, 607, 194, 23);
		}
		return btnCurrentDatetTime;
	}

	protected void currentDateTime() {
		getSpnYear().setValue(LocalDate.now().getYear());
		getSpnMes().setValue(LocalDate.now().getMonthValue());
		getSpnDia().setValue(LocalDate.now().getDayOfMonth());
		
		getSpnHora().setValue(LocalTime.now().getHour());
		getSpnMinutos().setValue(LocalTime.now().getMinute());
	}
}
