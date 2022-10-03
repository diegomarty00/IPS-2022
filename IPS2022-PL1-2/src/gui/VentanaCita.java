package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class VentanaCita extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCita frame = new VentanaCita();
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
	public VentanaCita() {
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
		JSpinner spnHoraEntrada = new JSpinner(smHoraEntrada);
		spnHoraEntrada.setBounds(118, 71, 37, 20);
		contentPane.add(spnHoraEntrada);
		
		JLabel lblHoraEntrada = new JLabel("Hora de entrada");
		lblHoraEntrada.setBounds(10, 77, 98, 14);
		contentPane.add(lblHoraEntrada);
		
		JLabel lblDosPuntosEntrada = new JLabel(":");
		lblDosPuntosEntrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDosPuntosEntrada.setBounds(158, 73, 46, 14);
		contentPane.add(lblDosPuntosEntrada);
		
		SpinnerModel smMinutosEntrada = new SpinnerNumberModel(00, 00, 59, 1);
		JSpinner spnMinutosEntrada = new JSpinner(smMinutosEntrada);
		spnMinutosEntrada.setBounds(165, 71, 36, 20);
		contentPane.add(spnMinutosEntrada);
		
		JButton btnSetHoraEntrada = new JButton("Establecer hora entrada");
		btnSetHoraEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalTime time = LocalTime.now();
				spnHoraEntrada.setValue(time.getHour());
				spnMinutosEntrada.setValue(time.getMinute());
			}
		});
		btnSetHoraEntrada.setBounds(273, 70, 158, 30);
		contentPane.add(btnSetHoraEntrada);
		
		SpinnerModel smHoraSalida = new SpinnerNumberModel(00, 00, 23, 1);
		JSpinner spnHoraSalida = new JSpinner(smHoraSalida);
		spnHoraSalida.setBounds(118, 116, 37, 20);
		contentPane.add(spnHoraSalida);
		
		JLabel lblHoraSalida = new JLabel("Hora de salida");
		lblHoraSalida.setBounds(10, 121, 98, 14);
		contentPane.add(lblHoraSalida);
		
		JLabel lblDosPuntosSalida = new JLabel(":");
		lblDosPuntosSalida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDosPuntosSalida.setBounds(158, 117, 46, 14);
		contentPane.add(lblDosPuntosSalida);
		
		SpinnerModel smMinutosSalida = new SpinnerNumberModel(00, 00, 59, 1);
		JSpinner spnMinutosSalida = new JSpinner(smMinutosSalida);
		spnMinutosSalida.setBounds(168, 116, 36, 20);
		contentPane.add(spnMinutosSalida);
		
		JButton btnSetHoraSalida = new JButton("Establecer hora salida");
		btnSetHoraSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalTime time = LocalTime.now();
				spnHoraSalida.setValue(time.getHour());
				spnMinutosSalida.setValue(time.getMinute());
			}
		});
		btnSetHoraSalida.setBounds(273, 111, 158, 30);
		contentPane.add(btnSetHoraSalida);
	}
}
