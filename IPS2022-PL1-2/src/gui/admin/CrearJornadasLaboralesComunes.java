package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.admin.JornadaRecord;
import persistencia.admin.MedicoRecord;
import util.BusinessException;

public class CrearJornadasLaboralesComunes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelDíaSemana;
    private JCheckBox chckbxLunes;
    private JCheckBox chckbxMartes;
    private JCheckBox chckbxMiercoles;
    private JCheckBox chckbxJueves;
    private JCheckBox chckbxViernes;
    private JCheckBox chckbxSabado;
    private JCheckBox chckbxDomingo;
    private JPanel panelInicioJornada;
    private JSpinner spinnerDiaInicio;
    private JLabel lblDe;
    private JSpinner spinnerMesInicio;
    private JLabel lblDel;
    private JSpinner SpinnerAnioInicio;
    private JPanel panelHoraJornada;
    private JLabel lblDe_1_1;
    private JSpinner spinnerHoraInicio;
    private JLabel lblA;
    private JSpinner spinnerHoraFin;
    private JPanel panelFinJornada;
    private JSpinner spinnerDiaFin;
    private JLabel lblDe_1;
    private JSpinner spinnerMesFin;
    private JLabel lblDel_1;
    private JSpinner SpinnerAnioFin;
    private JList<MedicoRecord> listMedicos;
    private JLabel lblListaMedicos;
    private JButton btnAsignar;
    private JButton btnCancelar;

    private static LocalDate today = LocalDate.now();
    private JSpinner spinnerMinutoInicio;
    private JLabel lblA_1;
    private JLabel lblA_1_1;
    private JSpinner spinnerMinutoFin;
    private List<MedicoRecord> medicos;
    private DefaultListModel<MedicoRecord> modelo = new DefaultListModel<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    CrearJornadasLaboralesComunes frame = new CrearJornadasLaboralesComunes();
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
    public CrearJornadasLaboralesComunes() throws BusinessException {
	setResizable(false);
	setTitle("Asignaci\u00F3n Jornadas Laborales - Manual");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 666, 400);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPanelDíaSemana());
	contentPane.add(getPanelInicioJornada());
	contentPane.add(getPanelHoraJornada());
	contentPane.add(getPanelFinJornada());
	contentPane.add(getListMedicos());
	contentPane.add(getLblListaMedicos());
	contentPane.add(getBtnAsignar());
	contentPane.add(getBtnCancelar());
    }

    private JPanel getPanelDíaSemana() {
	if (panelDíaSemana == null) {
	    panelDíaSemana = new JPanel();
	    panelDíaSemana.setBounds(20, 104, 322, 83);
	    panelDíaSemana
		    .setBorder(new TitledBorder(null, "D\u00EDas de la semana",

			    TitledBorder.LEFT, TitledBorder.TOP, null, null));
	    panelDíaSemana.add(getChckbxLunes());
	    panelDíaSemana.add(getChckbxMartes());
	    panelDíaSemana.add(getChckbxMiercoles());
	    panelDíaSemana.add(getChckbxJueves());
	    panelDíaSemana.add(getChckbxViernes());
	    panelDíaSemana.add(getChckbxSabado());
	    panelDíaSemana.add(getChckbxDomingo());
	}
	return panelDíaSemana;
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

    private JPanel getPanelInicioJornada() {
	if (panelInicioJornada == null) {
	    panelInicioJornada = new JPanel();
	    panelInicioJornada.setBounds(20, 43, 322, 54);
	    panelInicioJornada.setBorder(new TitledBorder(null,
		    "D\u00EDa de inicio de la jornada",

		    TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panelInicioJornada.setLayout(null);
	    panelInicioJornada.add(getspinnerDiaInicio());
	    panelInicioJornada.add(getLblDe());
	    panelInicioJornada.add(getspinnerMesInicio());
	    panelInicioJornada.add(getLblDel());
	    panelInicioJornada.add(getspinnerAnioInicio());
	}
	return panelInicioJornada;
    }

    private JSpinner getspinnerDiaInicio() {
	if (spinnerDiaInicio == null) {
	    SpinnerModel sm = new SpinnerNumberModel(today.getDayOfMonth(), 1,
		    31, 1);
	    spinnerDiaInicio = new JSpinner(sm);
	    spinnerDiaInicio.setBounds(60, 21, 39, 20);
	}
	return spinnerDiaInicio;
    }

    private JLabel getLblDe() {
	if (lblDe == null) {
	    lblDe = new JLabel("de");
	    lblDe.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDe.setBounds(98, 24, 21, 14);
	}
	return lblDe;
    }

    private JSpinner getspinnerMesInicio() {
	if (spinnerMesInicio == null) {
	    String[] mes = new java.text.DateFormatSymbols().getMonths();
	    SpinnerListModel modelMes = new SpinnerListModel(mes);
	    spinnerMesInicio = new JSpinner(modelMes);
	    spinnerMesInicio.setBounds(119, 21, 85, 20);
	}
	return spinnerMesInicio;
    }

    private JLabel getLblDel() {
	if (lblDel == null) {
	    lblDel = new JLabel("del");
	    lblDel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDel.setBounds(204, 24, 30, 14);
	}
	return lblDel;
    }

    private JSpinner getspinnerAnioInicio() {
	if (SpinnerAnioInicio == null) {
	    SpinnerModel sm = new SpinnerNumberModel(today.getYear(), 2022,
		    2099, 1);
	    SpinnerAnioInicio = new JSpinner(sm);
	    SpinnerAnioInicio.setBounds(232, 21, 51, 20);
	}
	return SpinnerAnioInicio;
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
	    panelHoraJornada.setBounds(18, 193, 324, 54);
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

    private JPanel getPanelFinJornada() {
	if (panelFinJornada == null) {
	    panelFinJornada = new JPanel();
	    panelFinJornada.setBorder(new TitledBorder(

		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255),

			    new Color(160, 160, 160)),

		    "D\u00EDa de fin de la jornada", TitledBorder.LEADING,

		    TitledBorder.TOP, null, new Color(0, 0, 0)));
	    panelFinJornada.setBounds(18, 258, 324, 54);
	    panelFinJornada.setLayout(null);
	    panelFinJornada.add(getspinnerDiaFin());
	    panelFinJornada.add(getLblDe_1());
	    panelFinJornada.add(getspinnerMesFin());
	    panelFinJornada.add(getLblDel_1());
	    panelFinJornada.add(getspinnerAnioFin());
	}
	return panelFinJornada;
    }

    private JSpinner getspinnerDiaFin() {
	if (spinnerDiaFin == null) {
	    SpinnerModel sm = new SpinnerNumberModel(today.getDayOfMonth(), 1,
		    31, 1);
	    spinnerDiaFin = new JSpinner(sm);
	    spinnerDiaFin.setBounds(60, 21, 39, 20);
	}
	return spinnerDiaFin;
    }

    private JLabel getLblDe_1() {
	if (lblDe_1 == null) {
	    lblDe_1 = new JLabel("de");
	    lblDe_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDe_1.setBounds(98, 24, 18, 14);
	}
	return lblDe_1;
    }

    private JSpinner getspinnerMesFin() {
	if (spinnerMesFin == null) {
	    String[] mes = new java.text.DateFormatSymbols().getMonths();
	    SpinnerListModel modelMes = new SpinnerListModel(mes);
	    spinnerMesFin = new JSpinner(modelMes);
	    spinnerMesFin.setBounds(116, 21, 88, 20);

	}
	return spinnerMesFin;
    }

    private JLabel getLblDel_1() {
	if (lblDel_1 == null) {
	    lblDel_1 = new JLabel("del");
	    lblDel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblDel_1.setBounds(204, 24, 30, 14);
	}
	return lblDel_1;
    }

    private JSpinner getspinnerAnioFin() {
	if (SpinnerAnioFin == null) {
	    SpinnerModel sm = new SpinnerNumberModel(today.getYear(), 2022,
		    2099, 1);
	    SpinnerAnioFin = new JSpinner(sm);
	    SpinnerAnioFin.setBounds(232, 21, 51, 20);

	}
	return SpinnerAnioFin;
    }

    private JList<MedicoRecord> getListMedicos() throws BusinessException {
	if (listMedicos == null) {

	    medicos = BusinessFactory.forAdminService().buscarMedicos();
	    for (MedicoRecord medico : medicos) {
		modelo.addElement(medico);
	    }
	    listMedicos = new JList<MedicoRecord>(modelo);
	    /**
	     * listMedicos.addMouseListener(new MouseAdapter() {
	     * 
	     * @Override public void mousePressed(MouseEvent e) { CitaRecord
	     *           citaPulsada = (CitaRecord) listMedicos.getModel()
	     *           .getElementAt(
	     *           listMedicos.locationToIndex(e.getPoint())); } });
	     */

	    listMedicos.setBounds(363, 42, 270, 276);
	}
	return listMedicos;
    }

    private JLabel getLblListaMedicos() {
	if (lblListaMedicos == null) {
	    lblListaMedicos = new JLabel("Listado de m\u00E9dicos");
	    lblListaMedicos.setHorizontalAlignment(SwingConstants.CENTER);
	    lblListaMedicos.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblListaMedicos.setBounds(363, 6, 270, 25);
	}
	return lblListaMedicos;
    }

    private JButton getBtnAsignar() {
	if (btnAsignar == null) {
	    btnAsignar = new JButton("Asignar");
	    btnAsignar.addActionListener(new ActionListener() {
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
		    JornadaRecord jornada = new JornadaRecord();
		    try {
			if (getListMedicos().getSelectedValuesList()
				.size() == 0)
			    JOptionPane.showMessageDialog(null,
				    "Por favor, seleccione a algún médico al que quiera añadirle una jornada",
				    "Error - Médico no seleccionado", 0);
		    } catch (HeadlessException e2) {
			e2.printStackTrace();
		    } catch (BusinessException e2) {
			e2.printStackTrace();
		    }
		    for (MedicoRecord medico : medicos) {
			try {
			    for (int i = 0; i < getListMedicos()
				    .getSelectedValues().length; i++)
				if (getListMedicos().getSelectedValues()[i]
					.toString().equals(medico.toString())) {
				    if (comprobacionesBasicas()) {
					jornada.idMedico = medico.id;
					jornada.inicio = indicarInicio();
					jornada.fin = indicarFin();
					asignarDias(jornada);
					JOptionPane.showMessageDialog(null,
						"Se han añadido las jornadas correctamente.",
						"Asignación realizada", 1);
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

    private void arregloDias(String mes, int dia, String temporada) {
	SpinnerModel sm = null;
	if (mes == "enero" || mes == "marzo" || mes == "mayo" || mes == "julio"
		|| mes == "agosto" || mes == "octubre" || mes == "diciembre") {
	    sm = new SpinnerNumberModel(dia, 1, 31, 1);
	}
	if (mes == "abril" || mes == "junio" || mes == "septiembre"
		|| mes == "noviembre") {
	    if (dia == 31)
		dia = 30;
	    sm = new SpinnerNumberModel(dia, 1, 30, 1);
	}
	if (temporada == "inicio") {
	    if ((mes == "febrero")
		    && ((int) SpinnerAnioInicio.getValue() % 4 != 0)) {
		if (dia >= 28)
		    dia = 28;
		sm = new SpinnerNumberModel(dia, 1, 28, 1);
	    }
	    if ((mes == "febrero")
		    && ((int) SpinnerAnioInicio.getValue() % 4 == 0)) {
		if (dia >= 29)
		    dia = 29;
		sm = new SpinnerNumberModel(dia, 1, 29, 1);

	    }
	} else {
	    if ((mes == "febrero")
		    && ((int) SpinnerAnioFin.getValue() % 4 != 0)) {
		if (dia >= 28)
		    dia = 28;
		sm = new SpinnerNumberModel(dia, 1, 28, 1);
	    }
	    if ((mes == "febrero")
		    && ((int) SpinnerAnioFin.getValue() % 4 == 0)) {
		if (dia >= 29)
		    dia = 29;
		sm = new SpinnerNumberModel(dia, 1, 29, 1);
	    }
	}
	spinnerDiaFin = new JSpinner(sm);
    }

    private int indicarAnioFin() {
	String[] cadena = getspinnerAnioFin().getValue().toString().split(".");
	String rs = (cadena[0] + "" + cadena[1]);
	return Integer.parseInt(rs);
    }

    private int indicarMesFin() {
	String[] mes = new java.text.DateFormatSymbols().getMonths();
	for (int i = 0; i <= mes.length - 1; i++) {
	    if (getspinnerMesFin().getValue()
		    .toString() == (mes[i].toString())) {
		return i;
	    }
	}
	return 0;
    }

    private Timestamp indicarFin() {
	int anio = Integer.parseInt(getspinnerAnioFin().getValue().toString());
	int dia = (int) getspinnerDiaFin().getValue();
	dia = arreglarDiaMes(anio, indicarMesFin() + 1, dia);
	@SuppressWarnings("deprecation")
	Timestamp fecha = new Timestamp(anio - 1900, indicarMesFin(), dia,
		(int) getspinnerHoraFin().getValue(),
		(int) getspinnerMinutoFin().getValue(), 0, 0);
	return fecha;
    }

    private Timestamp indicarInicio() {
	int anio = Integer
		.parseInt(getspinnerAnioInicio().getValue().toString());
	int dia = (int) getspinnerDiaInicio().getValue();
	dia = arreglarDiaMes(anio, indicarMesInicio() + 1, dia);
	@SuppressWarnings("deprecation")
	Timestamp fecha = new Timestamp(anio - 1900, indicarMesInicio(), dia,
		(int) getspinnerHoraInicio().getValue(),
		(int) getspinnerMinutoInicio().getValue(), 0, 0);
	return fecha;
    }

    private int indicarMesInicio() {
	String[] mes = new java.text.DateFormatSymbols().getMonths();
	for (int i = 0; i <= mes.length - 1; i++) {
	    if (getspinnerMesInicio().getValue()
		    .toString() == (mes[i].toString())) {
		return i;
	    }
	}
	return 1;
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
		    "Por favor, seleccione al menos un día de la semana para añadir a la jornada",
		    "Error - Días de la semana no escogidos", 0);
	    return false;
	}

	if (indicarFin().compareTo(indicarInicio()) <= 0) {
	    JOptionPane.showMessageDialog(null,
		    "Por favor, comprueba de que la fecha de incio de jornada sea menor que la de finalización de la misma",
		    "Error - Mala finalización de jornadas", 0);
	    return false;
	}
	return true;
    }

}
