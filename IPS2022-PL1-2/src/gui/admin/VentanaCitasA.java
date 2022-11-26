package gui.admin;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import business.cita.operaciones.CrearCita;
import persistencia.enfermero.EnfermeroCitaRecord;
import persistencia.enfermero.EnfermeroRecord;
import persistencia.enfermero.impl.EnfermeroCitaGatewayImpl;
import persistencia.enfermero.impl.EnfermeroGatewayImpl;
import persistencia.especialidad.EspecialidadCitaGateway;
import persistencia.especialidad.EspecialidadCitaRecord;
import persistencia.especialidad.EspecialidadRecord;
import persistencia.especialidad.impl.EspecialidadGatewayImpl;
import persistencia.medico.MedicoRecord;
import persistencia.medico.impl.MedicoGatewayImpl;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.impl.PacienteGatewayImpl;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class VentanaCitasA<E> extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    private JButton jbAniadir;
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
    private JCheckBox jChBUrgente;
    private JLabel lbFecha;
    private JButton btEliminar;
    private JPanel panel_6;
    private JPanel panel_7;
    private JPanel panel_8;
    private JPanel panel_9;
    private JList listMedicos;
    private DefaultListModel<String> modjlist = new DefaultListModel<>();
    private DefaultListModel<EspecialidadCitaRecord> eslist =  new DefaultListModel<>();
    private DefaultListModel<EnfermeroCitaRecord> enflist = new DefaultListModel<>();
    
    private JPanel panel_10;
    private JPanel panel_11;
    private JPanel panel_12;
    private JPanel panel_13;
    private JLabel lbAnio;
    private JComboBox cbAnio;
    private JLabel lbMes;
    private JComboBox cbMes;
    private JLabel lbDia;
    private JComboBox cbDia;
    private List<String> listaH = setHorasL();
    private ProcesarAccion pa = new ProcesarAccion();
    private JCheckBox jChBPrioritario;
    private JPanel panel_14;
    private JLabel lblNewLabel;
    private JTextField txtElegirEspecialidad;
    private JPanel panel_15;
    private JComboBox cbEspecialidad;
    private JPanel panel_16;
    private JSpinner spNme;
    private JButton btAniadirE;
    private JList listEspecialistas;
    private JTextField txtEnfermero;
    private JPanel panel_17;
    private JComboBox cbEnfermeros;
    private JPanel panel_16_1;
    private JSpinner spEnf;
    private JButton btAniadirE_1;
    private JTextField txtEnfermeros;
    private JList<EnfermeroCitaRecord> listEnfermeros;
    /**
     * Create the frame.
     */
    public VentanaCitasA() {
	setTitle("Crear citas");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 769, 374);
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
	    panelN.add(getJChBPrioritario(), BorderLayout.WEST);
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
	    jbConfirmar.addActionListener(pa);
	}
	return jbConfirmar;
    }
    	
    public class ProcesarAccion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int size = modjlist.size();
			int si = eslist.size();
		    if (size > 0|| si > 0 ) {
			CrearCita cc = new CrearCita();
				if (cbAnio.getSelectedIndex() != -1
					&& cbMes.getSelectedIndex() != -1
					&& cbDia.getSelectedIndex() != -1
					&& cbHoraInicio.getSelectedIndex() != -1
					&& cbHoraFinal.getSelectedIndex() != -1) {
					
						DatosCita frame = new DatosCita(cbPacinte.getSelectedItem().toString(),
								jChBUrgente.isSelected(),
							    cbLugar.getSelectedItem().toString(),
							    cbAnio.getSelectedItem().toString(),
							    cbMes.getSelectedItem().toString(),
							    cbDia.getSelectedItem().toString(),
							    cbHoraInicio.getSelectedItem().toString(),
							    cbHoraFinal.getSelectedItem().toString(),
							    modjlist,jChBPrioritario.isSelected(),eslist,enflist);
						frame.setVisible(true);
				

				} 
				delete();
		    }
		}
	}
    	
    	
    	
    	
    
    
    
    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setLayout(new GridLayout(7, 1, 0, 0));
	    panel.add(getLbPacientes());
	    panel.add(getLbMedicos());
	    panel.add(getTxtElegirEspecialidad());
	    panel.add(getTxtEnfermero());
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
	    pDer.add(getPanel_14(), BorderLayout.CENTER);
	}
	return pDer;
    }

    private JLabel getLbMedicosSelccionados() {
	if (lbMedicosSelccionados == null) {
	    lbMedicosSelccionados = new JLabel("Medicos seleciconados:");
	    lbMedicosSelccionados
		    .setFont(new Font("Times New Roman", Font.BOLD, 14));
	}
	return lbMedicosSelccionados;
    }

    private JButton getJbAniadir() {
	if (jbAniadir == null) {
	    jbAniadir = new JButton("A\u00F1adir");

	    jbAniadir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(cbMedicos.getSelectedIndex() > 0 ) {
			    String me = cbMedicos.getSelectedItem().toString();
			    boolean b = true;
			    for (int i = 0; i < modjlist.getSize(); i++) {
					if (me.equals(modjlist.get(i))) {
					    b = false;
					}
			    }
			    if (b)
				modjlist.addElement(me);
			}
		}
	    });
	    jbAniadir.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	}
	return jbAniadir;
    }

    private JPanel getPanelComboBox() {
	if (panelComboBox == null) {
	    panelComboBox = new JPanel();
	    panelComboBox.setLayout(new GridLayout(7, 1, 0, 0));
	    panelComboBox.add(getComboBox_2());
	    panelComboBox.add(getComboBox_1_1());
	    panelComboBox.add(getPanel_15());
	    panelComboBox.add(getPanel_17());
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
	    List<PacienteRecord> l = p.findAll();
	    for (int i = 0; i < l.size(); i++) {
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
	    mod.addElement("--------");
	    MedicoGatewayImpl m = new MedicoGatewayImpl();
	    List<MedicoRecord> l = m.findAll();
	    for (int i = 0; i < l.size(); i++) {
		mod.addElement(l.get(i).toString());
	    }

	    cbMedicos.setModel(mod);
	}
	return cbMedicos;
    }

    private JComboBox getComboBox_2_1() {
	if (cbLugar == null) {
	    cbLugar = new JComboBox();
	    cbLugar.setModel(new DefaultComboBoxModel(
		    new String[] { "Consulta 1 ", "Consulta 2", "Consulta 3 ",
			    "Consulta 4", "Consulta 5", "Consulta 6",
			    "Consulta 7", "Consulta 8", "Consulta 9" }));
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
	    for (int i = 0; i < listaH.size(); i++) {
		mod.addElement(listaH.get(i));
	    }
	    cbHoraInicio.setModel(mod);
	}
	return cbHoraInicio;
    }

    private void setHFModel(int j) {
	DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	for (int i = j; i < listaH.size(); i++) {
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
	    panel_5.setLayout(new GridLayout(5, 1, 0, 0));
	    panel_5.add(getPanel_9());
	    panel_5.add(getPanel_8());
	    panel_5.add(getPanel_7());
	    panel_5.add(getPanel_6());
	}
	return panel_5;
    }

    private JCheckBox getRdbtnNewRadioButton() {
	if (jChBUrgente == null) {
	    jChBUrgente = new JCheckBox("Cita urgente ");
	    jChBUrgente
		    .setFont(new Font("Times New Roman", Font.BOLD, 14));
	}
	return jChBUrgente;
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
		    for (int i = 0; i < el.size(); i++) {
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
	    panel_8.add(getJbAniadir());
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
	    panel_11.add(getLbAnio());
	    panel_11.add(getCbAnio());
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

    private JLabel getLbAnio() {
	if (lbAnio == null) {
	    lbAnio = new JLabel("A\u00F1o");
	    lbAnio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    lbAnio.setHorizontalAlignment(SwingConstants.CENTER);
	}
	return lbAnio;
    }

    private JComboBox getCbAnio() {
	if (cbAnio == null) {
	    cbAnio = new JComboBox();
	    DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
	    for (int i = 2022; i < 2051; i++) {
		mod.addElement(i);
	    }
	    cbAnio.setModel(mod);
	}
	return cbAnio;
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
	    cbMes.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "07", "08", "09", "10", "11", "12"}));
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
	    for (int i = 1; i < 32; i++) {
	    	if(i<10) {
	    		mod.addElement("0"+i);
	    	}
	    	else {
	    		mod.addElement(i);
	    	}
	    }
	    cbDia.setModel(mod);
	}
	return cbDia;
    }

    private List<String> setHorasL() {
	List l = new ArrayList<>();
	for (int i = 8; i < 18; i++) {
	    String s = "";
	    if (i < 10) {
		s = "0" + i + ":";
	    } else {
		s = i + ":";
	    }
	    for (int j = 0; j < 60; j = j + 10) {
		if (j == 0) {
		    l.add(s + "0" + j);
		} else {
		    l.add(s + j);
		}
	    }
	}
	return l;
    }
	private JCheckBox getJChBPrioritario() {
		if (jChBPrioritario == null) {
			jChBPrioritario = new JCheckBox("Cita prioritaria");
			jChBPrioritario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		}
		return jChBPrioritario;
	}
	private JPanel getPanel_14() {
		if (panel_14 == null) {
			panel_14 = new JPanel();
			panel_14.setLayout(new GridLayout(5, 1, 0, 0));
			panel_14.add(getListMedicos());
			panel_14.add(getLblNewLabel());
			panel_14.add(getListEspecialistas());
			panel_14.add(getTxtEnfermeros());
			panel_14.add(getListEnfermeros());
		}
		return panel_14;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Especialistas");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		}
		return lblNewLabel;
	}
	private JTextField getTxtElegirEspecialidad() {
		if (txtElegirEspecialidad == null) {
			txtElegirEspecialidad = new JTextField();
			txtElegirEspecialidad.setText("Especialidad");
			txtElegirEspecialidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
			txtElegirEspecialidad.setEditable(false);
			txtElegirEspecialidad.setColumns(10);
		}
		return txtElegirEspecialidad;
	}
	private JPanel getPanel_15() {
		if (panel_15 == null) {
			panel_15 = new JPanel();
			panel_15.setLayout(new BorderLayout(0, 0));
			panel_15.add(getCbEspecialidad(), BorderLayout.CENTER);
			panel_15.add(getPanel_16(), BorderLayout.EAST);
		}
		return panel_15;
	}
	private JComboBox getCbEspecialidad() {
		if (cbEspecialidad == null) {
			cbEspecialidad = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
		    EspecialidadGatewayImpl e = new EspecialidadGatewayImpl();
		    List<EspecialidadRecord> l = e.findAll();
		    for (int i = 0; i < l.size(); i++) {
			mod.addElement(l.get(i));
		    }

		    cbEspecialidad.setModel(mod);
		}
		return cbEspecialidad;
	}
	private JPanel getPanel_16() {
		if (panel_16 == null) {
			panel_16 = new JPanel();
			panel_16.setLayout(new BorderLayout(0, 0));
			panel_16.add(getSpNme(), BorderLayout.WEST);
			panel_16.add(getBtAniadirE(), BorderLayout.CENTER);
		}
		return panel_16;
	}
	private JSpinner getSpNme() {
		if (spNme == null) {
			spNme = new JSpinner();
		}
		return spNme;
	}
	private JButton getBtAniadirE() {
		if (btAniadirE == null) {
			btAniadirE = new JButton("A\u00F1adir ");
			btAniadirE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbEspecialidad.getSelectedIndex() >= 0 ) {
					    EspecialidadCitaRecord es = new EspecialidadCitaRecord();
					    EspecialidadRecord especialidad = (EspecialidadRecord) cbEspecialidad.getSelectedItem();
					    es.idEspecialidad = especialidad.idEspecialidad;
					    es.nMedicos = (Integer) spNme.getValue();
					   
					    boolean b = true;
					    for (int i = 0; i < eslist.getSize(); i++) {
							if (es.idEspecialidad.equals(eslist.get(i).idEspecialidad)) {
							    b = false;
							}
					    }
					    if (b)
						eslist.addElement(es);
					}
				}
			});
		}
		return btAniadirE;
	}
	
	
	private JList getListEspecialistas() {
		if (listEspecialistas == null) {
			listEspecialistas = new JList();
			listEspecialistas.setModel(eslist);
		}
		return listEspecialistas;
	}
	private JTextField getTxtEnfermero() {
		if (txtEnfermero == null) {
			txtEnfermero = new JTextField();
			txtEnfermero.setText("Enfermero");
			txtEnfermero.setFont(new Font("Times New Roman", Font.BOLD, 16));
			txtEnfermero.setEditable(false);
			txtEnfermero.setColumns(10);
		}
		return txtEnfermero;
	}
	private JPanel getPanel_17() {
		if (panel_17 == null) {
			panel_17 = new JPanel();
			panel_17.setLayout(new BorderLayout(0, 0));
			panel_17.add(getCbEspecialidad_1());
			panel_17.add(getPanel_16_1(), BorderLayout.EAST);
		}
		return panel_17;
	}
	private JComboBox getCbEspecialidad_1() {
		if (cbEnfermeros == null) {
			cbEnfermeros = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
		    EnfermeroGatewayImpl e = new EnfermeroGatewayImpl();
		    List<EnfermeroRecord> l = e.findAll();
		    for (int i = 0; i < l.size(); i++) {
			mod.addElement(l.get(i));
		    }
		    cbEnfermeros.setModel(mod);
		}
		return cbEnfermeros;
	}
	private JPanel getPanel_16_1() {
		if (panel_16_1 == null) {
			panel_16_1 = new JPanel();
			panel_16_1.setLayout(new BorderLayout(0, 0));
			panel_16_1.add(getSpEnf(), BorderLayout.WEST);
			panel_16_1.add(getBtAniadirE_1(), BorderLayout.EAST);
		}
		return panel_16_1;
	}
	private JSpinner getSpEnf() {
		if (spEnf == null) {
			spEnf = new JSpinner();
		}
		return spEnf;
	}
	private JButton getBtAniadirE_1() {
		if (btAniadirE_1 == null) {
			btAniadirE_1 = new JButton("A\u00F1adir ");
			btAniadirE_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbEnfermeros.getSelectedIndex() > 0 ) {
					    EnfermeroCitaRecord en = new EnfermeroCitaRecord();
					    EnfermeroRecord enfermero = (EnfermeroRecord) cbEnfermeros.getSelectedItem();
					    en.idEnfermero = enfermero.idEnfermero;
					    en.nEnfermeros = 1;
					   
					    boolean b = true;
					    for (int i = 0; i < enflist.getSize(); i++) {
							if (en.idEnfermero == enflist.get(i).idEnfermero) {
							    b = false;
							}
					    }
					    if (b)
						enflist.addElement(en);
					}else if (cbEnfermeros.getSelectedIndex() == 0) {
						EnfermeroCitaRecord en = new EnfermeroCitaRecord();
					    EnfermeroRecord enfermero = (EnfermeroRecord) cbEnfermeros.getSelectedItem();
					    en.idEnfermero = enfermero.idEnfermero;
					    en.nEnfermeros = (int) spEnf.getValue();
					    enflist.addElement(en);
					}
					
				}
			});
		}
		return btAniadirE_1;
	}
	private JTextField getTxtEnfermeros() {
		if (txtEnfermeros == null) {
			txtEnfermeros = new JTextField();
			txtEnfermeros.setBorder(null);
			txtEnfermeros.setEditable(false);
			txtEnfermeros.setText("Enfermeros");
			txtEnfermeros.setFont(new Font("Times New Roman", Font.BOLD, 14));
			txtEnfermeros.setColumns(10);
		}
		return txtEnfermeros;
	}
	private JList<EnfermeroCitaRecord> getListEnfermeros() {
		if (listEnfermeros == null) {
			listEnfermeros = new JList();
			listEnfermeros.setModel(enflist);
		}
		return listEnfermeros;
	}
}
