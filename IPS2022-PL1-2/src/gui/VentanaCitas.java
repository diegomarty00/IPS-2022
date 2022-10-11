package gui;

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

public class VentanaCitas extends JFrame {

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
	private JButton jbA�adir;
	private JPanel panelComboBox;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
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

	/**
	 * Create the frame.
	 */
	public VentanaCitas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 686, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelC(), BorderLayout.CENTER);
		contentPane.add(getPanelN(), BorderLayout.SOUTH);
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
	private JButton getJbA�adir() {
		if (jbA�adir == null) {
			jbA�adir = new JButton("A\u00F1adir");
			jbA�adir.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		}
		return jbA�adir;
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
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setRequestFocusEnabled(false);
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Paco Garcia Fernadez ", "Arnau Ferrer Despuig"}));
		}
		return comboBox;
	}
	private JComboBox getComboBox_1_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Dr.Mario ", "Dr.Lustro"}));
		}
		return comboBox_1;
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
			panel_5.add(getJbA�adir());
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
}