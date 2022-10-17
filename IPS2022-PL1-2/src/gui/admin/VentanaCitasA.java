package gui.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import business.cita.operaciones.CrearCita;
import persistencia.medico.MedicoRecord;
import persistencia.medico.impl.MedicoGatewayImpl;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.impl.PacienteGatewayImpl;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;

public class VentanaCitasA<E> extends JFrame {

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
	private JComboBox cbHoraInicio;
	private JComboBox cbHoraFinal;
	private JPanel panel_5;
	private JRadioButton rdbtnNewRadioButton;
	private JLabel lbFecha;
	private JButton btEliminar;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JList listMedicos;
	private DefaultListModel<String> modjlist = new DefaultListModel<>();
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JLabel lbAño;
	private JComboBox cbAño;
	private JLabel lbMes;
	private JComboBox cbMes;
	private JLabel lbDia;
	private JComboBox cbDia;
	private List<String> listaH = setHorasL();
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
			jbCacelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete();
				}
			});
			jbCacelar.setMnemonic('c');
		}
		return jbCacelar;
	}
	private JButton getJbConfirmar() {
		if (jbConfirmar == null) {
			jbConfirmar = new JButton("Confirmar cita");
			jbConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int size = modjlist.size();
					if( size> 0) {
						CrearCita cc = new CrearCita();
						if(cbAño.getSelectedIndex() != -1 && cbMes.getSelectedIndex() != -1 && cbDia.getSelectedIndex() != -1 && 
								cbHoraInicio.getSelectedIndex() != -1 && cbHoraFinal.getSelectedIndex() != -1) {
							cc.crearCita(cbPacinte.getSelectedItem().toString(),
									rdbtnNewRadioButton.isSelected(),cbLugar.getSelectedItem().toString(),cbAño.getSelectedItem().toString(),
									cbMes.getSelectedItem().toString(),cbDia.getSelectedItem().toString(),
									cbHoraInicio.getSelectedItem().toString(),cbHoraFinal.getSelectedItem().toString()); 
							
						}else {
						cc.crearCita(cbPacinte.getSelectedItem().toString(),
								rdbtnNewRadioButton.isSelected(),cbLugar.getSelectedItem().toString());
						}
						for(int i = 0 ; i < size; i++) {
							cc.crearCitaMedico(modjlist.get(i));
						}
						delete();
					}
				}
			});
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
			pDer.add(getListMedicos(), BorderLayout.CENTER);
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
			
			jbAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String me = cbMedicos.getSelectedItem().toString();
					boolean b = true ; 
					for(int i = 0 ; i<modjlist.getSize();i++) {
						if (me.equals(modjlist.get(i))) {
							b = false ;
						}
					}
					if (b) modjlist.addElement(me);
				}
			});
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
			panelComboBox.add(getPanel_10());
			panelComboBox.add(getPanel_2());
		}
		return panelComboBox;
	}
	private JComboBox getComboBox_2() {
		if (cbPacinte == null) {
			cbPacinte = new JComboBox();
			cbPacinte.setRequestFocusEnabled(false);
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
			PacienteGatewayImpl p = new PacienteGatewayImpl();
			List<PacienteRecord> l =  p.findAll();
			for(int i = 0; i < l.size();i++) {
				mod.addElement(l.get(i).toString());
			}
			
			cbPacinte.setModel(mod);
		}
		return cbPacinte;
	}
	private JComboBox getComboBox_1_1() {
		if (cbMedicos == null) {
			cbMedicos = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
			MedicoGatewayImpl m = new MedicoGatewayImpl();
			List<MedicoRecord> l =  m.findAll();
			for(int i = 0; i < l.size();i++) {
				mod.addElement(l.get(i).toString());
			}
			
			cbMedicos.setModel(mod);
		}
		return cbMedicos;
	}
	private JComboBox getComboBox_2_1() {
		if (cbLugar == null) {
			cbLugar = new JComboBox();
			cbLugar.setModel(new DefaultComboBoxModel(new String[] {"Consulta 1 ", "Consulta 2", "Consulta 3 ", "Consulta 4", "Consulta 5", "Consulta 6", "Consulta 7", "Consulta 8", "Consulta 9"}));
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
			panel_4.add(getCbHoraInicio());
			panel_4.add(getCbHoraFinal());
		}
		return panel_4;
	}
	private JComboBox getCbHoraInicio() {
		if (cbHoraInicio == null) {
			cbHoraInicio = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
			cbHoraInicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setHFModel(cbHoraInicio.getSelectedIndex());
				}
			});
			for(int i = 0; i < listaH.size();i++) {
				mod.addElement(listaH.get(i));
			}
			cbHoraInicio.setModel(mod);
		}
		return cbHoraInicio;
	}
	
	private void setHFModel(int j) {
		DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
		for(int i =  j; i < listaH.size();i++) {
			mod.addElement(listaH.get(i));
		}
			cbHoraFinal.setModel(mod);
	}
	
	private JComboBox getCbHoraFinal() {
		if (cbHoraFinal == null) {
			cbHoraFinal = new JComboBox();
		}
		return cbHoraFinal;
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
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<String> el = listMedicos.getSelectedValuesList();
					for(int i= 0 ; i < el.size() ;i++) {
						modjlist.removeElement(el.get(i));
					}
				}
			});
		}
		return btEliminar;
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
	private JList getListMedicos() {
		if (listMedicos == null) {
			listMedicos = new JList();
			
			listMedicos.setModel(modjlist);
		}
		return listMedicos;
	}
	
	public void delete() {
		dispose();
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setLayout(new GridLayout(0, 3, 0, 0));
			panel_10.add(getPanel_11());
			panel_10.add(getPanel_12());
			panel_10.add(getPanel_13());
		}
		return panel_10;
	}
	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setLayout(new GridLayout(2, 0, 0, 0));
			panel_11.add(getLbAño());
			panel_11.add(getCbAño());
		}
		return panel_11;
	}
	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setLayout(new GridLayout(2, 0, 0, 0));
			panel_12.add(getLbMes());
			panel_12.add(getCbMes());
		}
		return panel_12;
	}
	private JPanel getPanel_13() {
		if (panel_13 == null) {
			panel_13 = new JPanel();
			panel_13.setLayout(new GridLayout(2, 0, 0, 0));
			panel_13.add(getLbDia());
			panel_13.add(getCbDia());
		}
		return panel_13;
	}
	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("A\u00F1o");
			lbAño.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lbAño.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbAño;
	}
	private JComboBox getCbAño() {
		if (cbAño == null) {
			cbAño = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
			for(int i =2022;i<2051;i++) {
				mod.addElement(i);
			}
			cbAño.setModel(mod);
		}
		return cbAño;
	}
	private JLabel getLbMes() {
		if (lbMes == null) {
			lbMes = new JLabel("Mes");
			lbMes.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			lbMes.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbMes;
	}
	private JComboBox getCbMes() {
		if (cbMes == null) {
			cbMes = new JComboBox();
			cbMes.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		}
		return cbMes;
	}
	private JLabel getLbDia() {
		if (lbDia == null) {
			lbDia = new JLabel("D\u00EDa");
			lbDia.setHorizontalAlignment(SwingConstants.CENTER);
			lbDia.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		}
		return lbDia;
	}
	private JComboBox getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
			for(int i =1;i<32;i++) {
				mod.addElement(i);
			}
			cbDia.setModel(mod);
		}
		return cbDia;
	}
	
	private List<String> setHorasL() {
		List l = new ArrayList<>();
		for(int i =8;i<18;i++) {
			String s = "";
			if(i<10) {
				s = "0" + i +":";
			}else {
				s = i + ":";
			}
			for(int j = 0 ; j <60;j = j +10) {
				if(j == 0 ) {
					l.add(s+"0"+j);
				}else {
					l.add(s+j);
				}
			}
		}
		return l ;
	}
}
