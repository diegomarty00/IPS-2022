package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.citas.AsignarVacuna;
import gui.citas.VentanaCalendarioCitas;
import persistencia.PersistenceFactory;

public class VentanaEnfermero extends JFrame {

    private JPanel contentPane;
    private JButton btnMedico;
    private JButton btnEnfermero;
    private JLabel lblZonaMedico;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {

		try {

		    VentanaEnfermero frame = new VentanaEnfermero();
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
    public VentanaEnfermero() {
	setTitle("Menu enfemero");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 404, 202);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getBtnMedico());
	contentPane.add(getBtnEnfermero());
	contentPane.add(getLblZonaMedico());
    }

    public JButton getBtnMedico() {
	if (btnMedico == null) {
	    btnMedico = new JButton("Calendario de citas");
	    btnMedico.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    VentanaCalendarioCitas frame = new VentanaCalendarioCitas(
			    null,
			    PersistenceFactory.forEnfermero().findAll().get(0));
		    frame.setVisible(true);
		    dispose();
		}
	    });
	    btnMedico.setBounds(205, 77, 156, 50);
	}
	return btnMedico;
    }

    private JButton getBtnEnfermero() {
	if (btnEnfermero == null) {
	    btnEnfermero = new JButton("Asignar vacuna");
	    btnEnfermero.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    AsignarVacuna frame = new AsignarVacuna();
		    frame.setVisible(true);
		    dispose();
		}
	    });
	    btnEnfermero.setBounds(28, 77, 156, 50);
	}
	return btnEnfermero;
    }

    private JLabel getLblZonaMedico() {
	if (lblZonaMedico == null) {
	    lblZonaMedico = new JLabel("Zona enfermero");
	    lblZonaMedico.setHorizontalAlignment(SwingConstants.CENTER);
	    lblZonaMedico.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	    lblZonaMedico.setBounds(10, 21, 368, 45);
	}
	return lblZonaMedico;
    }
}
