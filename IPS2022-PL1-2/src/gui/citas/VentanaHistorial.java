package gui.citas;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public class VentanaHistorial extends JFrame {

	PacienteRecord paciente;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblHistorial;
	private JList<HistorialRecord> list;
	List<HistorialRecord> historial;
	private DefaultListModel<HistorialRecord> modelo;

	/**
	 * Create the frame.
	 */
	public VentanaHistorial(PacienteRecord paciente) {
		this.paciente=paciente;
		
		modelo = new DefaultListModel<HistorialRecord>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblHistorial());
		
		showHistorial();
	}
	private void showHistorial() {
		try {
			modelo.clear();
			historial = BusinessFactory.forPacienteService().getHistorialPaciente(paciente.getDniPaciente());
			for (HistorialRecord hist : historial)
				modelo.addElement(hist);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(43, 88, 520, 307);
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JLabel getLblHistorial() {
		if (lblHistorial == null) {
			lblHistorial = new JLabel("Historial");
			lblHistorial.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblHistorial.setBounds(10, 11, 258, 54);
		}
		return lblHistorial;
	}
	private JList<HistorialRecord> getList() {
		if (list == null) {
			list = new JList<HistorialRecord>(modelo);
		}
		return list;
	}
}
