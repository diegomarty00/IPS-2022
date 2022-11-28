package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public class VerJornadasLaborales extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnMedico;
    private JLabel lblNombreMedico;
    private JTextField textNombreMedico;
    private JLabel lblLicencia;
    private JTextField textLicencia;
    private JList<PacienteRecord> listPacientesxMedico;

    private PacienteRecord paciente;
    private MedicoRecord medico;
    private JComboBox cbNombreMedico;
    private JComboBox cbLicenciaMedico;

    private static LocalDate today = LocalDate.now();
    private Optional<MedicoRecord> medicoRecord = null;
    private JButton btnAtras;
    private JButton btnVerHorario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    VerJornadasLaborales frame = new VerJornadasLaborales();
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
    public VerJornadasLaborales() {
	setTitle("Asignación de medicos de cabecera");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 492, 163);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPnMedico());
	contentPane.add(getBtnAtras());
	contentPane.add(getBtnVerHorario());
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
	    pnMedico.setBounds(10, 24, 310, 85);
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
	    cbNombreMedico.setBounds(74, 21, 226, 20);
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
	    cbLicenciaMedico.setBounds(74, 49, 226, 20);
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

    private JButton getBtnAtras() {
	if (btnAtras == null) {
	    btnAtras = new JButton("Retroceder");
	    btnAtras.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btnAtras.setBackground(Color.RED);
	    btnAtras.setBounds(330, 71, 136, 23);
	}
	return btnAtras;
    }

    private JButton getBtnVerHorario() {
	if (btnVerHorario == null) {
	    btnVerHorario = new JButton("Ver horario");
	    btnVerHorario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    try {
			medicoRecord = BusinessFactory.forAdminService()
				.buscarMedico(Integer.parseInt(cbLicenciaMedico
					.getSelectedItem().toString()));
		    } catch (NumberFormatException e1) {
			e1.printStackTrace();
		    } catch (BusinessException e1) {
			e1.printStackTrace();
		    }
		    VentanaJornadaMedico frame = new VentanaJornadaMedico(
			    medicoRecord.get());
		    frame.setVisible(true);
		}
	    });
	    btnVerHorario.setBackground(new Color(0, 255, 0));
	    btnVerHorario.setBounds(330, 37, 136, 23);
	}
	return btnVerHorario;
    }
}
