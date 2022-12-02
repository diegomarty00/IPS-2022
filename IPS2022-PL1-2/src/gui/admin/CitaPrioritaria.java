package gui.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.cita.operaciones.CrearCita;
import business.cita.operaciones.ModificarHoras;
import gui.admin.DatosCita.ProcesarAccion;
import persistencia.cita.CitaRecord;
import persistencia.enfermero.EnfermeroCitaRecord;
import persistencia.especialidad.EspecialidadCitaRecord;
import persistencia.paciente.PacienteRecord;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class CitaPrioritaria extends JFrame {

	private JPanel contentPane;
	private JLabel lbDescriptivo;
	private JList jlCitas;
	private JLabel lbNuevoHorario;
	private JLabel lbInicio;
	private JComboBox cbInicio;
	private JLabel lbfin;
	private JComboBox cbFin;
	private JButton btConfimar;
	private JButton btCancelar;
	private JButton btFinalizar;
	private DatosCita dc = null;
	private PacienteRecord paciente;
	private String lugar ;
	private String anio;
	private String mes;
	private String dia ;
	private String hI;
	private String hF;
	private String email;
	private String telf;
	private String otros;
	private boolean urgente;
	private boolean prioritario;
	CrearCita cc;
	private DefaultListModel<CitaRecord> modelo = new DefaultListModel<>();
	DefaultListModel<EspecialidadCitaRecord> eslist;
	private DefaultListModel<String> modjlist;
	private DefaultListModel<EnfermeroCitaRecord> enflist;
	private ProcesarAccion pa= new ProcesarAccion(); 
	private List<String> listaH = setHorasL();
	private JButton btModificar;
	/**
	 * Create the frame.
	 * @param eslist 
	 * @param enflist 
	 * @param  
	 */
	public CitaPrioritaria(CrearCita cc,DatosCita a ,PacienteRecord paciente, boolean urgencia,String lugar, String anio , String mes , String dia, String horaE, String horaS,DefaultListModel<String> modjlist,
			String email, String telf,String otros,boolean prioridad, DefaultListModel<EspecialidadCitaRecord> eslist, DefaultListModel<EnfermeroCitaRecord> enflist) {
		this.cc = cc;
		this.eslist = eslist;
		dc = a;
		this.enflist = enflist;
		this.paciente = paciente;
		this.urgente = urgencia;
		this.lugar = lugar;
		this.anio = anio;
		this.mes = mes ;
		this.dia = dia ;
		this.hI = horaE;
		this.hF = horaS;
		this.modjlist = modjlist;
		this.prioritario = prioridad;
		this.email = email;
		this.telf = telf;
		this.otros = otros;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbDescriptivo());
		contentPane.add(getJlCitas());
		contentPane.add(getLbNuevoHorario());
		contentPane.add(getLbInicio());
		contentPane.add(getCbInicio());
		contentPane.add(getLbfin());
		contentPane.add(getCbFin());
		contentPane.add(getBtConfimar());
		contentPane.add(getBtCancelar());
		contentPane.add(getBtFinalizar());
		contentPane.add(getBtModificar());
	}
	private JLabel getLbDescriptivo() {
		if (lbDescriptivo == null) {
			lbDescriptivo = new JLabel("Citas que coinciden en el mismo horario");
			lbDescriptivo.setFont(new Font("Times New Roman", Font.BOLD, 16));
			lbDescriptivo.setHorizontalAlignment(SwingConstants.CENTER);
			lbDescriptivo.setBounds(10, 11, 489, 56);
		}
		return lbDescriptivo;
	}
	private JList<CitaRecord> getJlCitas() {
		if (jlCitas == null) {
			asignarModelo();
			jlCitas = new JList();
			jlCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlCitas.setModel(modelo);
			jlCitas.setBounds(10, 52, 469, 147);
		}
		return jlCitas;
	}
	private JLabel getLbNuevoHorario() {
		if (lbNuevoHorario == null) {
			lbNuevoHorario = new JLabel("Nuevo Horario ");
			lbNuevoHorario.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			lbNuevoHorario.setBounds(10, 236, 211, 27);
		}
		return lbNuevoHorario;
	}
	private JLabel getLbInicio() {
		if (lbInicio == null) {
			lbInicio = new JLabel("Inicio:");
			lbInicio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lbInicio.setBounds(10, 261, 46, 27);
		}
		return lbInicio;
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
	private JComboBox getCbInicio() {
		if (cbInicio == null) {
			cbInicio = new JComboBox();
			DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
			cbInicio.setBounds(50, 263, 59, 22);
			cbInicio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    setHFModel(cbInicio.getSelectedIndex());
				}
			    });
			    for (int i = 0; i < listaH.size(); i++) {
				mod.addElement(listaH.get(i));
			    }
			    cbInicio.setModel(mod);
			}
		return cbInicio;
	}
	
	private JLabel getLbfin() {
		if (lbfin == null) {
			lbfin = new JLabel("Fin");
			lbfin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			lbfin.setBounds(123, 261, 59, 27);
		}
		return lbfin;
	}
	private JComboBox getCbFin() {
		if (cbFin == null) {
			cbFin = new JComboBox();
			cbFin.setBounds(150, 263, 59, 22);
		}
		return cbFin;
	}
	
	

	    private void setHFModel(int j) {
		DefaultComboBoxModel mod = new DefaultComboBoxModel<>();
		for (int i = j; i < listaH.size(); i++) {
		    mod.addElement(listaH.get(i));
		}
		cbFin.setModel(mod);
	    }

	    
	private JButton getBtConfimar() {
		if (btConfimar == null) {
			btConfimar = new JButton("Confirmar");
			btConfimar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarHoras();
				}

				
			});
			btConfimar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			btConfimar.setBounds(231, 261, 100, 27);
		}
		return btConfimar;
	}
	
	private void cambiarHoras() {
		CitaRecord cita =(CitaRecord) getJlCitas().getSelectedValue();
		ModificarHoras.ModificarhoraCita(cita, (String)cbInicio.getSelectedItem(), (String)cbFin.getSelectedItem());
		asignarModelo();
	}
	
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete();
				}
			});
			btCancelar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			btCancelar.setBounds(410, 261, 89, 27);
		}
		return btCancelar;
	}
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setBounds(509, 261, 89, 27);
			btFinalizar.addActionListener(pa);
		}
		return btFinalizar;
	}
	
	private void asignarModelo() {
		modelo.clear();
		List< CitaRecord> citas= cc.citasChoque(anio, dia, mes, hI, hF);
		for (CitaRecord cita : citas) {
			modelo.addElement(cita);
		}
	}
	
	private void delete() {
		dispose();
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
	private void deletet() {
		dc.dispose();
		this.dispose();
	}
	
	class ProcesarAccion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			CrearCita cc = new CrearCita();
		    cc.crearCita(paciente,
				    urgente,
				    lugar,
				    anio,
				    mes,
				    dia,
				    hI,
				    hF,
				    email,
				    telf,otros,prioritario);
		    if(modjlist != null) {
		    	int size = modjlist.size();
				for (int i = 0; i < size; i++) {
				    cc.crearCitaMedico(modjlist.get(i));
				}
		    }
		    if(eslist !=null) {
		    	for(int i = 0 ; i < eslist.size() ; i++)
		    		cc.crearCitaEspecialidad(eslist.get(i));
		    }
		    if(enflist !=null) {
		    	for(int i = 0 ; i < enflist.size() ; i++)
		    		cc.crearCitaEnfermero(enflist.get(i));
		    }
		    
		    deletet();
		}
	}
	private JButton getBtModificar() {
		if (btModificar == null) {
			btModificar = new JButton("Modificar");
			btModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModificarCitas frame = new ModificarCitas(getJlCitas().getSelectedValue());
					frame.setVisible(true);
					asignarModelo();
					
				}
			});
			btModificar.setBounds(489, 70, 101, 27);
		}
		return btModificar;
	}
}
