package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public class BusquedaPaciente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNombrePaciente;
    private JTextField textNombrePaciente;
    private JLabel lblDNI;
    private JTextField textDNIPaciente;
    private JButton btnConfirmar;
    private JLabel lblTutorPaciente;
    private JTextField textTutorPaciente;
    private JTextField textNombreMedico;
    private JTextField textLicencia;
    private JList<PacienteRecord> listPacientes;

    private PacienteRecord paciente;
    private MedicoRecord medico;

    private List<PacienteRecord> pacientes;

    private static LocalDate today = LocalDate.now();
    private Optional<MedicoRecord> medicoRecord = null;
    private JButton btnAtras;
    private JLabel lbApellidos;
    private JTextField textApellidos;
    private JLabel lblTutorPacienteDni;
    private JTextField textDNITutor;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textDniPaciente;
    private JTextField textNombreTutor;
    private JTextField textDniTutor;
    private JLabel lblListaDePacientes;
    private JPanel panel;
    private JButton btnBuscarTutor;
    private JButton btnBuscarDni;
    private JButton btnBuscarNombre;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    BusquedaPaciente frame = new BusquedaPaciente();
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
    public BusquedaPaciente() {
	setTitle("Asignación de medicos de cabecera");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 562, 292);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	try {
	    pacientes = BusinessFactory.forAdminService().buscarPacientes();
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getBtnConfirmar());
	contentPane.add(getBtnAtras());
	contentPane.add(getListPacientes());
	contentPane.add(getLblListaDePacientes());
	contentPane.add(getBtnBuscarTutor());
	contentPane.add(getLblNombrePaciente());
	contentPane.add(getTextNombre());
	contentPane.add(getLbApellidos());
	contentPane.add(getTextApellido());
	contentPane.add(getTextDniPaciente());
	contentPane.add(getLblDNI());
	contentPane.add(getTextNombreTutor());
	contentPane.add(getTextDniTutor());
	contentPane.add(getLblTutorPacienteDni());
	contentPane.add(getLblTutorPaciente());
	contentPane.add(getBtnBuscarDni());
	contentPane.add(getBtnBuscarNombre());
    }

    private JLabel getLblNombrePaciente() {
	if (lblNombrePaciente == null) {
	    lblNombrePaciente = new JLabel("Nombre:");
	    lblNombrePaciente.setBounds(10, 14, 70, 14);
	}
	return lblNombrePaciente;
    }

    private JLabel getLblDNI() {
	if (lblDNI == null) {
	    lblDNI = new JLabel("DNI:");
	    lblDNI.setBounds(10, 106, 51, 14);
	}
	return lblDNI;
    }

    private JButton getBtnConfirmar() {
	if (btnConfirmar == null) {
	    btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.setBackground(new Color(0, 255, 0));
	    btnConfirmar.setBounds(414, 220, 122, 23);
	}
	return btnConfirmar;
    }

    private JLabel getLblTutorPaciente() {
	if (lblTutorPaciente == null) {
	    lblTutorPaciente = new JLabel("Tutor:");
	    lblTutorPaciente.setBounds(10, 167, 51, 14);
	}
	return lblTutorPaciente;
    }

    private JTextField getTextNombreMedico() {
	if (textNombreMedico == null) {
	    textNombreMedico = new JTextField();
	    textNombreMedico.setEditable(false);
	    textNombreMedico.setColumns(10);
	    textNombreMedico.setBounds(74, 21, 168, 20);
	}
	return textNombreMedico;
    }

    private JTextField getTextLicencia() {
	if (textLicencia == null) {
	    textLicencia = new JTextField();
	    textLicencia.setEditable(false);
	    textLicencia.setColumns(10);
	    textLicencia.setBounds(74, 46, 168, 20);
	}
	return textLicencia;
    }

    private JButton getBtnAtras() {
	if (btnAtras == null) {
	    btnAtras = new JButton("Cancelar");
	    btnAtras.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btnAtras.setBackground(Color.RED);
	    btnAtras.setBounds(282, 220, 122, 23);
	}
	return btnAtras;
    }

    private JLabel getLbApellidos() {
	if (lbApellidos == null) {
	    lbApellidos = new JLabel("Apellidos:");
	    lbApellidos.setBounds(10, 45, 70, 14);
	}
	return lbApellidos;
    }

    private JLabel getLblTutorPacienteDni() {
	if (lblTutorPacienteDni == null) {
	    lblTutorPacienteDni = new JLabel("DNI Tutor:");
	    lblTutorPacienteDni.setBounds(10, 192, 70, 14);
	}
	return lblTutorPacienteDni;
    }

    private JTextField getTextNombre() {
	if (textNombre == null) {
	    textNombre = new JTextField();
	    textNombre.setBounds(73, 11, 194, 20);
	    textNombre.setColumns(10);
	}
	return textNombre;
    }

    private JTextField getTextApellido() {
	if (textApellido == null) {
	    textApellido = new JTextField();
	    textApellido.setBounds(83, 42, 184, 20);
	    textApellido.setColumns(10);
	}
	return textApellido;
    }

    private JTextField getTextDniPaciente() {
	if (textDniPaciente == null) {
	    textDniPaciente = new JTextField();
	    textDniPaciente.setBounds(45, 103, 222, 20);
	    textDniPaciente.setColumns(10);
	}
	return textDniPaciente;
    }

    private JTextField getTextNombreTutor() {
	if (textNombreTutor == null) {
	    textNombreTutor = new JTextField();
	    textNombreTutor.setBounds(55, 164, 212, 20);
	    textNombreTutor.setColumns(10);
	}
	return textNombreTutor;
    }

    private JTextField getTextDniTutor() {
	if (textDniTutor == null) {
	    textDniTutor = new JTextField();
	    textDniTutor.setBounds(73, 189, 194, 20);
	    textDniTutor.setColumns(10);
	}
	return textDniTutor;
    }

    private JLabel getLblListaDePacientes() {
	if (lblListaDePacientes == null) {
	    lblListaDePacientes = new JLabel("Lista de pacientes");
	    lblListaDePacientes.setHorizontalAlignment(SwingConstants.CENTER);
	    lblListaDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblListaDePacientes.setBounds(297, 0, 239, 43);
	}
	return lblListaDePacientes;
    }

    private JList<PacienteRecord> getListPacientes() {
	if (listPacientes == null) {
	    listaPacientes();
	}
	return listPacientes;
    }

    private void listaPacientes() {
	DefaultListModel modelo = new DefaultListModel<>();

	for (PacienteRecord paciente : pacientes) {

	    if (paciente.getDniPaciente() == null)
		modelo.addElement(
			paciente.getNombre() + " " + paciente.getApellidos()
				+ " - Tutor: " + paciente.getTutorLegal());
	    else
		modelo.addElement(
			paciente.getNombre() + " " + paciente.getApellidos()
				+ " - " + paciente.getDniPaciente());

	}
	listPacientes = new JList<PacienteRecord>(modelo);
	listPacientes.setBounds(297, 33, 239, 168);

    }

    private JButton getBtnBuscarTutor() {
	if (btnBuscarTutor == null) {
	    btnBuscarTutor = new JButton("Buscar por Tutor");
	    btnBuscarTutor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    DefaultListModel modelo = new DefaultListModel<>();
		    for (PacienteRecord paciente : pacientes) {
			if (!getTextNombreTutor().getText().isEmpty()
				&& !getTextNombreTutor().getText().isBlank()) {
			    if (getTextNombreTutor().getText() == paciente
				    .getTutorLegal())
				modelo.addElement(paciente.getNombre() + " "
					+ paciente.getApellidos() + " - Tutor: "
					+ paciente.getTutorLegal());
			} else if (!getTextDniTutor().getText().isEmpty()
				&& !getTextDniTutor().getText().isBlank()) {
			    if (getTextDniTutor().getText() == paciente
				    .getDniTutorLegal())
				modelo.addElement(paciente.getNombre() + " "
					+ paciente.getApellidos() + " - Tutor: "
					+ paciente.getTutorLegal());
			}
		    }
		    listPacientes = new JList<PacienteRecord>(modelo);
		}
	    });
	    btnBuscarTutor.setBackground(Color.GREEN);
	    btnBuscarTutor.setBounds(55, 220, 174, 23);
	}
	return btnBuscarTutor;
    }

    private JButton getBtnBuscarDni() {
	if (btnBuscarDni == null) {
	    btnBuscarDni = new JButton("Buscar por DNI");
	    btnBuscarDni.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    DefaultListModel modelo = new DefaultListModel<>();
		    for (PacienteRecord paciente : pacientes) {
			if (!getTextDniPaciente().getText().isEmpty()
				&& !getTextDniPaciente().getText().isBlank()) {
			    if (getTextDniPaciente().getText() == paciente
				    .getDniPaciente())
				modelo.addElement(paciente.getNombre() + " "
					+ paciente.getApellidos() + " - "
					+ paciente.getDniPaciente());
			}
		    }
		    listPacientes = new JList<PacienteRecord>(modelo);
		}
	    });
	    btnBuscarDni.setBackground(Color.GREEN);
	    btnBuscarDni.setBounds(55, 130, 174, 23);
	}
	return btnBuscarDni;
    }

    private JButton getBtnBuscarNombre() {
	if (btnBuscarNombre == null) {
	    btnBuscarNombre = new JButton("Buscar por nombre");
	    btnBuscarNombre.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    DefaultListModel modelo = new DefaultListModel<>();
		    for (PacienteRecord paciente : pacientes) {
			if (!getTextApellido().getText().isEmpty()
				&& !getTextApellido().getText().isBlank()) {
			    if (getTextApellido().getText() == paciente
				    .getApellidos())
				if (paciente.getDniPaciente() == null)
				    modelo.addElement(paciente.getNombre() + " "
					    + paciente.getApellidos()
					    + " - Tutor: "
					    + paciente.getTutorLegal());
				else
				    modelo.addElement(paciente.getNombre() + " "
					    + paciente.getApellidos() + " - "
					    + paciente.getDniPaciente());
			} else if (!getTextNombre().getText().isEmpty()
				&& !getTextNombre().getText().isBlank()) {
			    if (getTextNombre().getText() == paciente
				    .getNombre())
				if (paciente.getDniPaciente() == null)
				    modelo.addElement(paciente.getNombre() + " "
					    + paciente.getApellidos()
					    + " - Tutor: "
					    + paciente.getTutorLegal());
				else
				    modelo.addElement(paciente.getNombre() + " "
					    + paciente.getApellidos() + " - "
					    + paciente.getDniPaciente());
			}
		    }
		    listPacientes = new JList<PacienteRecord>(modelo);
		}
	    });
	    btnBuscarNombre.setBackground(Color.GREEN);
	    btnBuscarNombre.setBounds(55, 69, 174, 23);
	}
	return btnBuscarNombre;
    }
}
