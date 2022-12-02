package gui.citas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
    private JTextField textNombreMedico;
    private JTextField textLicencia;
    private JButton btnAniadir;
    private JButton btnEliminar;
    private JList<PacienteRecord> listPacientesxMedico;

    private Optional<PacienteRecord> paciente;

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
    private JComboBox cbDniPaciente;
    private JComboBox cbNombrePaciente;

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
	setTitle("Asignar vacuna");
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
	    pnPaciente.add(getcbDNIPaciente());
	    pnPaciente.add(getLblDNI());
	    pnPaciente.add(getcbNombrePaciente());
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
	    cbNombrePaciente.setBounds(65, 21, 200, 20);
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
	    cbDniPaciente.setBounds(40, 46, 225, 20);
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

    private JLabel getLblDNI() {
	if (lblDNI == null) {
	    lblDNI = new JLabel("DNI:");
	    lblDNI.setBounds(10, 49, 51, 14);
	}
	return lblDNI;
    }

    private JButton getBtnBuscarPaciente() {
	if (btnBuscarPaciente == null) {
	    btnBuscarPaciente = new JButton("Buscar paciente");
	    btnBuscarPaciente.setBackground(new Color(0, 255, 0));
	    btnBuscarPaciente.setBounds(130, 128, 155, 23);
	}
	return btnBuscarPaciente;
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
	    btnAniadir = new JButton("AÃ±adir");
	    btnAniadir.setBackground(new Color(0, 255, 0));
	    btnAniadir.setBounds(498, 159, 89, 23);
	    btnAniadir.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		    if (!getTextDosis().getText().isEmpty()
			    || !getTextDosis().getText().isBlank()) {
			vacuna.setDosis(getTextDosis().getText());
			vacuna.setFechaAproximada(LocalDate.of(
				(Integer) getSpinnerAnioInicio().getValue(),
				(Integer) indicarMes() + 1,
				(Integer) getSpinnerDiaInicio().getValue()));
			vacuna.setRefuerzo(getChckbxRefuerzo().isSelected());
			try {
			    Optional<PacienteRecord> pac = BusinessFactory
				    .forAdminService()
				    .buscarPacienteDni(cbDniPaciente
					    .getSelectedItem().toString());

			    vacuna.setIdHistorial(
				    BusinessFactory.forCitaService()
					    .buscarHistorial(pac.get().getId())
					    .get().getIdHistorial());
			} catch (BusinessException e2) {
			    e2.printStackTrace();
			}
			try {
			    if (!getTextDosis().getText().isEmpty()
				    && !getTextDosis().getText().isBlank())

				vacuna.setDosis(getTextDosis().getText());
			    BusinessFactory.forCitaService()
				    .crearVacuna(vacuna);
			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
				"Se le ha añadido la vacuna al calendario",
				"Calendario actualizados", 1);

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
	    btnEliminar = new JButton("Cancelar");
	    btnEliminar.setBackground(new Color(255, 0, 0));
	    btnEliminar.setBounds(399, 159, 89, 23);
	    btnEliminar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
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
