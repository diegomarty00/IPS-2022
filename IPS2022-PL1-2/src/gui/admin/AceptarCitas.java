package gui.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.cita.operaciones.EliminarCita;
import persistencia.cita.CitaRecord;
import persistencia.cita.impl.CitaGatewayImpl;
import util.BusinessException;

public class AceptarCitas extends JFrame {

	private JPanel contentPane;



	private JLabel lblTitulo;
	private JButton btnBuscar;

	private static LocalDate today = LocalDate.now();
	private JScrollPane scrollPane;
	private JList<CitaRecord> list;
	List<CitaRecord> citas;
	private DefaultListModel<CitaRecord> modelo;
	private JButton btEliminar;
	private JButton btModificar;
	private JButton btConfirmarCita;


	public AceptarCitas(){
		setTitle("Aceptar Citas ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		modelo = new DefaultListModel<>();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());
		contentPane.add(getBtnBuscar());
		contentPane.add(getScrollPane());
		contentPane.add(getBtEliminar());
		contentPane.add(getBtModificar());
		contentPane.add(getBtConfirmarCita());
		searchCitas();
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Modifica citas");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitulo.setBounds(10, 11, 177, 39);
		}
		return lblTitulo;
	}

	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchCitas();
				}
			});
			btnBuscar.setBounds(260, 23, 89, 23);
		}
		return btnBuscar;
	}

	private void searchCitas() {
		try {
			modelo.clear();
			CitaGatewayImpl ci = new CitaGatewayImpl();
			citas = ci.findAllNc();
			for (CitaRecord cita : citas) {
				modelo.addElement(cita);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 69, 547, 246);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}

	private JList<CitaRecord> getList() {
		if (list == null) {
			list = new JList<CitaRecord>(modelo);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					CitaRecord citaPulsada = (CitaRecord) list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
					getBtEliminar().setEnabled(true);
					getBtModificar().setEnabled(true);
					getBtConfirmarCita().setEnabled(true);

				}
			});
		}
		return list;
	}


	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Cancelar Cita");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarCita();
					searchCitas();
				}
			});
			btEliminar.setFont(new Font("Times New Roman", Font.BOLD, 14));
			btEliminar.setEnabled(false);
			btEliminar.setBounds(10, 362, 124, 39);
		}
		return btEliminar;
	}

	private void eliminarCita() {
		CitaRecord citaPulsada = getList().getSelectedValue();
		EliminarCita.removeCita(citaPulsada.idCita);
	}

	private JButton getBtModificar() {
		if (btModificar == null) {
			btModificar = new JButton("Modificar");
			btModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModificarCitas frame = new ModificarCitas(getList().getSelectedValue());
					frame.setVisible(true);
				}
			});
			btModificar.setEnabled(false);
			btModificar.setFont(new Font("Times New Roman", Font.BOLD, 14));
			btModificar.setBounds(316, 362, 124, 39);
		}
		return btModificar;
	}

	private void confirmarCita() {
		CitaGatewayImpl ci = new CitaGatewayImpl();
		ci.ConfirCita(getList().getSelectedValue().idCita);
		searchCitas();

	}

	private JButton getBtConfirmarCita() {
		if (btConfirmarCita == null) {
			btConfirmarCita = new JButton("Confirmar Cita");
			btConfirmarCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmarCita();

				}
			});
			btConfirmarCita.setFont(new Font("Times New Roman", Font.BOLD, 14));
			btConfirmarCita.setBounds(144, 362, 152, 39);
			btConfirmarCita.setEnabled(false);
		}
		return btConfirmarCita;
	}
}
