package gui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.cita.CitaRecord;
import persistencia.paciente.PacienteRecord;
import util.BusinessException;

public class AsignarInformacionContactoCitas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblProximasCitas;
    private JList<CitaRecord> list;
    private JButton btnModificar;
    private JButton btnSalir;
    private JLabel lblNombre;
    private JPanel panel;
    private JTextField textTelefono;
    private JPanel panel_1;
    private JTextField textCorreo;
    private JTextField textNombre;

    private String idCita;
    private static LocalDate today = LocalDate.now();
    private List<CitaRecord> citas;
    private DefaultListModel<CitaRecord> modelo = new DefaultListModel<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AsignarInformacionContactoCitas frame = new AsignarInformacionContactoCitas();
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
    public AsignarInformacionContactoCitas() {
	setResizable(false);
	setTitle("Informaci\u00F3n de contacto de las citas");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 628, 366);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLblProximasCitas());
	contentPane.add(getList());
	contentPane.add(getBtnModificar());
	contentPane.add(getBtnSalir());
	contentPane.add(getLblNombre());
	contentPane.add(getPanel());
	contentPane.add(getPanel_1());
	contentPane.add(getTextNombre());
    }

    private JLabel getLblProximasCitas() {
	if (lblProximasCitas == null) {
	    lblProximasCitas = new JLabel("Pr\u00F3ximas Citas");
	    lblProximasCitas.setBounds(10, 22, 297, 22);
	    lblProximasCitas.setHorizontalAlignment(SwingConstants.CENTER);
	    lblProximasCitas.setFont(new Font("Tahoma", Font.PLAIN, 18));
	}
	return lblProximasCitas;
    }

    private JList<CitaRecord> getList() {
	if (list == null) {
	    reinicioCitas();
	}

	return list;
    }

    private void reinicioCitas() {
	try {
	    modelo = new DefaultListModel<>();
	    citas = BusinessFactory.forCitaService().getCitasProximas(
		    today.getYear(), today.getMonthValue(),
		    today.getDayOfMonth());
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	for (CitaRecord cita : citas) {
	    modelo.addElement(cita);
	}
	list = new JList<CitaRecord>(modelo);
	list.setBounds(20, 55, 297, 214);
	list.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		CitaRecord citaPulsada = (CitaRecord) list.getModel()
			.getElementAt(list.locationToIndex(e.getPoint()));
		verCita(citaPulsada);
	    }
	});
    }

    private void verCita(CitaRecord cita) {
	try {
	    Optional<PacienteRecord> paciente = BusinessFactory
		    .forPacienteService().getById(cita.idPaciente);
	    idCita = cita.idCita;
	    getTextNombre().setText(paciente.get().getNombre() + " "
		    + paciente.get().getApellidos());
	    getTextTelefono().setEditable(true);
	    getTextCorreo().setEditable(true);
	    getTextTelefono().setText(cita.telefonoPaciente + "");
	    getTextCorreo().setText(cita.correoPaciente);
	} catch (BusinessException e) {
	    e.printStackTrace();
	}

    }

    private JButton getBtnModificar() {
	if (btnModificar == null) {
	    btnModificar = new JButton("Modificar");
	    btnModificar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    try {
			BusinessFactory.forCitaService().modificarCita(idCita,
				getTextCorreo().getText(),
				Integer.parseInt(getTextTelefono().getText()));
			JOptionPane.showMessageDialog(null,
				"Se ha realiado el cambio de información de contacto",
				"Cambio realizado", 1);
		    } catch (Exception e1) {
			JOptionPane.showMessageDialog(null,
				"Seleccione alguna cita para modificar los datos",
				"Error de modificación", 0);
		    } finally {
			reinicioCitas();
		    }
		}
	    });
	    btnModificar.setBackground(Color.GREEN);
	    btnModificar.setBounds(500, 293, 89, 23);
	}
	return btnModificar;
    }

    private JButton getBtnSalir() {
	if (btnSalir == null) {
	    btnSalir = new JButton("Salir");
	    btnSalir.setBackground(Color.RED);
	    btnSalir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btnSalir.setBounds(393, 293, 89, 23);
	}
	return btnSalir;
    }

    private JLabel getLblNombre() {
	if (lblNombre == null) {
	    lblNombre = new JLabel("Paciente: ");
	    lblNombre.setBounds(340, 79, 60, 14);
	}
	return lblNombre;
    }

    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setBorder(new TitledBorder(null, "Tel\u00E9fono de Contacto",
		    TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    panel.setBounds(340, 117, 248, 49);
	    panel.setLayout(null);
	    panel.add(getTextTelefono());
	}
	return panel;
    }

    private JTextField getTextTelefono() {
	if (textTelefono == null) {
	    textTelefono = new JTextField();
	    textTelefono.setEditable(false);
	    textTelefono.setBounds(10, 18, 228, 20);
	    textTelefono.setColumns(10);
	}
	return textTelefono;
    }

    private JPanel getPanel_1() {
	if (panel_1 == null) {
	    panel_1 = new JPanel();
	    panel_1.setLayout(null);
	    panel_1.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Correo electr\u00F3nico", TitledBorder.LEADING,
		    TitledBorder.TOP, null, new Color(0, 0, 0)));
	    panel_1.setBounds(340, 183, 248, 49);
	    panel_1.add(getTextCorreo());
	}
	return panel_1;
    }

    private JTextField getTextCorreo() {
	if (textCorreo == null) {
	    textCorreo = new JTextField();
	    textCorreo.setEditable(false);
	    textCorreo.setColumns(10);
	    textCorreo.setBounds(10, 18, 228, 20);
	}
	return textCorreo;
    }

    private JTextField getTextNombre() {
	if (textNombre == null) {
	    textNombre = new JTextField();
	    textNombre.setEditable(false);
	    textNombre.setBounds(401, 76, 173, 20);
	    textNombre.setColumns(10);
	}
	return textNombre;
    }
}
