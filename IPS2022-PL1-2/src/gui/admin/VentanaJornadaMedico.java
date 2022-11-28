package gui.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import business.BusinessFactory;
import persistencia.admin.JornadaRecord;
import persistencia.medico.MedicoRecord;
import util.BusinessException;

public class VentanaJornadaMedico extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private MedicoRecord medico;
    private JPanel pnInicioJornada;
    private JPanel pnFinJornada;
    private JTextField textInicioJornada;
    private JTextField textFinJornada;
    private JLabel lblLunes;
    private JLabel lblMartes;
    private JLabel lblMiercoles;
    private JLabel lblJueves;
    private JLabel lblViernes;
    private JLabel lblSabado;
    private JLabel lblDomingo;
    private JTextField textLunes;
    private JTextField textMartes;
    private JTextField textMiercoles;
    private JTextField textJueves;
    private JTextField textViernes;
    private JTextField textSabado;
    private JTextField textDomingo;
    private JButton btnAtras;
    private List<JornadaRecord> lista;

    public VentanaJornadaMedico(MedicoRecord medico) {

	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 475, 382);
	this.medico = medico;
	setTitle("Jornada de " + medico.nombre + " " + medico.apellidos
		+ " - Lic: " + medico.idMedico);

	try {
	    lista = BusinessFactory.forAdminService()
		    .listarJornadasMedico(medico.idMedico);
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getPnInicioJornada());
	contentPane.add(getPnFinJornada());
	contentPane.add(getLblLunes());
	contentPane.add(getLblMartes());
	contentPane.add(getLblMiercoles());
	contentPane.add(getLblJueves());
	contentPane.add(getLblViernes());
	contentPane.add(getLblSabado());
	contentPane.add(getLblDomingo());
	contentPane.add(getTextLunes());
	contentPane.add(getTextMartes());
	contentPane.add(getTextMiercoles());
	contentPane.add(getTextJueves());
	contentPane.add(getTextViernes());
	contentPane.add(getTextSabado());
	contentPane.add(getTextDomingo());
	contentPane.add(getBtnAtras());
    }

    private JPanel getPnInicioJornada() {
	if (pnInicioJornada == null) {
	    pnInicioJornada = new JPanel();
	    pnInicioJornada.setLayout(null);
	    pnInicioJornada.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Inicio de la Jornada", TitledBorder.CENTER,
		    TitledBorder.TOP, null, new Color(0, 0, 0)));
	    pnInicioJornada.setBounds(10, 11, 212, 85);
	    pnInicioJornada.add(getTextInicioJornada());
	}
	return pnInicioJornada;
    }

    private JPanel getPnFinJornada() {
	if (pnFinJornada == null) {
	    pnFinJornada = new JPanel();
	    pnFinJornada.setLayout(null);
	    pnFinJornada.setBorder(new TitledBorder(
		    new EtchedBorder(EtchedBorder.LOWERED,
			    new Color(255, 255, 255), new Color(160, 160, 160)),
		    "Fin de la Jornada", TitledBorder.CENTER, TitledBorder.TOP,
		    null, new Color(0, 0, 0)));
	    pnFinJornada.setBounds(232, 11, 212, 85);
	    pnFinJornada.add(getTextFinJornada());
	}
	return pnFinJornada;
    }

    private JTextField getTextInicioJornada() {
	if (textInicioJornada == null) {
	    textInicioJornada = new JTextField();
	    textInicioJornada.setHorizontalAlignment(SwingConstants.CENTER);
	    textInicioJornada.setEditable(false);
	    try {
		textInicioJornada.setText(lista.get(0).fin.getDay() + " / "
			+ (lista.get(0).fin.getMonth() + 1) + " / "
			+ (lista.get(0).fin.getYear() + 1900));
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textInicioJornada.setBounds(21, 33, 168, 26);
	    textInicioJornada.setColumns(10);
	}
	return textInicioJornada;
    }

    private JTextField getTextFinJornada() {
	if (textFinJornada == null) {
	    textFinJornada = new JTextField();
	    textFinJornada.setHorizontalAlignment(SwingConstants.CENTER);
	    textFinJornada.setEditable(false);
	    try {
		textFinJornada.setText(lista.get(lista.size() - 1).fin.getDay()
			+ " / "
			+ (lista.get(lista.size() - 1).fin.getMonth() + 1)
			+ " / "
			+ (lista.get(lista.size() - 1).fin.getYear() + 1900));
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textFinJornada.setColumns(10);
	    textFinJornada.setBounds(21, 31, 168, 26);
	}
	return textFinJornada;
    }

    private JLabel getLblLunes() {
	if (lblLunes == null) {
	    lblLunes = new JLabel("Lunes:");
	    lblLunes.setBounds(77, 121, 67, 14);
	}
	return lblLunes;
    }

    private JLabel getLblMartes() {
	if (lblMartes == null) {
	    lblMartes = new JLabel("Martes: ");
	    lblMartes.setBounds(77, 146, 67, 14);
	}
	return lblMartes;
    }

    private JLabel getLblMiercoles() {
	if (lblMiercoles == null) {
	    lblMiercoles = new JLabel("Miercoles: ");
	    lblMiercoles.setBounds(77, 171, 67, 14);
	}
	return lblMiercoles;
    }

    private JLabel getLblJueves() {
	if (lblJueves == null) {
	    lblJueves = new JLabel("Jueves: ");
	    lblJueves.setBounds(77, 196, 67, 14);
	}
	return lblJueves;
    }

    private JLabel getLblViernes() {
	if (lblViernes == null) {
	    lblViernes = new JLabel("Viernes: ");
	    lblViernes.setBounds(77, 220, 67, 14);
	}
	return lblViernes;
    }

    private JLabel getLblSabado() {
	if (lblSabado == null) {
	    lblSabado = new JLabel("Sabado: ");
	    lblSabado.setBounds(77, 245, 67, 14);
	}
	return lblSabado;
    }

    private JLabel getLblDomingo() {
	if (lblDomingo == null) {
	    lblDomingo = new JLabel("Domingo: ");
	    lblDomingo.setBounds(77, 270, 67, 14);
	}
	return lblDomingo;
    }

    private JTextField getTextLunes() {
	String cadena = "";
	if (textLunes == null) {
	    textLunes = new JTextField();
	    textLunes.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia.equals("Lunes"))
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textLunes.setText(cadena);
	    textLunes.setBounds(153, 118, 227, 20);
	    textLunes.setColumns(10);
	}
	return textLunes;
    }

    private JTextField getTextMartes() {
	String cadena = "";
	if (textMartes == null) {
	    textMartes = new JTextField();
	    textMartes.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia == "Martes")
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textMartes.setText(cadena);
	    textMartes.setColumns(10);
	    textMartes.setBounds(153, 143, 227, 20);
	}
	return textMartes;
    }

    private JTextField getTextMiercoles() {
	String cadena = "";
	if (textMiercoles == null) {
	    textMiercoles = new JTextField();
	    textMiercoles.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia == "Miercoles")
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textMiercoles.setText(cadena);
	    textMiercoles.setColumns(10);
	    textMiercoles.setBounds(153, 168, 227, 20);
	}
	return textMiercoles;
    }

    private JTextField getTextJueves() {
	String cadena = "";
	if (textJueves == null) {
	    textJueves = new JTextField();
	    textJueves.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia == "Jueves")
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textJueves.setText(cadena);
	    textJueves.setColumns(10);
	    textJueves.setBounds(153, 193, 227, 20);
	}
	return textJueves;
    }

    private JTextField getTextViernes() {
	String cadena = "";
	if (textViernes == null) {
	    textViernes = new JTextField();
	    textViernes.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia == "Viernes")
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textViernes.setText(cadena);
	    textViernes.setColumns(10);
	    textViernes.setBounds(153, 217, 227, 20);
	}
	return textViernes;
    }

    private JTextField getTextSabado() {
	String cadena = "";
	if (textSabado == null) {
	    textSabado = new JTextField();
	    textSabado.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia == "Sabado")
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textSabado.setText(cadena);
	    textSabado.setColumns(10);
	    textSabado.setBounds(154, 242, 227, 20);
	}
	return textSabado;
    }

    private JTextField getTextDomingo() {
	String cadena = "";
	if (textDomingo == null) {
	    textDomingo = new JTextField();
	    textDomingo.setEditable(false);
	    try {
		for (JornadaRecord jornada : lista)
		    if (jornada.dia == "Domingo")
			cadena += jornada.inicio.getHours() + ":"
				+ jornada.inicio.getMinutes() + "-"
				+ jornada.fin.getHours() + ":"
				+ jornada.fin.getMinutes() + "  ";
	    } catch (Exception e) {
		textInicioJornada.setText("Sin datos");
	    }
	    textDomingo.setText(cadena);
	    textDomingo.setColumns(10);
	    textDomingo.setBounds(153, 267, 227, 20);
	}
	return textDomingo;
    }

    private JButton getBtnAtras() {
	if (btnAtras == null) {
	    btnAtras = new JButton("Retroceder");
	    btnAtras.setBackground(Color.RED);
	    btnAtras.setBounds(313, 309, 136, 23);
	    btnAtras.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	}
	return btnAtras;
    }
}
