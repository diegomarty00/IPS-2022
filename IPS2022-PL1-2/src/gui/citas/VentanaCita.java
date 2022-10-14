package gui.citas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.cita.CitaService;
import business.cita.impl.CitaServiceImpl;
import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.cita.impl.CitaGatewayImpl;
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
	CitaService citaService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitaGatewayImpl citaGateway = (CitaGatewayImpl) PersistenceFactory.forCita();
					CitaRecord cita = citaGateway.findById("1").get();
					VentanaCita frame = new VentanaCita(cita);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCita(CitaRecord cita) {

		this.cita = cita;
		citaService = new CitaServiceImpl();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCita = new JLabel("Cita");
		lblCita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCita.setBounds(10, 26, 78, 30);
		contentPane.add(lblCita);

		SpinnerModel smHoraEntrada = new SpinnerNumberModel(00, 00, 23, 1);
		spnHoraEntrada = new JSpinner(smHoraEntrada);
		spnHoraEntrada.setBounds(118, 71, 37, 20);
		spnHoraEntrada.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				horaEntrMod = true;
			}
		});
		contentPane.add(spnHoraEntrada);

		JLabel lblHoraEntrada = new JLabel("Hora de entrada");
		lblHoraEntrada.setBounds(10, 77, 98, 14);
		contentPane.add(lblHoraEntrada);

		JLabel lblDosPuntosEntrada = new JLabel(":");
		lblDosPuntosEntrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDosPuntosEntrada.setBounds(158, 73, 46, 14);
		contentPane.add(lblDosPuntosEntrada);

		SpinnerModel smMinutosEntrada = new SpinnerNumberModel(00, 00, 59, 1);
		spnMinutosEntrada = new JSpinner(smMinutosEntrada);
		spnMinutosEntrada.setBounds(165, 71, 36, 20);
		spnMinutosEntrada.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				minEntrMod = true;
			}
		});
		contentPane.add(spnMinutosEntrada);

		JButton btnSetHoraEntrada = new JButton("Establecer hora entrada");
		btnSetHoraEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerHoraEntrada();
			}
		});
		btnSetHoraEntrada.setBounds(273, 70, 158, 30);
		contentPane.add(btnSetHoraEntrada);

		SpinnerModel smHoraSalida = new SpinnerNumberModel(00, 00, 23, 1);
		spnHoraSalida = new JSpinner(smHoraSalida);
		spnHoraSalida.setBounds(118, 116, 37, 20);
		spnHoraSalida.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				horaSalMod = true;
			}
		});
		contentPane.add(spnHoraSalida);

		JLabel lblHoraSalida = new JLabel("Hora de salida");
		lblHoraSalida.setBounds(10, 121, 98, 14);
		contentPane.add(lblHoraSalida);

		JLabel lblDosPuntosSalida = new JLabel(":");
		lblDosPuntosSalida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDosPuntosSalida.setBounds(158, 117, 46, 14);
		contentPane.add(lblDosPuntosSalida);

		SpinnerModel smMinutosSalida = new SpinnerNumberModel(00, 00, 59, 1);
		spnMinutosSalida = new JSpinner(smMinutosSalida);
		spnMinutosSalida.setBounds(168, 116, 36, 20);
		spnMinutosSalida.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				minSalMod = true;
			}
		});
		contentPane.add(spnMinutosSalida);

		JButton btnSetHoraSalida = new JButton("Establecer hora salida");
		btnSetHoraSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerHoraSalida();
			}
		});
		btnSetHoraSalida.setBounds(273, 111, 158, 30);
		contentPane.add(btnSetHoraSalida);

		JButton btnCerrarCita = new JButton("Cerrar cita");
		btnCerrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarCita();
			}
		});
		btnCerrarCita.setBounds(435, 312, 89, 23);
		contentPane.add(btnCerrarCita);
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

}
