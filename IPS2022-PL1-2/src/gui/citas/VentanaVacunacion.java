package gui.citas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import persistencia.PersistenceFactory;
import persistencia.cita.CitaRecord;
import persistencia.paciente.HistorialRecord;
import persistencia.paciente.PacienteRecord;
import persistencia.paciente.VacunaRecord;
import util.BusinessException;

public class VentanaVacunacion extends JFrame {

	private HistorialRecord historial;
	private CitaRecord cita;
	private VacunaRecord vacuna;
	private PacienteRecord paciente;
	
	private static LocalDate today = LocalDate.now();
	private static LocalTime now = LocalTime.now();
	
	private JPanel contentPane;
	private JLabel lblTituloVentana;
	private JLabel lblHora;
	private JLabel lblFecha;
	private JCheckBox chckbxRefuerzo;
	private JSpinner spnHora;
	private JLabel lblDosPuntos;
	private JSpinner spnMinutos;
	private JLabel lblFormato;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnYear;
	private JLabel lblDosis;
	private JTextField txtDosis;
	private JButton btnTerminarVacuna;

	/**
	 * Create the frame.
	 */
	public VentanaVacunacion(PacienteRecord paciente, CitaRecord cita, VacunaRecord vacuna) {
		setResizable(false);
		this.historial=PersistenceFactory.forPaciente().getHistorial(paciente.getDniPaciente());
		this.cita=cita;
		this.vacuna=vacuna;
		this.paciente=paciente;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 609, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTituloVentana());
		contentPane.add(getLblHora());
		contentPane.add(getLblFecha());
		contentPane.add(getChckbxRefuerzo());
		contentPane.add(getSpnHora());
		contentPane.add(getLblDosPuntos());
		contentPane.add(getSpnMinutos());
		contentPane.add(getLblFormato());
		contentPane.add(getSpnDia());
		contentPane.add(getSpnMes());
		contentPane.add(getSpnYear());
		contentPane.add(getLblDosis());
		contentPane.add(getTxtDosis());
		contentPane.add(getBtnTerminarVacuna());
	}
	private JLabel getLblTituloVentana() {
		if (lblTituloVentana == null) {
			lblTituloVentana = new JLabel("Vacunacion "+paciente.getNombre()+" "+paciente.getApellidos());
			lblTituloVentana.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblTituloVentana.setBounds(142, 11, 440, 50);
		}
		return lblTituloVentana;
	}
	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora de vacunacion: ");
			lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblHora.setBounds(32, 90, 155, 23);
		}
		return lblHora;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha de vacunacion: ");
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblFecha.setBounds(32, 146, 122, 23);
		}
		return lblFecha;
	}
	private JCheckBox getChckbxRefuerzo() {
		if (chckbxRefuerzo == null) {
			chckbxRefuerzo = new JCheckBox("Refuerzo");
			chckbxRefuerzo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chckbxRefuerzo.setBounds(451, 214, 141, 35);
		}
		return chckbxRefuerzo;
	}
	private JSpinner getSpnHora() {
		if (spnHora == null) {
			SpinnerModel smHora = new SpinnerNumberModel(now.getHour(), 0, 23, 1);
			spnHora = new JSpinner(smHora);
			spnHora.setBounds(154, 93, 37, 20);
		}
		return spnHora;
	}
	private JLabel getLblDosPuntos() {
		if (lblDosPuntos == null) {
			lblDosPuntos = new JLabel(":");
			lblDosPuntos.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDosPuntos.setBounds(194, 95, 5, 14);
		}
		return lblDosPuntos;
	}
	private JSpinner getSpnMinutos() {
		if (spnMinutos == null) {
			SpinnerModel smMinutos = new SpinnerNumberModel(now.getMinute(), 0, 59, 1);
			spnMinutos = new JSpinner(smMinutos);
			spnMinutos.setBounds(201, 93, 36, 20);
		}
		return spnMinutos;
	}
	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("dd              mm             yyyy");
			lblFormato.setBounds(176, 124, 173, 20);
		}
		return lblFormato;
	}
	private JSpinner getSpnDia() {
		if (spnDia == null) {
			SpinnerModel smDia = new SpinnerNumberModel(today.getDayOfMonth(), 1, 31, 1);
			spnDia = new JSpinner(smDia);
			spnDia.setBounds(164, 148, 41, 20);
		}
		return spnDia;
	}
	private JSpinner getSpnMes() {
		if (spnMes == null) {
			SpinnerModel smMes = new SpinnerNumberModel(today.getMonthValue(), 1, 12, 1);
			spnMes = new JSpinner(smMes);
			spnMes.setBounds(215, 148, 43, 20);
		}
		return spnMes;
	}
	private JSpinner getSpnYear() {
		if (spnYear == null) {
			SpinnerModel smYear = new SpinnerNumberModel(today.getYear(), 1950, 2050, 1);
			spnYear = new JSpinner(smYear);
			spnYear.setBounds(268, 148, 64, 20);
		}
		return spnYear;
	}
	private JLabel getLblDosis() {
		if (lblDosis == null) {
			lblDosis = new JLabel("Dosis");
			lblDosis.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDosis.setBounds(32, 198, 105, 20);
		}
		return lblDosis;
	}
	private JTextField getTxtDosis() {
		if (txtDosis == null) {
			txtDosis = new JTextField();
			txtDosis.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtDosis.setBounds(32, 223, 193, 26);
			txtDosis.setColumns(10);
			if (vacuna!=null)
				txtDosis.setText(vacuna.getDosis());
		}
		return txtDosis;
	}
	private JButton getBtnTerminarVacuna() {
		if (btnTerminarVacuna == null) {
			btnTerminarVacuna = new JButton("Terminar vacuna");
			btnTerminarVacuna.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vacunar();
				}
			});
			btnTerminarVacuna.setBounds(411, 300, 141, 23);
		}
		return btnTerminarVacuna;
	}
	
	private void vacunar() {
		if (!checkFields() || !confirm())
			return;
		VacunaRecord myVacuna;
		LocalDate fecha = LocalDate.of((Integer) getSpnYear().getValue(), (Integer) getSpnMes().getValue(), (Integer) getSpnDia().getValue());
		LocalTime hora = LocalTime.of((Integer) getSpnHora().getValue(), (Integer) getSpnMes().getValue());
		if (vacuna!=null) {
			vacuna.setFechaReal(fecha);
			vacuna.setHora(hora);
			myVacuna=vacuna;
		} else {
			int nextId = PersistenceFactory.forCita().getLastId("VACUNA", "IDVACUNA")+1;
			String idCita = ((cita==null) ? null : cita.idCita);
			String dosis = getTxtDosis().getText();
			boolean refuerzo = getChckbxRefuerzo().isSelected();
			myVacuna = new VacunaRecord(nextId, historial.getIdHistorial(), idCita, fecha, fecha, hora, dosis, refuerzo);
		}
		try {
			BusinessFactory.forPacienteService().vacunar(myVacuna);
		} catch (BusinessException e) {
			e.printStackTrace();
		} finally {
			if (cita!=null) {
				VentanaCita v = new VentanaCita(cita);
				v.setVisible(true);
			} else {
				VentanaHistorial v = new VentanaHistorial(paciente);
				v.setVisible(true);
			}
			dispose();
		}
	}
	
	private boolean confirm() {
		int result = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea confirmar la vacuna? No podrá modificarla despues");
		if (result == JOptionPane.YES_OPTION)
			return true;
		return false;
	}
	
	private boolean checkFields() {
		if (getTxtDosis().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, indique que dosis va a aplicar");
			return false;
		}
		return true;
	}
}
