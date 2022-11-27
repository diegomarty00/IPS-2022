package gui.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.BusinessException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VentanaAdministrador extends JFrame {

	private JPanel contentPane;
	private JButton btCrearCita;
	private JLabel lblNewLabel;
	private JButton btJornadas;
	private JButton btModificarDatos_1;
	private JButton btModificarCitas;
	private JButton btCitasSolicitadas;



	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setTitle("Menu Administrador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtCrearCita());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtJornadas());
		contentPane.add(getBtModificarDatos_1());
		contentPane.add(getBtModificarCitas());
		contentPane.add(getBtCitasSolicitadas());
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
			btCrearCita.setBounds(20, 86, 192, 45);
		}
		return btCrearCita;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Zona administrador");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			lblNewLabel.setBounds(10, 30, 414, 45);
		}
		return lblNewLabel;
	}
	private JButton getBtJornadas() {
		if (btJornadas == null) {
			btJornadas = new JButton("Asignar Jornadas");
			btJornadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AsignarcionJornadasLaborales ven;
					try {
						ven = new AsignarcionJornadasLaborales();
						ven.setVisible(true);
					} catch (BusinessException e1) {
						e1.printStackTrace();
					}
				}
			});
			btJornadas.setBounds(20, 142, 192, 45);
		}
		return btJornadas;
	}
	private JButton getBtModificarDatos_1() {
		if (btModificarDatos_1 == null) {
			btModificarDatos_1 = new JButton("Modificar informaci\u00F3n cita");
			btModificarDatos_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AsignarInformacionContactoCitas ven;
						ven = new AsignarInformacionContactoCitas();
						ven.setVisible(true);
				}
			});
			btModificarDatos_1.setBounds(20, 198, 192, 45);
		}
		return btModificarDatos_1;
	}
	private JButton getBtModificarCitas() {
		if (btModificarCitas == null) {
			btModificarCitas = new JButton("Modificar Cita o Cancelar");
			btModificarCitas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SelectorCitasModificar frame = new SelectorCitasModificar();
					frame.setVisible(true);
				}
			});
			btModificarCitas.setBounds(232, 142, 192, 45);
		}
		return btModificarCitas;
	}
	private JButton getBtCitasSolicitadas() {
		if (btCitasSolicitadas == null) {
			btCitasSolicitadas = new JButton("Citas Solicitadas");
			btCitasSolicitadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AceptarCitas frame = new AceptarCitas();
					frame.setVisible(true);
				}
			});
			btCitasSolicitadas.setBounds(232, 86, 192, 45);
		}
		return btCitasSolicitadas;
	}
}
