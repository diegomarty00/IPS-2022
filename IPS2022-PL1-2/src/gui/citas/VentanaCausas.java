package gui.citas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import business.BusinessFactory;
import business.cita.CitaService;
import persistencia.PersistenceFactory;
import persistencia.cita.CausaRecord;
import persistencia.cita.CitaRecord;
import util.BusinessException;

public class VentanaCausas extends JFrame {

	private JPanel contentPane;
	private JLabel lblSeleccionCausas;
	private JTable tablePosiblesCausas;
	private JScrollPane scrollPane;
	private JButton btnSave;
	private JTextField txtBuscar;
	private JButton btnBuscar;

	Object[][] dataTable;
	String[] columnNames;
	private JButton btnReset;

	CitaRecord cita;
	CitaService citaService = BusinessFactory.forCitaService();
	
	ArrayList<String> selectedCausas = new ArrayList<String>();
	
	/**
	 * Create the frame.
	 */
	public VentanaCausas(CitaRecord cita) {
		this.cita = cita;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblSeleccionCausas());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnSave());
		contentPane.add(getTxtBuscar());
		contentPane.add(getBtnBuscar());
		contentPane.add(getBtnReset());
	}

	private JLabel getLblSeleccionCausas() {
		if (lblSeleccionCausas == null) {
			lblSeleccionCausas = new JLabel("Seleccione las causas de la consulta");
			lblSeleccionCausas.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSeleccionCausas.setBounds(20, 11, 298, 42);
		}
		return lblSeleccionCausas;
	}
	private JTable getTablePosiblesCausas() {
		if (tablePosiblesCausas == null) {
			dataTable = getDataTable();
			columnNames= new String[] {"Causa", "Seleccionado"};
			DefaultTableModel model = new DefaultTableModel(dataTable, columnNames);
			tablePosiblesCausas = new JTable(model) {
				private static final long serialVersionUID = 1L;

				@Override
				public Class<?> getColumnClass(int column) {
					switch (column) {
						case 0: 
							return String.class;
						default: 
							return Boolean.class;
					}
				}
			};
		}
		return tablePosiblesCausas;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 100, 534, 248);
			scrollPane.setViewportView(getTablePosiblesCausas());
		}
		return scrollPane;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Guardar Selecci\u00F3n");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveSelection();
				}
			});
			btnSave.setBounds(210, 359, 149, 23);
		}
		return btnSave;
	}
	private JTextField getTxtBuscar() {
		if (txtBuscar == null) {
			txtBuscar = new JTextField();
			txtBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			txtBuscar.setBounds(20, 69, 368, 20);
			txtBuscar.setColumns(10);
		}
		return txtBuscar;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					search();
				}
			});
			btnBuscar.setBounds(494, 66, 89, 23);
		}
		return btnBuscar;
	}
	
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("Limpiar");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getTxtBuscar().setText("");
					search();
				}
			});
			btnReset.setBounds(398, 66, 89, 23);
		}
		return btnReset;
	}
	
	private void search() {
		checkSelected();
		String text = getTxtBuscar().getText().toLowerCase();
		List<Object[]> dataFiltered = new ArrayList<Object[]>();
		String tituloCausa;
		for (int i = 0; i < dataTable.length; i++) {
			tituloCausa=((String)dataTable[i][0]).toLowerCase();
			if (tituloCausa.contains(text)) {
				dataFiltered.add(dataTable[i]);
			}
		}
		tablePosiblesCausas.setModel(new DefaultTableModel(dataFiltered.stream().toArray(Object[][] ::new), columnNames));
	}
	
	private Object[][] getDataTable() {
		List<String> causas = new ArrayList<String>(Arrays.asList(BusinessFactory.forCitaService().getPosiblesCausas()));
		List<Object[]> data = new ArrayList<Object[]>();
		List<CausaRecord> causasRecords = PersistenceFactory.forCita().getCausas(cita.idCita);
		for (CausaRecord causa : causasRecords) {
			if (causas.contains(causa.getTitulo())) {
				data.add(new Object[] {causa.getTitulo(), Boolean.TRUE});
				causas.remove(causa.getTitulo());
			}
		}
		for (String causa : causas) {
			data.add(new Object[] {causa, Boolean.FALSE});
		}
		
		return data.stream().toArray(Object[][] ::new);
	}
	
	private void checkSelected() {
		TableModel model = getTablePosiblesCausas().getModel();
		String tituloCausa;
		for (int i = 0; i < model.getRowCount(); i++) {
			tituloCausa=(String)model.getValueAt(i, 0);
			if ((boolean)model.getValueAt(i, 1)==true) {
				if (!selectedCausas.contains(tituloCausa))
					selectedCausas.add(tituloCausa);
			} else {
				if (selectedCausas.contains(tituloCausa))
					selectedCausas.remove(tituloCausa);
			}
		}
	}

	private void saveSelection() {
		try {
			checkSelected();
			BusinessFactory.forCitaService().updateCausas(cita.idCita, selectedCausas);
			VentanaCita ventanaCita = new VentanaCita(cita);
			ventanaCita.setVisible(true);
			this.dispose();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
