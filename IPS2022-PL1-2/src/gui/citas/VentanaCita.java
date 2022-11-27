package gui.citas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.BusinessFactory;
import business.cita.CitaService;
import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import persistencia.cita.PrescripcionRecord;
import persistencia.cita.impl.CitaGatewayImpl;
import persistencia.enfermero.EnfermeroRecord;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;

public class VentanaCita extends JFrame {

	private JPanel contentPane;
	private JSpinner spnHoraEntrada;
	private JSpinner spnMinutosEntrada;
	private JSpinner spnHoraSalida;
	private JSpinner spnMinutosSalida;
	private boolean horaEntrMod = false;
	private boolean minEntrMod = false;
	private boolean horaSalMod = false;
	private boolean minSalMod = false;

	CitaRecord cita;
	PacienteRecord pacienteAsociado;
	MedicoRecord medico;
	EnfermeroRecord enfermero;
	
	CitaService citaService;
	private JLabel lblCita;
	private JLabel lblHoraEntrada;
	private JLabel lblDosPuntosEntrada;
	private JButton btnSetHoraEntrada;
	private JLabel lblHoraSalida;
	private JLabel lblDosPuntosSalida;
	private JButton btnSetHoraSalida;
	private JButton btnCerrarCita;
	private JButton btnVerHistorial;
	private JLabel lblCausas;
	private JButton btnSeleccionarCausas;
	private JList<CausaRecord> listCausas;
	private DefaultListModel<CausaRecord> modeloCausas;
	private JLabel lblPrescripciones;
	private JList<PrescripcionRecord> listPrescripciones;
	private DefaultListModel<PrescripcionRecord> modeloPrescripciones;
	private JButton btnSeleccionarPrescripciones;
	private JScrollPane scrollPanePrescripciones;
	private JScrollPane scrollPaneCausas;
	private JComboBox cbAsistencia;
	private JLabel lblVacunas;
	private JButton btnVacunar;
	private JScrollPane scrollPaneVacunas;
	private JButton btnVerCalendarioVacunas;
	private JList<VacunaRecord> listVacunas;
	private DefaultListModel<VacunaRecord> modeloVacunas;
	private JButton btnSolicitarCita;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CitaGatewayImpl citaGateway = (CitaGatewayImpl) PersistenceFactory.forCita();
//					CitaRecord cita = citaGateway.findById("1").get();
//					VentanaCita frame = new VentanaCita(cita);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaCita(CitaRecord cita, MedicoRecord medico, EnfermeroRecord enfermero) {

		this.cita = cita;
		this.pacienteAsociado=cita.getPacienteAsociado();
		this.medico=medico;
		this.enfermero=enfermero;
		citaService = BusinessFactory.forCitaService();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 788, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		modeloCausas = new DefaultListModel<>();
		updateModeloCausas();
		
		modeloPrescripciones = new DefaultListModel<>();
		updateModeloPrescripciones();
		
		modeloVacunas = new DefaultListModel<>();
		updateModeloVacunas();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCita = new JLabel("Cita: "+pacienteAsociado.getNombre()+" "+pacienteAsociado.getApellidos());
		lblCita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCita.setBounds(10, 26, 484, 30);
		contentPane.add(lblCita);

		SpinnerModel smHoraEntrada = new SpinnerNumberModel((cita.horaEntradaReal!=null)?cita.horaEntradaReal.getHour():00, 00, 23, 1);
		spnHoraEntrada = new JSpinner(smHoraEntrada);
		spnHoraEntrada.setBounds(118, 105, 37, 20);
		spnHoraEntrada.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				horaEntrMod = true;
			}
		});
		contentPane.add(spnHoraEntrada);

		lblHoraEntrada = new JLabel("Hora de entrada");
		lblHoraEntrada.setBounds(10, 111, 98, 14);
		contentPane.add(lblHoraEntrada);

		lblDosPuntosEntrada = new JLabel(":");
		lblDosPuntosEntrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDosPuntosEntrada.setBounds(158, 107, 46, 14);;
		contentPane.add(lblDosPuntosEntrada);
		
		SpinnerModel smMinutosEntrada = new SpinnerNumberModel((cita.horaEntradaReal!=null)?cita.horaEntradaReal.getMinute():00, 00, 59, 1);
		spnMinutosEntrada = new JSpinner(smMinutosEntrada);
		spnMinutosEntrada.setBounds(165, 105, 36, 20);
		spnMinutosEntrada.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				minEntrMod = true;
			}
		});
		contentPane.add(spnMinutosEntrada);

		btnSetHoraEntrada = new JButton("Establecer hora entrada");
		btnSetHoraEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerHoraEntrada();
			}
		});
		btnSetHoraEntrada.setBounds(273, 104, 188, 30);
		contentPane.add(btnSetHoraEntrada);

		SpinnerModel smHoraSalida = new SpinnerNumberModel((cita.horaSalidaReal!=null)?cita.horaSalidaReal.getHour():00, 00, 23, 1);
		spnHoraSalida = new JSpinner(smHoraSalida);
		spnHoraSalida.setBounds(118, 150, 37, 20);
		spnHoraSalida.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				horaSalMod = true;
			}
		});
		contentPane.add(spnHoraSalida);

		lblHoraSalida = new JLabel("Hora de salida");
		lblHoraSalida.setBounds(10, 155, 98, 14);
		contentPane.add(lblHoraSalida);

		lblDosPuntosSalida = new JLabel(":");
		lblDosPuntosSalida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDosPuntosSalida.setBounds(158, 151, 46, 14);
		contentPane.add(lblDosPuntosSalida);

		SpinnerModel smMinutosSalida = new SpinnerNumberModel((cita.horaSalidaReal!=null)?cita.horaSalidaReal.getMinute():00, 00, 59, 1);
		spnMinutosSalida = new JSpinner(smMinutosSalida);
		spnMinutosSalida.setBounds(168, 150, 36, 20);
		spnMinutosSalida.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				minSalMod = true;
			}
		});
		contentPane.add(spnMinutosSalida);

		btnSetHoraSalida = new JButton("Establecer hora salida");
		btnSetHoraSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerHoraSalida();
			}
		});
		btnSetHoraSalida.setBounds(273, 145, 188, 30);
		contentPane.add(btnSetHoraSalida);

		btnCerrarCita = new JButton("Cerrar cita");
		btnCerrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarCita();
			}
		});
		btnCerrarCita.setBounds(635, 565, 124, 23);
		contentPane.add(btnCerrarCita);
		contentPane.add(getBtnVerHistorial());
		if (medico!=null) {
			contentPane.add(getLblCausas());
			contentPane.add(getBtnSeleccionarCausas());
			contentPane.add(getLblPrescripciones());
			contentPane.add(getBtnSeleccionarPrescripciones());
		
			scrollPanePrescripciones = new JScrollPane();
			scrollPanePrescripciones.setBounds(399, 252, 310, 127);
			contentPane.add(scrollPanePrescripciones);
			scrollPanePrescripciones.setViewportView(getListPrescripciones());
			
			scrollPaneCausas = new JScrollPane();
			scrollPaneCausas.setBounds(23, 250, 310, 129);
			contentPane.add(scrollPaneCausas);
			scrollPaneCausas.setViewportView(getListCausas());
		}
		contentPane.add(getCbAsistencia());
		contentPane.add(getLblVacunas());
		contentPane.add(getBtnVacunar());
		contentPane.add(getScrollPaneVacunas());
		contentPane.add(getBtnVerCalendarioVacunas());
		contentPane.add(getBtnSolicitarCita());
	}

	private void updateModeloCausas() {
		modeloCausas.clear();
		List<CausaRecord> causas = PersistenceFactory.forCita().getCausas(cita.idCita);
		for (CausaRecord causa : causas) {
			modeloCausas.addElement(causa);
		}
	}
	
	private void updateModeloPrescripciones() {
		modeloPrescripciones.clear();
		List<PrescripcionRecord> prescripciones = PersistenceFactory.forCita().getPrescripciones(cita.idCita);
		for (PrescripcionRecord prescripcion : prescripciones) {
			modeloPrescripciones.addElement(prescripcion);
		}
	}
	
	private void updateModeloVacunas() {
		modeloVacunas.clear();
		List<VacunaRecord> vacunas = PersistenceFactory.forCita().getVacunas(cita.idCita);
		modeloVacunas.addAll(vacunas);
	}

	private void establecerHoraEntrada() {
		LocalTime time = LocalTime.now();
		spnHoraEntrada.setValue(time.getHour());
		spnMinutosEntrada.setValue(time.getMinute());
		horaEntrMod = true;
		minEntrMod = true;
	}

	private void establecerHoraSalida() {
		LocalTime time = LocalTime.now();
		spnHoraSalida.setValue(time.getHour());
		spnMinutosSalida.setValue(time.getMinute());
		horaSalMod = true;
		minSalMod = true;
	}

	private void cerrarCita() {
		try {
			
			if (isEntradaAntesQueSalida()) {
				if (horaEntrMod || minEntrMod)
					citaService.asignarHoraEntrada(cita.idCita, (Integer) spnHoraEntrada.getValue(),
							(Integer) spnMinutosEntrada.getValue());

				if (horaSalMod || minSalMod)
					citaService.asignarHoraSalida(cita.idCita, (Integer) spnHoraSalida.getValue(),
							(Integer) spnMinutosSalida.getValue());
				
				citaService.pacienteAcudido(cita.idCita, (String)cbAsistencia.getSelectedItem());
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "La hora de entrada no puede ser mayor que la de salida");
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	private boolean isEntradaAntesQueSalida() {
		if ((horaEntrMod || minEntrMod) && !(horaSalMod || minSalMod)) return true;
		if ((Integer) spnHoraEntrada.getValue() > (Integer) spnHoraSalida.getValue())
			return false;
		else if ((Integer) spnHoraEntrada.getValue() == (Integer) spnHoraSalida.getValue()) {
			if ((Integer) spnMinutosEntrada.getValue() > (Integer) spnMinutosSalida.getValue())
				return false;
		}
		return true;
	}
	
	private JButton getBtnVerHistorial() {
		if (btnVerHistorial == null) {
			btnVerHistorial = new JButton("Ver Historial");
			btnVerHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaHistorial v  = new VentanaHistorial(pacienteAsociado,medico,enfermero);
					v.setVisible(true);
				}
			});
			btnVerHistorial.setBounds(655, 34, 104, 23);
		}
		return btnVerHistorial;
	}
	private JLabel getLblCausas() {
		if (lblCausas == null) {
			lblCausas = new JLabel("Causas de la consulta");
			lblCausas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblCausas.setBounds(10, 211, 166, 41);
		}
		return lblCausas;
	}
	private JButton getBtnSeleccionarCausas() {
		if (btnSeleccionarCausas == null) {
			btnSeleccionarCausas = new JButton("Seleccionar Causas");
			btnSeleccionarCausas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openVentCausas();
				}			
			});
			btnSeleccionarCausas.setBounds(186, 223, 181, 20);
		}
		return btnSeleccionarCausas;
	}
	
	private void openVentCausas() {
		VentanaCausas ventCausas = new VentanaCausas(cita,medico,enfermero);
		ventCausas.setVisible(true);
		this.dispose();
	}
	
	private JList<CausaRecord> getListCausas() {
		if (listCausas == null) {
			listCausas = new JList<CausaRecord>(modeloCausas);
		}
		return listCausas;
	}
	private JLabel getLblPrescripciones() {
		if (lblPrescripciones == null) {
			lblPrescripciones = new JLabel("Prescripciones");
			lblPrescripciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPrescripciones.setBounds(399, 211, 166, 41);
		}
		return lblPrescripciones;
	}
	private JList<PrescripcionRecord> getListPrescripciones() {
		if (listPrescripciones == null) {
			listPrescripciones = new JList<PrescripcionRecord>(modeloPrescripciones);
		}
		return listPrescripciones;
	}
	private JButton getBtnSeleccionarPrescripciones() {
		if (btnSeleccionarPrescripciones == null) {
			btnSeleccionarPrescripciones = new JButton("Seleccionar Prescripciones");
			btnSeleccionarPrescripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openVentPresc();
				}
			});
			btnSeleccionarPrescripciones.setBounds(545, 223, 200, 20);
		}
		return btnSeleccionarPrescripciones;
	}
	
	private void openVentPresc() {
		VentanaPrescripciones ventPresc = new VentanaPrescripciones(cita,medico,enfermero);
		ventPresc.setVisible(true);
		this.dispose();
	}
	private JComboBox getCbAsistencia() {
		if (cbAsistencia == null) {
			cbAsistencia = new JComboBox();
			cbAsistencia.setModel(new DefaultComboBoxModel(new String[] {"Asistencia Sin Asignar", "Paciente Acudido", "Paciente No Acudido"}));
			cbAsistencia.setBounds(10, 72, 188, 22);
			cbAsistencia.setSelectedItem(cita.pacienteAcudido);
		}
		return cbAsistencia;
	}
	private JLabel getLblVacunas() {
		if (lblVacunas == null) {
			lblVacunas = new JLabel("Vacunas");
			lblVacunas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblVacunas.setBounds(399, 413, 70, 41);
		}
		return lblVacunas;
	}
	private JButton getBtnVacunar() {
		if (btnVacunar == null) {
			btnVacunar = new JButton("Vacunar");
			btnVacunar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					vacunar();
				}
			});
			btnVacunar.setBounds(467, 425, 98, 20);
		}
		return btnVacunar;
	}
	
	private void vacunar() {
		VentanaVacunacion v = new VentanaVacunacion(pacienteAsociado, cita, null,medico,enfermero);
		v.setVisible(true);
		this.dispose();
	}
	
	private JScrollPane getScrollPaneVacunas() {
		if (scrollPaneVacunas == null) {
			scrollPaneVacunas = new JScrollPane();
			scrollPaneVacunas.setBounds(401, 452, 310, 65);
			listVacunas = new JList<VacunaRecord>(modeloVacunas);
			scrollPaneVacunas.setViewportView(listVacunas);
		}
		return scrollPaneVacunas;
	}
	private JButton getBtnVerCalendarioVacunas() {
		if (btnVerCalendarioVacunas == null) {
			btnVerCalendarioVacunas = new JButton("Ver calendario vacunas");
			btnVerCalendarioVacunas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verCalendarioVacunas();
				}
			});
			btnVerCalendarioVacunas.setBounds(568, 424, 177, 23);
		}
		return btnVerCalendarioVacunas;
	}

	private JButton getBtnSolicitarCita() {
		if (btnSolicitarCita == null) {
			btnSolicitarCita = new JButton("Solicitar Cita");
			btnSolicitarCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SolicitarCita frame = new SolicitarCita(pacienteAsociado);
					frame.setVisible(true);
				}
			});
			btnSolicitarCita.setBounds(467, 565, 118, 23);
		}
		return btnSolicitarCita;
  }

  private void verCalendarioVacunas() {
		VentanaCalendarioVacunacion v = new VentanaCalendarioVacunacion(pacienteAsociado, cita, medico, enfermero);
		v.setVisible(true);
		this.dispose();
	}
}
