package gui.Administrador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdministrador extends JFrame {

	private JPanel contentPane;
	private JButton btCrearCita;
	private JLabel lblNewLabel;
	private JButton btHorarios;



	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtCrearCita());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtHorarios());
	}
	private JButton getBtCrearCita() {
		if (btCrearCita == null) {
			btCrearCita = new JButton("Crear cita");
			btCrearCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaCitasA ven = new VentanaCitasA();
		    		ven.setVisible(true);
				}
			});
			btCrearCita.setBounds(10, 119, 136, 45);
		}
		return btCrearCita;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Zona administrador");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			lblNewLabel.setBounds(135, 30, 182, 45);
		}
		return lblNewLabel;
	}
	private JButton getBtHorarios() {
		if (btHorarios == null) {
			btHorarios = new JButton("AsignarHorarios");
			btHorarios.setBounds(288, 119, 136, 45);
		}
		return btHorarios;
	}
}
