package gui.citas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.medico.MedicoRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;

public class AsignarVacuna extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnPaciente;
    private JLabel lblNombrePaciente;
    private JTextField textNombrePaciente;
    private JLabel lblDNI;
    private JTextField textDNIPaciente;
    private JButton btnBuscarPaciente;
    private JLabel lblTutorPaciente;
    private JTextField textTutorPaciente;
    private JTextField textNombreMedico;
    private JTextField textLicencia;
    private JButton btnAniadir;
    private JButton btnEliminar;
    private JList<PacienteRecord> listPacientesxMedico;

    private PacienteRecord paciente = new PacienteRecord();

    private static LocalDate today = LocalDate.now();
    private Optional<MedicoRecord> medicoRecord = null;
    private JPanel pnVacuna;
    private JLabel lblDosis;
    private JPanel panelDiaVacuna;
    private JSpinner spinnerDiaInicio;
    private JLabel lblDe;
    private JSpinner spinnerMesInicio;
    private JLabel lblDel;
    private JSpinner SpinnerAnioInicio;
    private JCheckBox chckbxRefuerzo;
    private JTextField textDosis;

    private VacunaRecord vacuna = new VacunaRecord();
    private MedicoRecord medico = new MedicoRecord();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AsignarVacuna frame = new AsignarVacuna();
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
    public AsignarVacuna() {
	setTitle("Asignación de medicos de cabecera");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 613, 232);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPnPaciente());
	contentPane.add(getBtnBuscarPaciente());
	contentPane.add(getBtnAniadir());
	contentPane.add(getBtnEliminar());
	contentPane.add(getPnVacuna());
	contentPane.add(getPanelDiaVacuna());
    }

    private JPanel getPnPaciente() {
	if (pnPaciente == null) {
	    pnPaciente = new JPanel();
	    pnPaciente.setBorder(new TitledBorder(null, "Paciente",
		    TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    pnPaciente.setBounds(10, 11, 275, 106);
	    pnPaciente.setLayout(null);
	    pnPaciente.add(getLblNombrePaciente());
	    pnPaciente.add(getTextNombrePaciente());
	    pnPaciente.add(getLblDNI());
	    pnPaciente.add(getTextDNIPaciente());
	    pnPaciente.add(getLblTutorPaciente());
	    pnPaciente.add(getTextTutorPaciente());
	}
	return pnPaciente;
    }

    private JLabel getLblNombrePaciente() {
	if (lblNombrePaciente == null) {
	    lblNombrePaciente = new JLabel("Nombre:");
	    lblNombrePaciente.setBounds(10, 24, 70, 14);
	}
	return lblNombrePaciente;
    }

    private JTextField getTextNombrePaciente() {
	if (textNombrePaciente == null) {
	    textNombrePaciente = new JTextField();
	    textNombrePaciente.setBounds(65, 21, 200, 20);
	    textNombrePaciente.setEditable(false);
	    textNombrePaciente.setColumns(10);
	}
	return textNombrePaciente;
    }

    private JLabel getLblDNI() {
	if (lblDNI == null) {
	    lblDNI = new JLabel("DNI:");
	    lblDNI.setBounds(10, 49, 51, 14);
	}
	return lblDNI;
    }

    private JTextField getTextDNIPaciente() {
	if (textDNIPaciente == null) {
	    textDNIPaciente = new JTextField();
	    textDNIPaciente.setEditable(false);
	    textDNIPaciente.setBounds(40, 46, 225, 20);
	    textDNIPaciente.setColumns(10);
	}
	return textDNIPaciente;
    }

    private JButton getBtnBuscarPaciente() {
	if (btnBuscarPaciente == null) {
	    btnBuscarPaciente = new JButton("Buscar paciente");
	    btnBuscarPaciente.setBackground(new Color(0, 255, 0));
	    btnBuscarPaciente.setBounds(130, 128, 155, 23);
	}
	return btnBuscarPaciente;
    }

    private JLabel getLblTutorPaciente() {
	if (lblTutorPaciente == null) {
	    lblTutorPaciente = new JLabel("Tutor:");
	    lblTutorPaciente.setBounds(10, 74, 51, 14);
	}
	return lblTutorPaciente;
    }

    private JTextField getTextTutorPaciente() {
	if (textTutorPaciente == null) {
	    textTutorPaciente = new JTextField();
	    textTutorPaciente.setEditable(false);
	    textTutorPaciente.setColumns(10);
	    textTutorPaciente.setBounds(50, 71, 215, 20);
	}
	return textTutorPaciente;
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

    private int indicarMes() {
	String[] mes = new java.text.DateFormatSymbols().getMonths();
	for (int i = 0; i <= mes.length - 1; i++) {
	    if (getSpinnerMesInicio().getValue()
		    .toString() == (mes[i].toString())) {
		return i;
	    }
	}
	return 0;
    }

    private Timestamp indicarInicio() {
	int anio = Integer
		.parseInt(getSpinnerAnioInicio().getValue().toString());
	int dia = (int) getSpinnerDiaInicio().getValue();
	dia = arreglarDiaMes(anio, indicarMes() + 1, dia);
	@SuppressWarnings("deprecation")
	Timestamp fecha = new Timestamp(anio - 1900, indicarMes(), dia, 0, 0, 0,
		0);
	return fecha;
    }

    private int arreglarDiaMes(int anio, int mes, int dia) {
	if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
	    if (dia == 31)
		dia = 30;
	}
	if ((mes == 2) && (anio % 4 != 0)) {
	    if (dia >= 28)
		dia = 28;
	}
	if ((mes == 2) && (anio % 4 == 0)) {
	    if (dia >= 29)
		dia = 29;

	}
	return dia;
    }

    private JButton getBtnAniadir() {
	if (btnAniadir == null) {
	    btnAniadir = new JButton("Añadir");
	    btnAniadir.setBackground(new Color(0, 255, 0));
	    btnAniadir.setBounds(498, 159, 89, 23);
	    btnAniadir.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    if (!getTextNombrePaciente().getText().isEmpty()
			    || !getTextNombrePaciente().getText().isBlank()) {
			JOptionPane.showMessageDialog(null,
				"Por favor, selecciona el paciente al que se le quiere asignar una vacuna",
				"Error - Paciente no seleccionado", 0);
		    } else if (!getTextDosis().getText().isEmpty()
			    || !getTextDosis().getText().isBlank()) {
			vacuna.setDosis(getTextDosis().getText());
			vacuna.setFechaAproximada(LocalDate.of(
				(Integer) getSpinnerAnioInicio().getValue(),
				(Integer) indicarMes(),
				(Integer) getSpinnerDiaInicio().getValue()));
			vacuna.setRefuerzo(getChckbxRefuerzo().isSelected());
			try {
			    vacuna.setIdHistorial(
				    BusinessFactory.forCitaService()
					    .buscarHistorial(paciente.getId())
					    .get().getIdHistorial());
			} catch (BusinessException e2) {
			    e2.printStackTrace();
			}
			if (getTextDNIPaciente().getText() == "No tiene") {
			    try {
				if (!getTextDosis().getText().isEmpty()
					&& !getTextDosis().getText().isBlank())

				    BusinessFactory.forCitaService()
					    .crearVacuna(vacuna);
			    } catch (BusinessException e1) {
				e1.printStackTrace();
			    }

			} else {
			    try {
				BusinessFactory.forAdminService()
					.asignarMedicoCabeceraDni(
						paciente.getDniPaciente(),
						medico.idMedico);
			    } catch (BusinessException e1) {
				e1.printStackTrace();
			    }
			}
		    } else {
			JOptionPane.showMessageDialog(null,
				"Por favor, indique el nombre de la dosis",
				"Error - Dosis vacuna", 0);
		    }

		}
	    });
	}
	return btnAniadir;
    }

    private JButton getBtnEliminar() {
	if (btnEliminar == null) {
	    btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBackground(new Color(255, 0, 0));
	    btnEliminar.setBounds(399, 159, 89, 23);
	    btnEliminar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (getLblDNI().getText() == "No tiene") {
			try {
			    BusinessFactory.forAdminService()
				    .eliminarMedicoCabeceraTutor(
					    paciente.getDniTutorLegal(),
					    paciente.getNombre(),
					    paciente.getApellidos());
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}

		    } else {
			try {
			    BusinessFactory.forAdminService()
				    .eliminarMedicoCabeceraDni(
					    paciente.getDniPaciente());
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}
		    }
		}
	    });
	}
	return btnEliminar;
    }

    private JPanel getPnVacuna() {
	if (pnVacuna == null) {
	    pnVacuna = new JPanel();
	    pnVacuna.setLayout(null);
	    pnVacuna.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Vacuna", TitledBorder.LEADING, TitledBorder.TOP, null,
		    new Color(0, 0, 0)));
	    pnVacuna.setBounds(312, 11, 275, 80);
	    pnVacuna.add(getLblDosis());
	    pnVacuna.add(getChckbxRefuerzo());
	    pnVacuna.add(getTextDosis());
	}
	return pnVacuna;
    }

    private JLabel getLblDosis() {
	if (lblDosis == null) {
	    lblDosis = new JLabel("Dosis:");
	    lblDosis.setBounds(10, 24, 84, 14);
	}
	return lblDosis;
    }

    private JPanel getPanelDiaVacuna() {
	if (panelDiaVacuna == null) {
	    panelDiaVacuna = new JPanel();
	    panelDiaVacuna.setLayout(null);
	    panelDiaVacuna.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Dia vacuna", TitledBorder.LEADING, TitledBorder.TOP, null,
		    new Color(0, 0, 0)));
	    panelDiaVacuna.setBounds(312, 97, 275, 54);
	    panelDiaVacuna.add(getSpinnerDiaInicio());
	    panelDiaVacuna.add(getLblDe());
	    panelDiaVacuna.add(getSpinnerMesInicio());
	    panelDiaVacuna.add(getLblDel());
	    panelDiaVacuna.add(getSpinnerAnioInicio());
	}
	return panelDiaVacuna;
    }

    private JSpinner getSpinnerDiaInicio() {
	if (spinnerDiaInicio == null) {
	    SpinnerModel sm = new SpinnerNumberModel(today.getDayOfMonth(), 1,
		    31, 1);
	    spinnerDiaInicio = new JSpinner(sm);
	    spinnerDiaInicio.setBounds(28, 21, 39, 20);
	}
	return spinnerDiaInicio;
    }

    private JLabel getLblDe() {
	if (lblDe == null) {
	    lblDe = new JLabel("de");
	    lblDe.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDe.setBounds(71, 24, 21, 14);
	}
	return lblDe;
    }

    private JSpinner getSpinnerMesInicio() {
	if (spinnerMesInicio == null) {
	    String[] mes = new java.text.DateFormatSymbols().getMonths();
	    SpinnerListModel modelMes = new SpinnerListModel(mes);
	    spinnerMesInicio = new JSpinner(modelMes);
	    spinnerMesInicio.setBounds(91, 21, 94, 20);
	}
	return spinnerMesInicio;
    }

    private JLabel getLblDel() {
	if (lblDel == null) {
	    lblDel = new JLabel("del");
	    lblDel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDel.setBounds(183, 24, 30, 14);
	}
	return lblDel;
    }

    private JSpinner getSpinnerAnioInicio() {
	if (SpinnerAnioInicio == null) {
	    SpinnerModel sm = new SpinnerNumberModel(today.getYear(), 2022,
		    2099, 1);
	    SpinnerAnioInicio = new JSpinner(sm);
	    SpinnerAnioInicio.setBounds(212, 21, 53, 20);
	}
	return SpinnerAnioInicio;
    }

    private JCheckBox getChckbxRefuerzo() {
	if (chckbxRefuerzo == null) {
	    chckbxRefuerzo = new JCheckBox("Refuerzo");
	    chckbxRefuerzo.setHorizontalAlignment(SwingConstants.LEFT);
	    chckbxRefuerzo.setBounds(10, 48, 255, 23);
	}
	return chckbxRefuerzo;
    }

    private JTextField getTextDosis() {
	if (textDosis == null) {
	    textDosis = new JTextField();
	    textDosis.setBounds(57, 21, 208, 20);
	    textDosis.setColumns(10);
	}
	return textDosis;
    }
}
