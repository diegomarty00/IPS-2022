package gui.citas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class VentanaDiagnosticos extends JFrame {

	private JPanel contentPane;
	private JLabel lblTituloPantalla;
	private JLabel lblSecciones;
	private JScrollPane scrollPaneSecciones;
	private JTree treeSecciones;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JList listPossiblesDiag;
	private JList listDiagSeleccionados;
	private JButton btnConfirmarSeleccion;
	private JButton btnEliminarDiagnostico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDiagnosticos frame = new VentanaDiagnosticos();
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
	public VentanaDiagnosticos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 861, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTituloPantalla());
		contentPane.add(getLblSecciones());
		contentPane.add(getScrollPaneSecciones());
		contentPane.add(getScrollPane());
		contentPane.add(getScrollPane_1());
		contentPane.add(getBtnConfirmarSeleccion());
		contentPane.add(getBtnEliminarDiagnostico());
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
			treeSecciones.setRootVisible(false);
			treeSecciones.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("JTree") {
					{
						DefaultMutableTreeNode node_1;
						DefaultMutableTreeNode node_2;
						node_1 = new DefaultMutableTreeNode("CIE-10-ES Diagnosticos 2022");
							node_2 = new DefaultMutableTreeNode("CIERTAS ENFERMEDADES INFECCIOSAS Y PARASITARIAS");
								node_2.add(new DefaultMutableTreeNode("TUBERCULOSIS"));
								node_2.add(new DefaultMutableTreeNode("RICKETTOSIS"));
							node_1.add(node_2);
							node_2 = new DefaultMutableTreeNode("NEOPLASTIA");
								node_2.add(new DefaultMutableTreeNode("NEOPLASTIAS IN SITU"));
								node_2.add(new DefaultMutableTreeNode("TUMORES NEUROENDOCRINOS BENINGNOS"));
							node_1.add(node_2);
							node_2 = new DefaultMutableTreeNode("ENFERMEDADES DEL SISTEMA NERVIOSO");
								node_2.add(new DefaultMutableTreeNode("ENFERMEDADES INFLAMATORIAS DEL SISTEMA NERVIOSO"));
								node_2.add(new DefaultMutableTreeNode("TRASTORNOS EPISODICOS Y PAROXISTICOS"));
							node_1.add(node_2);
						add(node_1);
					}
				}
			));
		}
		return treeSecciones;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(453, 130, 349, 124);
			scrollPane.setViewportView(getListPossiblesDiag());
		}
		return scrollPane;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(453, 324, 349, 124);
			scrollPane_1.setViewportView(getListDiagSeleccionados());
		}
		return scrollPane_1;
	}
	private JList getListPossiblesDiag() {
		if (listPossiblesDiag == null) {
			listPossiblesDiag = new JList();
			listPossiblesDiag.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listPossiblesDiag;
	}
	private JList getListDiagSeleccionados() {
		if (listDiagSeleccionados == null) {
			listDiagSeleccionados = new JList();
			listDiagSeleccionados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listDiagSeleccionados;
	}
	private JButton getBtnConfirmarSeleccion() {
		if (btnConfirmarSeleccion == null) {
			btnConfirmarSeleccion = new JButton("Confirmar Seleccion");
			btnConfirmarSeleccion.setBounds(24, 467, 145, 29);
		}
		return btnConfirmarSeleccion;
	}
	private JButton getBtnEliminarDiagnostico() {
		if (btnEliminarDiagnostico == null) {
			btnEliminarDiagnostico = new JButton("Eliminar Diagnostico");
			btnEliminarDiagnostico.setBounds(453, 459, 145, 23);
		}
		return btnEliminarDiagnostico;
	}
}
