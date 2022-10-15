package gui.citas;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import persistencia.cita.CitaRecord;
import util.BusinessException;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class VentanaCalendarioCitas extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnYear;
	private JButton btnBuscar;
	private JLabel lblFormato;

	private static LocalDate today = LocalDate.now();
	private JScrollPane scrollPane;
	private JList<CitaRecord> list;
	List<CitaRecord> citas;
	private DefaultListModel<CitaRecord> modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCalendarioCitas frame = new VentanaCalendarioCitas();
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
	public VentanaCalendarioCitas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		modelo = new DefaultListModel<>();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTitulo());
		contentPane.add(getSpnDia());
		contentPane.add(getSpnMes());
		contentPane.add(getSpnYear());
		contentPane.add(getBtnBuscar());
		contentPane.add(getLblFormato());
		contentPane.add(getScrollPane());
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Consultas del día");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitulo.setBounds(10, 11, 177, 39);
		}
		return lblTitulo;
	}

	private JSpinner getSpnDia() {
		if (spnDia == null) {
			SpinnerModel smDia = new SpinnerNumberModel(today.getDayOfMonth(), 1, 31, 1);
			spnDia = new JSpinner(smDia);
			spnDia.setBounds(202, 24, 41, 20);
		}
		return spnDia;
	}

	private JSpinner getSpnMes() {
		if (spnMes == null) {
			SpinnerModel smMes = new SpinnerNumberModel(today.getMonthValue(), 1, 12, 1);
			spnMes = new JSpinner(smMes);
			spnMes.setBounds(253, 24, 43, 20);
		}
		return spnMes;
	}

	private JSpinner getSpnYear() {
		if (spnYear == null) {
			SpinnerModel smYear = new SpinnerNumberModel(today.getYear(), 1950, 2050, 1);
			spnYear = new JSpinner(smYear);
			spnYear.setBounds(306, 24, 64, 20);
		}
		return spnYear;
	}

	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchCitas();
				}
			});
			btnBuscar.setBounds(409, 23, 89, 23);
		}
		return btnBuscar;
	}
	
	private void searchCitas() {
		try {
			modelo.clear();
			citas = BusinessFactory.forCitaService().getCitasDelDia((Integer)getSpnYear().getValue(), (Integer)getSpnMes().getValue(), (Integer)getSpnDia().getValue());
			for (CitaRecord cita : citas) {
				modelo.addElement(cita);
				System.out.println(cita);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("dd              mm             yyyy");
			lblFormato.setBounds(210, 0, 173, 20);
		}
		return lblFormato;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 69, 547, 344);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}

	private JList<CitaRecord> getList() {
		if (list == null) {
			list = new JList<CitaRecord>();
			list.setModel(modelo);
		}
		return list;
	}
}
