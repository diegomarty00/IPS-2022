package gui.Administrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JList;

public class VentanaCitasA extends JFrame {

	private JPanel contentPane;
	private JPanel panelC;
	private JPanel panelN;
	private JPanel jPanelBotones;
	private JButton jbCacelar;
	private JButton jbConfirmar;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lbPacientes;
	private JLabel lbMedicos;
	private JLabel lbLugar;
	private JLabel lbHorario;
	private JPanel pDer;
	private JLabel lbMedicosSelccionados;
	private JButton jbAñadir;
	private JPanel panelComboBox;
	private JComboBox cbPacinte;
	private JComboBox cbMedicos;
	private JComboBox cbLugar;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lbInicio;
	private JLabel lbFinal;
	private JPanel panel_4;
	private JComboBox jcbHoraInicio;
	private JComboBox jcbHoraFinal;
	private JPanel panel_5;
	private JRadioButton rdbtnNewRadioButton;
	private JLabel lbFecha;
	private JButton btEliminar;
	private JComboBox cbFecha;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JList list;

	/**
	 * Create the frame.
	 */
	public VentanaCitasA() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelC(), BorderLayout.CENTER);
		contentPane.add(getPanelN(), BorderLayout.SOUTH);
	    AutoCompleteDecorator.decorate(cbPacinte);
	    AutoCompleteDecorator.decorate(cbMedicos);
	}
	private JPanel getPanelC() {
		if (panelC == null) {
			panelC = new JPanel();
			panelC.setLayout(new BorderLayout(0, 0));
			panelC.add(getPanel(), BorderLayout.WEST);
			panelC.add(getPanel_1(), BorderLayout.CENTER);
			panelC.add(getPDer(), BorderLayout.EAST);
		}
		return panelC;
	}
	private JPanel getPanelN() {
		if (panelN == null) {
			panelN = new JPanel();
			panelN.setLayout(new BorderLayout(0, 0));
			panelN.add(getJPanelBotones(), BorderLayout.EAST);
			panelN.add(getRdbtnNewRadioButton(), BorderLayout.CENTER);
		}
		return panelN;
	}
	private JPanel getJPanelBotones() {
		if (jPanelBotones == null) {
			jPanelBotones = new JPanel();
			jPanelBotones.add(getJbCacelar());
			jPanelBotones.add(getJbConfirmar());
		}
		return jPanelBotones;
	}
	private JButton getJbCacelar() {
		if (jbCacelar == null) {
			jbCacelar = new JButton("Cancelar");
			jbCacelar.setMnemonic('c');
		}
		return jbCacelar;
	}
	private JButton getJbConfirmar() {
		if (jbConfirmar == null) {
			jbConfirmar = new JButton("Confirmar cita");
		}
		return jbConfirmar;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getLbPacientes());
			panel.add(getLbMedicos());
			panel.add(getLbLugar());
			panel.add(getLbFecha());
			panel.add(getLbHorario());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getPanelComboBox(), BorderLayout.CENTER);
			panel_1.add(getPanel_5(), BorderLayout.EAST);
		}
		return panel_1;
	}
	private JLabel getLbPacientes() {
		if (lbPacientes == null) {
			lbPacientes = new JLabel("Elegir paciente :");
			lbPacientes.setFont(new Font("Times New Roman", Font.BOLD, 16));
		}
		return lbPacientes;
	}
	private JLabel getLbMedicos() {
		if (lbMedicos == null) {
			lbMedicos = new JLabel("Elegir Medicos ");
			lbMedicos.setFont(new Font("Times New Roman", Font.BOLD, 16));
		}
		return lbMedicos;
	}
	private JLabel getLbLugar() {
		if (lbLugar == null) {
			lbLugar = new JLabel("Elegir Lugar ");
			lbLugar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		}
		return lbLugar;
	}
	private JLabel getLbHorario() {
		if (lbHorario == null) {
			lbHorario = new JLabel("Horario");
			lbHorario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		}
		return lbHorario;
	}
	private JPanel getPDer() {
		if (pDer == null) {
			pDer = new JPanel();
			pDer.setLayout(new BorderLayout(0, 0));
			pDer.add(getLbMedicosSelccionados(), BorderLayout.NORTH);
			pDer.add(getBtEliminar(), BorderLayout.SOUTH);
			pDer.add(getList(), BorderLayout.CENTER);
		}
		return pDer;
	}
	private JLabel getLbMedicosSelccionados() {
		if (lbMedicosSelccionados == null) {
			lbMedicosSelccionados = new JLabel("Medicos seleciconados:");
			lbMedicosSelccionados.setFont(new Font("Times New Roman", Font.BOLD, 14));
		}
		return lbMedicosSelccionados;
	}
	private JButton getJbAñadir() {
		if (jbAñadir == null) {
			jbAñadir = new JButton("A\u00F1adir");
			jbAñadir.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		}
		return jbAñadir;
	}
	private JPanel getPanelComboBox() {
		if (panelComboBox == null) {
			panelComboBox = new JPanel();
			panelComboBox.setLayout(new GridLayout(5, 1, 0, 0));
			panelComboBox.add(getComboBox_2());
			panelComboBox.add(getComboBox_1_1());
			panelComboBox.add(getComboBox_2_1());
			panelComboBox.add(getCbFecha());
			panelComboBox.add(getPanel_2());
		}
		return panelComboBox;
	}
	private JComboBox getComboBox_2() {
		if (cbPacinte == null) {
			cbPacinte = new JComboBox();
			cbPacinte.setRequestFocusEnabled(false);
			cbPacinte.setModel(new DefaultComboBoxModel(new String[] {"Paco Garcia Fernadez ", "Arnau Ferrer Despuig"}));
		}
		return cbPacinte;
	}
	private JComboBox getComboBox_1_1() {
		if (cbMedicos == null) {
			cbMedicos = new JComboBox();
			cbMedicos.setModel(new DefaultComboBoxModel(new String[] {"Dr.Mario ", "Dr.Lustro"}));
		}
		return cbMedicos;
	}
	private JComboBox getComboBox_2_1() {
		if (cbLugar == null) {
			cbLugar = new JComboBox();
			cbLugar.setModel(new DefaultComboBoxModel(new String[] {"Consulta 1 ", "Consulta 2"}));
		}
		return cbLugar;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPanel_3(), BorderLayout.WEST);
			panel_2.add(getPanel_4(), BorderLayout.CENTER);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(2, 0, 0, 0));
			panel_3.add(getLblNewLabel_1());
			panel_3.add(getLbFinal());
		}
		return panel_3;
	}
	private JLabel getLblNewLabel_1() {
		if (lbInicio == null) {
			lbInicio = new JLabel("Inicio");
			lbInicio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		}
		return lbInicio;
	}
	private JLabel getLbFinal() {
		if (lbFinal == null) {
			lbFinal = new JLabel("Final");
			lbFinal.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		}
		return lbFinal;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(2, 0, 0, 0));
			panel_4.add(getJcbHoraInicio());
			panel_4.add(getJcbHoraFinal());
		}
		return panel_4;
	}
	private JComboBox getJcbHoraInicio() {
		if (jcbHoraInicio == null) {
			jcbHoraInicio = new JComboBox();
		}
		return jcbHoraInicio;
	}
	private JComboBox getJcbHoraFinal() {
		if (jcbHoraFinal == null) {
			jcbHoraFinal = new JComboBox();
		}
		return jcbHoraFinal;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new GridLayout(0, 1, 0, 0));
			panel_5.add(getPanel_9());
			panel_5.add(getPanel_8());
			panel_5.add(getPanel_7());
			panel_5.add(getPanel_6());
		}
		return panel_5;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("Cita urgente ");
			rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		}
		return rdbtnNewRadioButton;
	}
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha");
			lbFecha.setFont(new Font("Times New Roman", Font.BOLD, 16));
		}
		return lbFecha;
	}
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
		}
		return btEliminar;
	}
	private JComboBox getCbFecha() {
		if (cbFecha == null) {
			cbFecha = new JComboBox();
		}
		return cbFecha;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.add(getJbAñadir());
		}
		return panel_8;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
		}
		return panel_9;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
		}
		return list;
	}
}
