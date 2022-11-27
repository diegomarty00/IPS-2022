package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.admin.JornadaComunRecord;
import persistencia.admin.JornadaRecord;
import persistencia.medico.MedicoRecord;
import util.BusinessException;

public class CrearJornadasComunes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelDiaSemana;
    private JCheckBox chckbxLunes;
    private JCheckBox chckbxMartes;
    private JCheckBox chckbxMiercoles;
    private JCheckBox chckbxJueves;
    private JCheckBox chckbxViernes;
    private JCheckBox chckbxSabado;
    private JCheckBox chckbxDomingo;
    private JPanel panelHoraJornada;
    private JLabel lblDe_1_1;
    private JSpinner spinnerHoraInicio;
    private JLabel lblA;
    private JSpinner spinnerHoraFin;
    private JList<MedicoRecord> listResumen;
    private JLabel lblResumen;
    private JButton btnAsignar;
    private JButton btnCancelar;

    private static LocalDate today = LocalDate.now();
    private JSpinner spinnerMinutoInicio;
    private JLabel lblA_1;
    private JLabel lblA_1_1;
    private JSpinner spinnerMinutoFin;
    private List<MedicoRecord> medicos;
    private DefaultListModel<MedicoRecord> modelo = new DefaultListModel<>();
    private JLabel lblNNombreJornada;
    private JTextField textField;
    private List<JornadaComunRecord> jornada = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    CrearJornadasComunes frame = new CrearJornadasComunes();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     * 
     * @throws BusinessException
     */
    public CrearJornadasComunes() throws BusinessException {
	setResizable(false);
	setTitle("Asignaci\u00F3n Jornadas Laborales - Manual");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 666, 400);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPanelDiaSemana());
	contentPane.add(getPanelHoraJornada());
	contentPane.add(getListResumen());
	contentPane.add(getLblResumen());
	contentPane.add(getBtnAsignar());
	contentPane.add(getBtnCancelar());
	contentPane.add(getLblNNombreJornada());
	contentPane.add(getTextField());
    }

    private JPanel getPanelDiaSemana() {
	if (panelDiaSemana == null) {
	    panelDiaSemana = new JPanel();
	    panelDiaSemana.setBounds(21, 123, 319, 83);
	    panelDiaSemana
		    .setBorder(new TitledBorder(null, "D\u00EDas de la semana",

			    TitledBorder.LEFT, TitledBorder.TOP, null, null));
	    panelDiaSemana.add(getChckbxLunes());
	    panelDiaSemana.add(getChckbxMartes());
	    panelDiaSemana.add(getChckbxMiercoles());
	    panelDiaSemana.add(getChckbxJueves());
	    panelDiaSemana.add(getChckbxViernes());
	    panelDiaSemana.add(getChckbxSabado());
	    panelDiaSemana.add(getChckbxDomingo());
	}
	return panelDiaSemana;
    }

    private JCheckBox getChckbxLunes() {
	if (chckbxLunes == null) {
	    chckbxLunes = new JCheckBox("Lunes");
	}
	return chckbxLunes;
    }

    private JCheckBox getChckbxMartes() {
	if (chckbxMartes == null) {
	    chckbxMartes = new JCheckBox("Martes");
	}
	return chckbxMartes;
    }

    private JCheckBox getChckbxMiercoles() {
	if (chckbxMiercoles == null) {
	    chckbxMiercoles = new JCheckBox("Mi\u00E9rcoles");
	}
	return chckbxMiercoles;
    }

    private JCheckBox getChckbxJueves() {
	if (chckbxJueves == null) {
	    chckbxJueves = new JCheckBox("Jueves");
	}
	return chckbxJueves;
    }

    private JCheckBox getChckbxViernes() {
	if (chckbxViernes == null) {
	    chckbxViernes = new JCheckBox("Viernes");
	}
	return chckbxViernes;
    }

    private JCheckBox getChckbxSabado() {
	if (chckbxSabado == null) {
	    chckbxSabado = new JCheckBox("S\u00E1bado");
	}
	return chckbxSabado;
    }

    private JCheckBox getChckbxDomingo() {
	if (chckbxDomingo == null) {
	    chckbxDomingo = new JCheckBox("Domingo");
	}
	return chckbxDomingo;
    }

    private JPanel getPanelHoraJornada() {
	if (panelHoraJornada == null) {
	    panelHoraJornada = new JPanel();
	    panelHoraJornada.setBorder(new TitledBorder(

		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255),

			    new Color(160, 160, 160)),

		    "Horario de la jornada", TitledBorder.LEADING,
		    TitledBorder.TOP,

		    null, new Color(0, 0, 0)));
	    panelHoraJornada.setBounds(21, 239, 321, 54);
	    panelHoraJornada.setLayout(null);
	    panelHoraJornada.add(getLblDe_1_1());
	    panelHoraJornada.add(getspinnerHoraInicio());
	    panelHoraJornada.add(getLblA());
	    panelHoraJornada.add(getspinnerHoraFin());
	    panelHoraJornada.add(getspinnerMinutoInicio());
	    panelHoraJornada.add(getLblA_1());
	    panelHoraJornada.add(getLblA_1_1());
	    panelHoraJornada.add(getspinnerMinutoFin());
	}
	return panelHoraJornada;
    }

    private JLabel getLblDe_1_1() {
	if (lblDe_1_1 == null) {
	    lblDe_1_1 = new JLabel("De");
	    lblDe_1_1.setBounds(58, 26, 19, 14);
	}
	return lblDe_1_1;
    }

    private JSpinner getspinnerHoraInicio() {
	if (spinnerHoraInicio == null) {
	    SpinnerModel sm = new SpinnerNumberModel(9, 0, 23, 1);
	    spinnerHoraInicio = new JSpinner(sm);
	    spinnerHoraInicio.setBounds(78, 23, 39, 20);
	}
	return spinnerHoraInicio;
    }

    private JLabel getLblA() {
	if (lblA == null) {
	    lblA = new JLabel("a");
	    lblA.setHorizontalAlignment(SwingConstants.CENTER);
	    lblA.setBounds(163, 26, 13, 14);
	}
	return lblA;
    }

    private JSpinner getspinnerHoraFin() {
	if (spinnerHoraFin == null) {
	    SpinnerModel sm = new SpinnerNumberModel(9, 0, 23, 1);
	    spinnerHoraFin = new JSpinner(sm);
	    spinnerHoraFin.setBounds(173, 23, 39, 20);
	}
	return spinnerHoraFin;
    }

    private JList<MedicoRecord> getListResumen() throws BusinessException {
	if (listResumen == null) {

	    medicos = BusinessFactory.forAdminService().buscarMedicos();
	    for (MedicoRecord medico : medicos) {
		modelo.addElement(medico);
	    }
	    listResumen = new JList<MedicoRecord>(modelo);
	    /**
	     * listMedicos.addMouseListener(new MouseAdapter() {
	     * 
	     * @Override public void mousePressed(MouseEvent e) { CitaRecord
	     *           citaPulsada = (CitaRecord) listMedicos.getModel()
	     *           .getElementAt(
	     *           listMedicos.locationToIndex(e.getPoint())); } });
	     */

	    listResumen.setBounds(363, 42, 270, 276);
	}
	return listResumen;
    }

    private JLabel getLblResumen() {
	if (lblResumen == null) {
	    lblResumen = new JLabel("Resumen jornada");
	    lblResumen.setHorizontalAlignment(SwingConstants.CENTER);
	    lblResumen.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblResumen.setBounds(363, 6, 270, 25);
	}
	return lblResumen;
    }

    private JButton getBtnAsignar() {
	if (btnAsignar == null) {
	    btnAsignar = new JButton("Crear");
	    btnAsignar.addActionListener(new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
		    try {
			if (getListResumen().getSelectedValuesList()
				.size() == 0)
			    JOptionPane.showMessageDialog(null,
				    "Por favor, seleccione a alg�n m�dico al que quiera a�adirle una jornada",
				    "Error - M�dico no seleccionado", 0);
		    } catch (HeadlessException e2) {
			e2.printStackTrace();
		    } catch (BusinessException e2) {
			e2.printStackTrace();
		    }
		    for (MedicoRecord medico : medicos) {
			try {
			    for (int i = 0; i < getListResumen()
				    .getSelectedValues().length; i++)
				if (getListResumen().getSelectedValues()[i]
					.toString().equals(medico.toString())) {
				    if (comprobacionesBasicas()) {
					jornada. = medico.idMedico;
					asignarDias(jornada);
					JOptionPane.showMessageDialog(null,
						"Se ha creado la jornada correctamente.",
						"Creacion realizada", 1);
				    }
				}

			} catch (BusinessException e1) {
			    e1.printStackTrace();
			}

		    }
		}
	    });
	    btnAsignar.setBackground(Color.GREEN);
	    btnAsignar.setBounds(544, 329, 89, 23);
	}
	return btnAsignar;

    }

    private JButton getBtnCancelar() {
	if (btnCancelar == null) {
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btnCancelar.setBackground(Color.RED);
	    btnCancelar.setBounds(445, 329, 89, 23);
	}
	return btnCancelar;
    }

    private JSpinner getspinnerMinutoInicio() {
	if (spinnerMinutoInicio == null) {
	    SpinnerModel sm = new SpinnerNumberModel(0, 0, 45, 15);
	    spinnerMinutoInicio = new JSpinner(sm);
	    spinnerMinutoInicio.setBounds(124, 23, 39, 20);
	}
	return spinnerMinutoInicio;
    }

    private JLabel getLblA_1() {
	if (lblA_1 == null) {
	    lblA_1 = new JLabel(":");
	    lblA_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblA_1.setBounds(114, 26, 13, 14);
	}
	return lblA_1;
    }

    private JLabel getLblA_1_1() {
	if (lblA_1_1 == null) {
	    lblA_1_1 = new JLabel(":");
	    lblA_1_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblA_1_1.setBounds(211, 26, 13, 14);
	}
	return lblA_1_1;
    }

    private JSpinner getspinnerMinutoFin() {
	if (spinnerMinutoFin == null) {
	    SpinnerModel sm = new SpinnerNumberModel(15, 0, 45, 15);
	    spinnerMinutoFin = new JSpinner(sm);
	    spinnerMinutoFin.setBounds(222, 23, 39, 20);
	}
	return spinnerMinutoFin;
    }

    private void asignarDias(JornadaRecord jornada) {
	try {
	    if (getChckbxLunes().isSelected()) {
		jornada.dia = "Lunes";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	    if (getChckbxMartes().isSelected()) {
		jornada.dia = "Martes";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	    if (getChckbxMiercoles().isSelected()) {
		jornada.dia = "Miercoles";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	    if (getChckbxJueves().isSelected()) {
		jornada.dia = "Jueves";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	    if (getChckbxViernes().isSelected()) {
		jornada.dia = "Viernes";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	    if (getChckbxSabado().isSelected()) {
		jornada.dia = "Sabado";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	    if (getChckbxDomingo().isSelected()) {
		jornada.dia = "Domingo";
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

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

    private boolean comprobacionesBasicas() {
	if (!getChckbxLunes().isSelected() && !getChckbxMartes().isSelected()
		&& !getChckbxMiercoles().isSelected()
		&& !getChckbxJueves().isSelected()
		&& !getChckbxViernes().isSelected()
		&& !getChckbxSabado().isSelected()
		&& !getChckbxDomingo().isSelected()) {
	    JOptionPane.showMessageDialog(null,
		    "Por favor, seleccione al menos un d�a de la semana para a�adir a la jornada",
		    "Error - D�as de la semana no escogidos", 0);
	    return false;
	}
	return true;
    }

    private JLabel getLblNNombreJornada() {
	if (lblNNombreJornada == null) {
	    lblNNombreJornada = new JLabel("Nombre Jornada:");
	    lblNNombreJornada.setBounds(30, 78, 99, 14);
	}
	return lblNNombreJornada;
    }

    private JTextField getTextField() {
	if (textField == null) {
	    textField = new JTextField();
	    textField.setBounds(126, 75, 202, 20);
	    textField.setColumns(10);
	}
	return textField;
    }
}
