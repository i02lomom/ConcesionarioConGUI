package concesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import concesionarioFuncionalidad.Coche;
import concesionarioFuncionalidad.Marca;
import concesionarioFuncionalidad.Modelo;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ButtonGroup;

/**
 * Clase DialogoPadre. Este diálogo contendrá todos los botones usados en el programa. Los botones
 * estarán habilitados o no en función del hijo.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class DialogoPadre extends JDialog {
	private static final long serialVersionUID = 1L;
	final JPanel contentPanel = new JPanel();
	JTextField matricula;
	protected JLabel lblMatrcula;
	protected JLabel lblMarca;
	protected JLabel lblModelo;
	protected JPanel color;
	protected JRadioButton rdbtnPlata;
	protected JRadioButton rdbtnRojo;
	protected JRadioButton rdbtnAzul;
	protected JComboBox<Marca> comboBoxMarca;
	protected JComboBox<Modelo> comboBoxModelo;
	protected JPanel buttonPane;
	protected JButton btnBuscar;
	protected JButton okButton;
	protected JButton cancelButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Lanza la aplicación.
	 */
	public static void main(String[] args) {
		try {
			DialogoPadre dialog = new DialogoPadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diálogo padre
	 */
	public DialogoPadre() {
		super();
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		
		lblMatrcula = new JLabel("Matr\u00EDcula");
		lblMatrcula.setBounds(87, 24, 62, 14);
		
		matricula = new JTextField();
		matricula.setBounds(172, 21, 86, 20);
		matricula.setColumns(10);
		
		color = new JPanel();
		color.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		color.setBounds(87, 125, 171, 97);
		color.setLayout(null);
		
		rdbtnPlata = new JRadioButton("Plata");
		rdbtnPlata.setBounds(93, 17, 72, 23);
		buttonGroup.add(rdbtnPlata);	
		color.add(rdbtnPlata);
		
		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.setBounds(93, 41, 72, 23);
		buttonGroup.add(rdbtnRojo);
		color.add(rdbtnRojo);
		
		rdbtnAzul = new JRadioButton("Azul");
		rdbtnAzul.setBounds(94, 67, 71, 23);
		buttonGroup.add(rdbtnAzul);
		color.add(rdbtnAzul);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(87, 56, 46, 14);
		
		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.setBounds(172, 52, 86, 22);
		comboBoxModelo = new JComboBox<Modelo>();
		comboBoxModelo.setBounds(172, 87, 86, 22);
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(87, 91, 46, 14);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(292, 20, 91, 23);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(lblMatrcula);
		contentPanel.add(matricula);
		contentPanel.add(btnBuscar);
		contentPanel.add(lblMarca);
		contentPanel.add(comboBoxMarca);
		contentPanel.add(lblModelo);
		contentPanel.add(comboBoxModelo);
		contentPanel.add(color);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				}
			}
	}
	
	/**
	 * Limpia los textos y opciones seleccionadas anteriormente.
	 */
	protected void clear() {
		matricula.setText("");
		buttonGroup.clearSelection();
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);	
	}
	
	/**
	 * Muestra el coche pasado como argumento
	 * @param coche a mostrar
	 */
	protected void mostrarCoche(Coche coche) {
		matricula.setText(coche.getMatricula());
		switch (coche.getColor()) {
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		case AZUL:
			rdbtnAzul.setSelected(true);
		}
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.addItem(coche.getModelo());
		comboBoxModelo.setSelectedItem(coche.getModelo());
	}
}
