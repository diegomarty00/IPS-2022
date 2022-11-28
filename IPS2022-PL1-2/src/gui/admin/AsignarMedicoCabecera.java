package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public class AsignarMedicoCabecera extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnPaciente;
    private JLabel lblNombrePaciente;
    private JTextField textNombrePaciente;
    private JLabel lblDNI;
    private JTextField textDNIPaciente;
    private JButton btnBuscarPaciente;
    private JPanel pnMedico;
    private JLabel lblNombreMedico;
    private JTextField textNombreMedico;
    private JLabel lblLicencia;
    private JTextField textLicencia;
    private JButton btnAniadir;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JList<PacienteRecord> listPacientesxMedico;
    private JLabel lblNewLabel;

    private Optional<PacienteRecord> paciente;
    private MedicoRecord medico;
    private JComboBox cbNombreMedico;
    private JComboBox cbLicenciaMedico;

    private static LocalDate today = LocalDate.now();
    private Optional<MedicoRecord> medicoRecord = null;
    private JButton btnAtras;
    private AsignarMedicoCabecera frame;
    private JComboBox cbNombrePaciente;
    private JComboBox cbDniPaciente;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AsignarMedicoCabecera frame = new AsignarMedicoCabecera();
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
    public AsignarMedicoCabecera() {
	frame = this;
	setTitle("Asignacion de medicos de cabecera");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 628, 366);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPnPaciente());
	contentPane.add(getBtnBuscarPaciente());
	contentPane.add(getPnMedico());
	contentPane.add(getBtnAniadir());
	contentPane.add(getBtnModificar());
	contentPane.add(getBtnEliminar());
	contentPane.add(getBtnAtras());
	contentPane.add(getListPacientesxMedico());
	contentPane.add(getLblNewLabel());
    }

    private JPanel getPnPaciente() {
	if (pnPaciente == null) {
	    pnPaciente = new JPanel();
	    pnPaciente.setBorder(new TitledBorder(null, "Paciente",
		    TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnPaciente.setBounds(10, 29, 252, 106);
	    pnPaciente.setLayout(null);
	    pnPaciente.add(getLblNombrePaciente());
	    pnPaciente.add(getcbNombrePaciente());
	    pnPaciente.add(getLblDNI());
	    pnPaciente.add(getcbDNIPaciente());
	}
	return pnPaciente;
    }

    private JLabel getLblNombrePaciente() {
	if (lblNombrePaciente == null) {
	    lblNombrePaciente = new JLabel("Nombre:");
	    lblNombrePaciente.setBounds(10, 37, 70, 14);
	}
	return lblNombrePaciente;
    }

    private JComboBox getcbNombrePaciente() {
	if (cbNombrePaciente == null) {
	    cbNombrePaciente = new JComboBox();
	    cbNombrePaciente.setEditable(true);
	    cbNombrePaciente.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    cbDniPaciente.setSelectedIndex(
			    cbNombrePaciente.getSelectedIndex());
		    try {
			paciente = BusinessFactory.forAdminService()
				.buscarPacienteDni(cbDniPaciente
					.getSelectedItem().toString());
		    } catch (NumberFormatException | BusinessException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	    cbNombrePaciente.setBounds(65, 34, 173, 20);
	    cbNombrePaciente.setRequestFocusEnabled(false);
	    DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	    List<PacienteRecord> l = new ArrayList<>();
	    try {
		l = BusinessFactory.forAdminService().buscarPacientes();
	    } catch (BusinessException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < l.size(); i++) {
		mod.addElement(
			l.get(i).getNombre() + " " + l.get(i).getApellidos());
	    }
	    cbNombrePaciente.setModel(mod);

	}
	return cbNombrePaciente;
    }

    private JLabel getLblDNI() {
	if (lblDNI == null) {
	    lblDNI = new JLabel("DNI:");
	    lblDNI.setBounds(10, 62, 51, 14);
	}
	return lblDNI;
    }

    private JComboBox getcbDNIPaciente() {
	if (cbDniPaciente == null) {
	    cbDniPaciente = new JComboBox();
	    cbDniPaciente.setEditable(true);
	    cbDniPaciente.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    cbNombrePaciente
			    .setSelectedIndex(cbDniPaciente.getSelectedIndex());
		    try {
			paciente = BusinessFactory.forAdminService()
				.buscarPacienteDni(cbDniPaciente
					.getSelectedItem().toString());
		    } catch (NumberFormatException | BusinessException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	    cbDniPaciente.setBounds(40, 59, 198, 20);
	    cbDniPaciente.setRequestFocusEnabled(false);
	    DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	    List<PacienteRecord> l = new ArrayList<>();
	    try {
		l = BusinessFactory.forAdminService().buscarPacientes();
	    } catch (BusinessException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < l.size(); i++) {
		mod.addElement(l.get(i).getDniPaciente());
	    }
	    cbDniPaciente.setModel(mod);

	}
	return cbDniPaciente;
    }

    private JButton getBtnBuscarPaciente() {
	if (btnBuscarPaciente == null) {
	    btnBuscarPaciente = new JButton("Buscar paciente");
	    btnBuscarPaciente.setBackground(new Color(0, 255, 0));
	    btnBuscarPaciente.setBounds(107, 146, 155, 23);
	    btnBuscarPaciente.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    BusquedaPaciente frame = new BusquedaPaciente();
		    frame.setVisible(true);
		    ;
		}
	    });
	}
	return btnBuscarPaciente;
    }

    private JPanel getPnMedico() {
	if (pnMedico == null) {
	    pnMedico = new JPanel();
	    pnMedico.setLayout(null);
	    pnMedico.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Medico", TitledBorder.LEADING, TitledBorder.TOP, null,
		    new Color(0, 0, 0)));
	    pnMedico.setBounds(10, 190, 252, 85);
	    pnMedico.add(getLblNombreMedico());
	    // pnMedico.add(getTextNombreMedico());
	    pnMedico.add(getLblLicencia());
	    pnMedico.add(getCbLicenciaMedico());
	    pnMedico.add(getCbNombreMedico());
	}
	return pnMedico;
    }

    private JLabel getLblNombreMedico() {
	if (lblNombreMedico == null) {
	    lblNombreMedico = new JLabel("Nombre:");
	    lblNombreMedico.setBounds(10, 24, 70, 14);
	}
	return lblNombreMedico;
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

    private JComboBox getCbNombreMedico() {
	if (cbNombreMedico == null) {
	    cbNombreMedico = new JComboBox();
	    cbNombreMedico.setEditable(true);
	    cbNombreMedico.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    cbLicenciaMedico.setSelectedIndex(
			    cbNombreMedico.getSelectedIndex());
		    try {
			medicoRecord = BusinessFactory.forAdminService()
				.buscarMedico(Integer.parseInt(cbLicenciaMedico
					.getSelectedItem().toString()));
		    } catch (NumberFormatException | BusinessException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	    cbNombreMedico.setBounds(74, 21, 168, 20);
	    cbNombreMedico.setRequestFocusEnabled(false);
	    DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	    List<MedicoRecord> l = new ArrayList<>();
	    try {
		l = BusinessFactory.forAdminService().buscarMedicos();
	    } catch (BusinessException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < l.size(); i++) {
		mod.addElement(l.get(i).nombre + " " + l.get(i).apellidos);
	    }
	    cbNombreMedico.setModel(mod);

	}
	return cbNombreMedico;
    }

    private JComboBox getCbLicenciaMedico() {
	if (cbLicenciaMedico == null) {
	    cbLicenciaMedico = new JComboBox();
	    cbLicenciaMedico.setEditable(true);
	    cbLicenciaMedico.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    cbNombreMedico.setSelectedIndex(
			    cbLicenciaMedico.getSelectedIndex());
		    try {
			medicoRecord = BusinessFactory.forAdminService()
				.buscarMedico(Integer.parseInt(cbLicenciaMedico
					.getSelectedItem().toString()));
		    } catch (NumberFormatException | BusinessException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	    cbLicenciaMedico.setBounds(74, 49, 168, 20);
	    cbLicenciaMedico.setRequestFocusEnabled(false);
	    DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	    List<MedicoRecord> l = new ArrayList<>();
	    try {
		l = BusinessFactory.forAdminService().buscarMedicos();
	    } catch (BusinessException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < l.size(); i++) {
		mod.addElement(l.get(i).idMedico);
	    }
	    cbLicenciaMedico.setModel(mod);
	}
	return cbLicenciaMedico;
    }

    private JLabel getLblLicencia() {
	if (lblLicencia == null) {
	    lblLicencia = new JLabel("Licencia:");
	    lblLicencia.setBounds(10, 49, 84, 14);
	}
	return lblLicencia;
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

    private JButton getBtnAniadir() {
	if (btnAniadir == null) {
	    btnAniadir = new JButton("A\u00F1adir");
	    btnAniadir.setBackground(new Color(0, 255, 0));
	    btnAniadir.setBounds(272, 109, 89, 23);
	    btnAniadir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (getLblDNI().getText() == "No tiene") {
			try {
			    BusinessFactory.forAdminService()
				    .asignarMedicoCabeceraTutor(
					    paciente.get().getDniTutorLegal(),
					    paciente.get().getNombre(),
					    paciente.get().getApellidos(),
					    Integer.parseInt(cbLicenciaMedico
						    .getSelectedItem()
						    .toString()));
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}

		    } else {
			try {
			    String cadena = cbDniPaciente.getSelectedItem()
				    .toString();
			    int culo = Integer.parseInt(cbLicenciaMedico
				    .getSelectedItem().toString());
			    BusinessFactory.forAdminService()
				    .asignarMedicoCabeceraDni(
					    cbDniPaciente.getSelectedItem()
						    .toString(),
					    Integer.parseInt(cbLicenciaMedico
						    .getSelectedItem()
						    .toString()));
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
				"Se han añadido el medico de cabecera correctamente.",
				"Asignacion realizada", 1);
		    }
		}
	    });
	}
	return btnAniadir;
    }

    private JButton getBtnModificar() {
	if (btnModificar == null) {
	    btnModificar = new JButton("Modificar");
	    btnModificar.setBackground(new Color(255, 140, 0));
	    btnModificar.setBounds(272, 146, 89, 23);
	    btnModificar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (getLblDNI().getText() == "No tiene") {
			try {
			    BusinessFactory.forAdminService()
				    .eliminarMedicoCabeceraTutor(
					    paciente.get().getDniTutorLegal(),
					    paciente.get().getNombre(),
					    paciente.get().getApellidos());

			    BusinessFactory.forAdminService()
				    .asignarMedicoCabeceraTutor(
					    paciente.get().getDniTutorLegal(),
					    paciente.get().getNombre(),
					    paciente.get().getApellidos(),
					    medico.idMedico);
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}

		    } else {
			try {
			    BusinessFactory.forAdminService()
				    .eliminarMedicoCabeceraDni(
					    paciente.get().getDniPaciente());

			    BusinessFactory.forAdminService()
				    .asignarMedicoCabeceraDni(
					    paciente.get().getDniPaciente(),
					    medico.idMedico);
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}
		    }
		}
	    });
	}
	return btnModificar;
    }

    private JButton getBtnEliminar() {
	if (btnEliminar == null) {
	    btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBackground(new Color(255, 0, 0));
	    btnEliminar.setBounds(272, 186, 89, 23);
	    btnEliminar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (getLblDNI().getText() == "No tiene") {
			try {
			    BusinessFactory.forAdminService()
				    .eliminarMedicoCabeceraTutor(
					    paciente.get().getDniTutorLegal(),
					    paciente.get().getNombre(),
					    paciente.get().getApellidos());
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}

		    } else {
			try {
			    BusinessFactory.forAdminService()
				    .eliminarMedicoCabeceraDni(
					    paciente.get().getDniPaciente());
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}
		    }
		}
	    });
	}
	return btnEliminar;
    }

    private JList<PacienteRecord> getListPacientesxMedico() {
	if (listPacientesxMedico == null) {
	    listaPacientesXMedico();
	}
	return listPacientesxMedico;
    }

    private void listaPacientesXMedico() {
	DefaultListModel modelo = null;
	List<PacienteRecord> pacientes = null;

	try {
	    modelo = new DefaultListModel<>();
	    pacientes = BusinessFactory.forAdminService().buscarPacientes();
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	for (PacienteRecord paciente : pacientes) {
	    try {
		if (paciente
			.getIdMedicoCabecera() == medicoRecord.get().idMedico) {
		    if (paciente.getDniPaciente() == null)
			modelo.addElement(paciente.getNombre() + " "
				+ paciente.getApellidos() + " - Sin DNI");
		    else
			modelo.addElement(paciente.getNombre() + " "
				+ paciente.getApellidos() + " - "
				+ paciente.getDniPaciente());
		}
	    } catch (Exception e) {
	    }
	}
	listPacientesxMedico = new JList<PacienteRecord>(modelo);
	listPacientesxMedico.setBounds(371, 55, 231, 220);

    }

    private JButton getBtnAtras() {
	if (btnAtras == null) {
	    btnAtras = new JButton("Retroceder");
	    btnAtras.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btnAtras.setBackground(Color.RED);
	    btnAtras.setBounds(466, 293, 136, 23);
	}
	return btnAtras;
    }

    private JLabel getLblNewLabel() {
	if (lblNewLabel == null) {
	    lblNewLabel = new JLabel("Lista de pacientes \rdel medico");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setBounds(371, 11, 231, 43);
	}
	return lblNewLabel;
    }
}
