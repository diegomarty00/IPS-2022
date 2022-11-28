package gui.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.BusinessException;

public class VentanaAdministrador extends JFrame {

    private JPanel contentPane;
    private JButton btCrearCita;
    private JLabel lblNewLabel;
    private JButton btJornadas;
    private JButton btModificarDatos_1;
    private JButton btModificarCitas;
    private JButton btCitasSolicitadas;
    private JButton btnCrearJornadas;
    private JButton btnAsignarJornadasComunes;
    private JButton btnMedicosDeCabecera;
    private JButton btnVerJornadas;

    /**
     * Create the frame.
     */
    public VentanaAdministrador() {
	setTitle("Menu Administrador");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 450, 403);
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
	contentPane.add(getBtnCrearJornadas());
	contentPane.add(getBtnAsignarJornadasComunes());
	contentPane.add(getBtnMedicosDeCabecera());
	contentPane.add(getBtnVerJornadas());
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
	    btJornadas = new JButton("Asignar Jornadas manuales");
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
	    btModificarCitas.setBounds(232, 198, 192, 45);
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

    private JButton getBtnCrearJornadas() {
	if (btnCrearJornadas == null) {
	    btnCrearJornadas = new JButton("Crear Jornadas");
	    btnCrearJornadas.setBounds(124, 308, 192, 45);
	    btnCrearJornadas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    CrearJornadasComunes frame;
		    try {
			frame = new CrearJornadasComunes();
			frame.setVisible(true);
		    } catch (BusinessException e1) {
			e1.printStackTrace();
		    }

		}
	    });
	}
	return btnCrearJornadas;
    }

    private JButton getBtnAsignarJornadasComunes() {
	if (btnAsignarJornadasComunes == null) {
	    btnAsignarJornadasComunes = new JButton("Asignar Jornadas comunes");
	    btnAsignarJornadasComunes.setBounds(232, 142, 192, 45);
	    btnAsignarJornadasComunes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    AsignarcionJornadasComunes frame;
		    try {
			frame = new AsignarcionJornadasComunes();
			frame.setVisible(true);
		    } catch (BusinessException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	}
	return btnAsignarJornadasComunes;
    }

    private JButton getBtnMedicosDeCabecera() {
	if (btnMedicosDeCabecera == null) {
	    btnMedicosDeCabecera = new JButton("Medicos de Cabecera");
	    btnMedicosDeCabecera.setBounds(20, 254, 192, 45);
	    btnMedicosDeCabecera.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    AsignarMedicoCabecera frame = new AsignarMedicoCabecera();
		    frame.setVisible(true);
		}
	    });
	}
	return btnMedicosDeCabecera;
    }

    private JButton getBtnVerJornadas() {
	if (btnVerJornadas == null) {
	    btnVerJornadas = new JButton("Ver jornadas");
	    btnVerJornadas.setBounds(232, 254, 192, 45);
	    btnVerJornadas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    VerJornadasLaborales frame = new VerJornadasLaborales();
		    frame.setVisible(true);
		}
	    });
	}
	return btnVerJornadas;
    }
}
