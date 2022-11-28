package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
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

public class AsignarcionJornadasComunes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelInicioJornada;
    private JSpinner spinnerDiaInicio;
    private JLabel lblDe;
    private JSpinner spinnerMesInicio;
    private JLabel lblDel;
    private JSpinner SpinnerAnioInicio;
    private JPanel panelFinJornada;
    private JSpinner spinnerDiaFin;
    private JLabel lblDe_1;
    private JSpinner spinnerMesFin;
    private JLabel lblDel_1;
    private JSpinner SpinnerAnioFin;
    private JList listMedicos;
    private JLabel lblListaMedicos;
    private JButton btnAsignar;
    private JButton btnCancelar;

    private static LocalDate today = LocalDate.now();
    private List<MedicoRecord> medicos;
    private DefaultListModel<MedicoRecord> modelo = new DefaultListModel<>();
    private JPanel panelJornada;
    private JLabel lblNombre;
    private JComboBox cbNombreJornada;

    private Optional<JornadaComunRecord> jornadaComun;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AsignarcionJornadasComunes frame = new AsignarcionJornadasComunes();
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
    public AsignarcionJornadasComunes() throws BusinessException {
	setResizable(false);
	setTitle("Asignaci\u00F3n Jornadas Laborales - Manual");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 666, 400);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPanelInicioJornada());
	contentPane.add(getPanelFinJornada());
	contentPane.add(getListMedicos());
	contentPane.add(getLblListaMedicos());
	contentPane.add(getBtnAsignar());
	contentPane.add(getBtnCancelar());
	contentPane.add(getPanelJornada());
    }

    private JPanel getPanelInicioJornada() {
	if (panelInicioJornada == null) {
	    panelInicioJornada = new JPanel();
	    panelInicioJornada.setBounds(10, 155, 341, 54);
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

    private JPanel getPanelFinJornada() {
	if (panelFinJornada == null) {
	    panelFinJornada = new JPanel();
	    panelFinJornada.setBorder(new TitledBorder(

		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255),

			    new Color(160, 160, 160)),

		    "D\u00EDa de fin de la jornada", TitledBorder.LEADING,

		    TitledBorder.TOP, null, new Color(0, 0, 0)));
	    panelFinJornada.setBounds(10, 220, 341, 54);
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

    private JList getListMedicos() throws BusinessException {
	if (listMedicos == null) {

	    medicos = BusinessFactory.forAdminService().buscarMedicos();
	    for (MedicoRecord medico : medicos) {
		modelo.addElement(medico);
	    }
	    listMedicos = new JList<MedicoRecord>(modelo);
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
		public void actionPerformed(ActionEvent e) {
		    JornadaRecord jornada = new JornadaRecord();
		    try {
			if (getListMedicos().getSelectedValuesList()
				.size() == 0)
			    JOptionPane.showMessageDialog(null,
				    "Por favor, seleccione a algun medico al que quiera añadirle una jornada",
				    "Error - Medico no seleccionado", 0);
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
					jornada.idMedico = medico.idMedico;
					asignarDias(jornada);
					JOptionPane.showMessageDialog(null,
						"Se han añadido las jornadas correctamente.",
						"Asignacion realizada", 1);
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

    private Timestamp indicarFin(int hora, int minuto) {
	int anio = Integer.parseInt(getspinnerAnioFin().getValue().toString());
	int dia = (int) getspinnerDiaFin().getValue();
	dia = arreglarDiaMes(anio, indicarMesFin() + 1, dia);
	@SuppressWarnings("deprecation")
	Timestamp fecha = new Timestamp(anio - 1900, indicarMesFin(), dia, hora,
		minuto, 0, 0);
	return fecha;
    }

    private Timestamp indicarInicio(int hora, int minuto) {
	int anio = Integer
		.parseInt(getspinnerAnioInicio().getValue().toString());
	int dia = (int) getspinnerDiaInicio().getValue();
	dia = arreglarDiaMes(anio, indicarMesInicio() + 1, dia);
	@SuppressWarnings("deprecation")
	Timestamp fecha = new Timestamp(anio - 1900, indicarMesFin(), dia, hora,
		minuto, 0, 0);
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
	String[] franja;
	String[] horas;
	try {
	    for (String d : jornadaComun.get().lunes) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	} catch (Exception e) {
	}
	try {
	    for (String d : jornadaComun.get().martes) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);

	    }
	} catch (Exception e) {
	}
	try {
	    for (String d : jornadaComun.get().miercoles) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	} catch (Exception e) {
	}
	try {
	    for (String d : jornadaComun.get().jueves) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	} catch (Exception e) {
	}
	try {
	    for (String d : jornadaComun.get().viernes) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	} catch (Exception e) {
	}
	try {
	    for (String d : jornadaComun.get().sabado) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }
	} catch (Exception e) {
	}
	try {
	    for (String d : jornadaComun.get().domingo) {
		franja = d.split("-");
		horas = franja[0].split(":");
		jornada.dia = "Lunes";
		jornada.inicio = indicarInicio(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		horas = franja[1].split(":");
		jornada.fin = indicarFin(Integer.parseInt(horas[0]),
			Integer.parseInt(horas[1]));
		BusinessFactory.forAdminService()
			.asignarJornadasLaboralesMedicos(jornada);
	    }

	} catch (Exception e) {
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
	if (!comprobarDia()) {
	    JOptionPane.showMessageDialog(null,
		    "Por favor, comprueba de que la fecha de incio de jornada sea menor que la de finalizacion de la misma",
		    "Error - Mala finalizacion de jornadas", 0);
	    return false;
	}
	return true;
    }

    private boolean comprobarDia() {
	if ((int) getspinnerAnioInicio().getValue() == (int) getspinnerAnioFin()
		.getValue()) {
	    if (indicarMesInicio() == indicarMesFin()) {
		if ((int) getspinnerDiaInicio()
			.getValue() >= (int) getspinnerDiaFin().getValue()) {
		    return false;
		} else {
		    return true;
		}
	    }
	    if (indicarMesInicio() < indicarMesFin()) {
		return true;
	    } else {
		return false;
	    }
	}
	if ((int) getspinnerAnioInicio().getValue() > (int) getspinnerAnioFin()
		.getValue())
	    return false;
	return true;
    }

    private JPanel getPanelJornada() {
	if (panelJornada == null) {
	    panelJornada = new JPanel();
	    panelJornada.setLayout(null);
	    panelJornada.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Jornada", TitledBorder.LEADING, TitledBorder.TOP, null,
		    new Color(0, 0, 0)));
	    panelJornada.setBounds(12, 90, 341, 54);
	    panelJornada.add(getLblNombre());
	    panelJornada.add(getCbNombreJornada());
	}
	return panelJornada;
    }

    private JLabel getLblNombre() {
	if (lblNombre == null) {
	    lblNombre = new JLabel("Nombre: ");
	    lblNombre.setBounds(23, 11, 58, 32);
	}
	return lblNombre;
    }

    private JComboBox getCbNombreJornada() {
	if (cbNombreJornada == null) {
	    cbNombreJornada = new JComboBox();
	    cbNombreJornada.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    try {
			jornadaComun = BusinessFactory.forAdminService()
				.buscarJornadaComun(cbNombreJornada
					.getSelectedItem().toString());
		    } catch (NumberFormatException | BusinessException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	    cbNombreJornada.setEditable(true);
	    cbNombreJornada.setRequestFocusEnabled(false);
	    cbNombreJornada.setBounds(86, 17, 202, 20);
	    DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	    List<JornadaComunRecord> l = new ArrayList<>();
	    try {
		l = BusinessFactory.forAdminService().buscarJornadasComunes();
	    } catch (BusinessException e) {
		e.printStackTrace();
	    }
	    for (int i = 0; i < l.size(); i++) {
		mod.addElement(l.get(i).nombre);
	    }
	    cbNombreJornada.setModel(mod);
	}
	return cbNombreJornada;
    }
}
