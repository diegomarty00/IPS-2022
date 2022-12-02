package gui.citas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.tree.DefaultTreeModel;

import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.cita.DiagnosticoRecord;
import persistencia.enfermero.EnfermeroRecord;
import persistencia.medico.MedicoRecord;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDiagnosticos extends JFrame {

	private JPanel contentPane;
	private JLabel lblTituloPantalla;
	private JLabel lblSecciones;
	private JScrollPane scrollPaneSecciones;
	private JTree treeSecciones;
	private JScrollPane scrollPanePosibles;
	private JScrollPane scrollSeleccionadas;
	private JList listPossiblesDiag;
	private JList<DiagnosticoRecord> listDiagSeleccionados;
	private JButton btnConfirmarSeleccion;
	private JButton btnEliminarDiagnostico;

	private HashMap<String, List<String>> posibles;

	CitaRecord cita; MedicoRecord medico; EnfermeroRecord enfermero;
	
	List<DiagnosticoRecord> myDiagnosticos;
	List<DiagnosticoRecord> initialDiagnosticos;
 private JButton btnSeleccionar;
	
	/**
	 * Create the frame.
	 * @param enfermero 
	 * @param medico 
	 * @param cita 
	 */
	public VentanaDiagnosticos(CitaRecord cita, MedicoRecord medico, EnfermeroRecord enfermero) {
		this.cita = cita;
		this.medico = medico;
		this.enfermero=enfermero;
		
		myDiagnosticos = PersistenceFactory.forCita().getDiagnosticos(cita.idCita);
		initialDiagnosticos = PersistenceFactory.forCita().getDiagnosticos(cita.idCita);
		
		posibles = new HashMap<>();
		rellenarPosibles();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 861, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTituloPantalla());
		contentPane.add(getLblSecciones());
		contentPane.add(getScrollPaneSecciones());
		contentPane.add(getScrollPanePosibles());
		contentPane.add(getScrollSeleccionadas());
		contentPane.add(getBtnConfirmarSeleccion());
		contentPane.add(getBtnEliminarDiagnostico());
		contentPane.add(getBtnSeleccionar());
		
		updateGetListDiagSeleccionados();
	}

	private JLabel getLblTituloPantalla() {
		if (lblTituloPantalla == null) {
			lblTituloPantalla = new JLabel("Diagnosticos ");
			lblTituloPantalla.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lblTituloPantalla.setBounds(158, 11, 449, 49);
		}
		return lblTituloPantalla;
	}
	private JLabel getLblSecciones() {
		if (lblSecciones == null) {
			lblSecciones = new JLabel("Secciones");
			lblSecciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSecciones.setBounds(42, 81, 169, 38);
		}
		return lblSecciones;
	}
	private JScrollPane getScrollPaneSecciones() {
		if (scrollPaneSecciones == null) {
			scrollPaneSecciones = new JScrollPane();
			scrollPaneSecciones.setBounds(24, 130, 349, 315);
			scrollPaneSecciones.setViewportView(getTreeSecciones());
		}
		return scrollPaneSecciones;
	}
	private JTree getTreeSecciones() {
		if (treeSecciones == null) {
			treeSecciones = new JTree();
			treeSecciones.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode selectedNode = 
						       (DefaultMutableTreeNode)treeSecciones.getLastSelectedPathComponent();
					if (selectedNode.isLeaf() && posibles.keySet().contains(selectedNode.getUserObject().toString())) {
						DefaultListModel<String> model = new DefaultListModel<String>();
						model.addAll(posibles.get(selectedNode.getUserObject().toString()));
						getListPossiblesDiag().setModel(model);
					}
				}
			});
			treeSecciones.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("JTree") {
					{
						DefaultMutableTreeNode node_1;
						DefaultMutableTreeNode node_2;
						node_1 = new DefaultMutableTreeNode("CIE-10-ES Diagnosticos 2022");
							node_2 = new DefaultMutableTreeNode("CIERTAS ENFERMEDADES INFECCIOSAS Y PARASITARIAS (A00-B99)");
								node_2.add(new DefaultMutableTreeNode("TUBERCULOSIS (A15-A19)"));
								node_2.add(new DefaultMutableTreeNode("RICKETTSIOSIS (A75-A79)"));
							node_1.add(node_2);
							node_2 = new DefaultMutableTreeNode("ENFERMEDADES DEL SISTEMA NERVIOSO (G00-G99)");
								node_2.add(new DefaultMutableTreeNode("ENFERMEDADES DESMIELINIZANTES DEL SISTEMA NERVIOSO CENTRAL (G35-G37)"));
								node_2.add(new DefaultMutableTreeNode("TRASTORNOS EPIS\u00D3DICOS Y PAROX\u00CDSTICOS (G40-G47)"));
							node_1.add(node_2);
							node_2 = new DefaultMutableTreeNode("EMBARAZO, PARTO Y PUERPERIO (O00-O9A)");
								node_2.add(new DefaultMutableTreeNode("EMBARAZO CON RESULTADO ABORTIVO (O00-O08)"));
								node_2.add(new DefaultMutableTreeNode("CONTACTO PARA EL PARTO (O80-O82)"));
							node_1.add(node_2);
//							node_2 = new DefaultMutableTreeNode("MALFORMACIONES CONG\u00C9NITAS, DEFORMIDADES Y ANOMAL\u00CDAS CROMOS\u00D3MICAS (Q00-Q99)");
//								node_2.add(new DefaultMutableTreeNode("MALFORMACIONES CONG\u00C9NITAS DE LOS OJOS, O\u00CDDOS, CARA Y CUELLO (Q10-Q18)"));
//								node_2.add(new DefaultMutableTreeNode("LABIO LEPORINO Y PALADAR HENDIDO (Q35-Q37)"));
//							node_1.add(node_2);
						add(node_1);
					}
				}
			));
			treeSecciones.setRootVisible(false);
		}
		return treeSecciones;
	}
	
	private void rellenarPosibles() {
		posibles.put("TUBERCULOSIS (A15-A19)", Arrays.asList("Tuberculosis respiratoria", "Tuberculosis del sistema nervioso", "Tuberculosis de otros organos", "Tuberculosis miliar"));
		posibles.put("RICKETTSIOSIS (A75-A79)", Arrays.asList("Fiebre tifoidea", "Fiebre maculosa [rickettsiosis transmitida por garrapatas]", "Fiebre", "Otras rickettsiosis"));
		posibles.put("ENFERMEDADES DESMIELINIZANTES DEL SISTEMA NERVIOSO CENTRAL (G35-G37)", Arrays.asList("Esclerosis múltiple", "Otras desmielinizaciones diseminadas agudas", "Otras enfermedades desmielinizantes del sistema nervioso central"));
		posibles.put("TRASTORNOS EPIS\\u00D3DICOS Y PAROX\\u00CDSTICOS (G40-G47)", Arrays.asList("Epilepsia y crisis epilépticas recurrentes", "Migraña", "Transtornos del sueño", "Accidentes isquemicos cerebrales"));
		posibles.put("EMBARAZO CON RESULTADO ABORTIVO (O00-O08)", Arrays.asList("Embarazo ectópico", "Mola hidatiforme", "Aborto espontaneo", "Intento fallido de interrupcion del embarazo"));
		posibles.put("CONTACTO PARA EL PARTO (O80-O82)", Arrays.asList("Admisión para el parto a término no complicado", "Admisión por parto por cesárea sin indicación"));
	}
	
	private JScrollPane getScrollPanePosibles() {
		if (scrollPanePosibles == null) {
			scrollPanePosibles = new JScrollPane();
			scrollPanePosibles.setBounds(453, 130, 349, 124);
			scrollPanePosibles.setViewportView(getListPossiblesDiag());
		}
		return scrollPanePosibles;
	}
	private JScrollPane getScrollSeleccionadas() {
		if (scrollSeleccionadas == null) {
			scrollSeleccionadas = new JScrollPane();
			scrollSeleccionadas.setBounds(453, 324, 349, 124);
			scrollSeleccionadas.setViewportView(getListDiagSeleccionados());
		}
		return scrollSeleccionadas;
	}
	private JList getListPossiblesDiag() {
		if (listPossiblesDiag == null) {
			listPossiblesDiag = new JList();
			listPossiblesDiag.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					getBtnSeleccionar().setVisible(true);
				}
			});
			listPossiblesDiag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listPossiblesDiag;
	}
	private JList<DiagnosticoRecord> getListDiagSeleccionados() {
		if (listDiagSeleccionados == null) {
			listDiagSeleccionados = new JList();
			listDiagSeleccionados.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					getBtnEliminarDiagnostico().setVisible(true);
				}
			});
			listDiagSeleccionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listDiagSeleccionados;
	}
	private JButton getBtnConfirmarSeleccion() {
		if (btnConfirmarSeleccion == null) {
			btnConfirmarSeleccion = new JButton("Confirmar Seleccion");
			btnConfirmarSeleccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmarYCerrar();
				}
			});
			btnConfirmarSeleccion.setBounds(24, 467, 145, 29);
		}
		return btnConfirmarSeleccion;
	}
	
	private void confirmarYCerrar() {
		//Comprobamos si hay nuevos diagnosticos
		for (DiagnosticoRecord diag : myDiagnosticos) {
			if (!initialDiagnosticos.contains(diag)) {
				PersistenceFactory.forCita().addDiagnostico(diag);
			}
		}
		
		//Comprobamos si se han quitado diagnosticos
		for (DiagnosticoRecord diag : initialDiagnosticos) {
			if (!myDiagnosticos.contains(diag)) {
				PersistenceFactory.forCita().deleteDiagnostico(diag);
			}
		}
		
		VentanaCita v = new VentanaCita(cita,medico, enfermero);
		v.setVisible(true);
		dispose();
	}
	
	private JButton getBtnEliminarDiagnostico() {
		if (btnEliminarDiagnostico == null) {
			btnEliminarDiagnostico = new JButton("Eliminar Diagnostico");
			btnEliminarDiagnostico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desSeleccionar(getListDiagSeleccionados().getSelectedValue());
				}
			});
			btnEliminarDiagnostico.setBounds(453, 459, 169, 23);
			btnEliminarDiagnostico.setVisible(false);
		}
		return btnEliminarDiagnostico;
	}
	private JButton getBtnSeleccionar() {
		if (btnSeleccionar == null) {
			btnSeleccionar = new JButton("Seleccionar Diagnostico");
			btnSeleccionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionar();
				}
			});
			btnSeleccionar.setBounds(453, 265, 169, 23);
			btnSeleccionar.setVisible(false);
		}
		return btnSeleccionar;
	}
	
	private void desSeleccionar(DiagnosticoRecord diag) {
		myDiagnosticos.remove(diag);
		updateGetListDiagSeleccionados();
	}
	
	private void seleccionar() {
		DiagnosticoRecord diagnostico = new DiagnosticoRecord((String)getListPossiblesDiag().getSelectedValue());
		diagnostico.setFechaAsignacion(LocalDate.now());
		diagnostico.setHoraAsginacion(LocalTime.now());
		diagnostico.setMedicoAsociado(medico);
		diagnostico.setIdCita(cita.idCita);
		myDiagnosticos.add(diagnostico);
		updateGetListDiagSeleccionados();
	}
	
	private void updateGetListDiagSeleccionados() {
		DefaultListModel<DiagnosticoRecord> model = new DefaultListModel<DiagnosticoRecord>();
		model.addAll(myDiagnosticos);
		listDiagSeleccionados.setModel(model);
	}
}
