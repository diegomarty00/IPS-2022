package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setTitle("GPTo - Inicio de Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTítulo = new JLabel("GPTo");
		lblTítulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTítulo.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblTítulo.setBounds(10, 34, 459, 35);
		contentPane.add(lblTítulo);
		
		JLabel lblTituloCompleto = new JLabel("Gestor de Pacientes Total");
		lblTituloCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloCompleto.setBounds(10, 70, 459, 14);
		contentPane.add(lblTituloCompleto);
		
		JButton btnAdmin = new JButton("Administrador");
		btnAdmin.setBounds(82, 130, 125, 50);
		contentPane.add(btnAdmin);
		
		JButton btnMetico = new JButton("M\u00E9dico");
		btnMetico.setBounds(279, 130, 125, 50);
		contentPane.add(btnMetico);
		
		JLabel lblSesion = new JLabel("Inicio de sesi\u00F3n");
		lblSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSesion.setBounds(10, 95, 459, 24);
		contentPane.add(lblSesion);
	}
}
