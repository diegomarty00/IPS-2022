package gui;

import java.sql.Connection;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.Administrador.VentanaAdministrador;
import gui.Administrador.VentanaCitasA;
import gui.citas.VentanaCalendarioCitas;
import util.jdbc.Jdbc;
import util.mail.EnviarMail;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

    private JPanel contentPane;
    private JLabel lblTitulo;
    private JLabel lblTituloCompleto;
    private JLabel lblInicioSesion;
    private JButton btnAdmin;
    private JButton btnMedico;

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
	contentPane.add(getLblTitulo());
	contentPane.add(getLblTituloCompleto());
	contentPane.add(getLblInicioSesion());
	contentPane.add(getBtnAdmin());
	contentPane.add(getBtnMedico());
    }

    public JLabel getLblTitulo() {
	if (lblTitulo == null) {
	    lblTitulo = new JLabel("GPTo");
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 24));
	    lblTitulo.setBounds(0, 0, 483, 44);
	}
	return lblTitulo;
    }

    public JLabel getLblTituloCompleto() {
	if (lblTituloCompleto == null) {
	    lblTituloCompleto = new JLabel("Gestion Paciente Total");
	    lblTituloCompleto.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTituloCompleto.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTituloCompleto.setBounds(10, 65, 473, 29);
	}
	return lblTituloCompleto;
    }

    public JLabel getLblInicioSesion() {
	if (lblInicioSesion == null) {
	    lblInicioSesion = new JLabel("Inicio Sesi\u00F3n");
	    lblInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
	    lblInicioSesion.setBounds(10, 105, 463, 14);
	}
	return lblInicioSesion;
    }

    public JButton getBtnAdmin() {
	if (btnAdmin == null) {
	    btnAdmin = new JButton("Administrador");
	    btnAdmin.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		VentanaAdministrador frame = new VentanaAdministrador();
				frame.setVisible(true);
				setVisible(false);
	    	}
	    });
	    btnAdmin.setBounds(90, 130, 125, 50);
	}
	return btnAdmin;
    }

    public JButton getBtnMedico() {
	if (btnMedico == null) {
	    btnMedico = new JButton("Medico");
	    btnMedico.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		VentanaCalendarioCitas frame = new VentanaCalendarioCitas();
				frame.setVisible(true);
				setVisible(false);
	    	}
	    });
	    btnMedico.setBounds(269, 130, 125, 50);
	}
	return btnMedico;
    }
}
