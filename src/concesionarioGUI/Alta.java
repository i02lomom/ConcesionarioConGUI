package concesionarioGUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import concesionarioFuncionalidad.Concesionario;
import concesionarioFuncionalidad.Marca;
import concesionarioFuncionalidad.Modelo;
import concesionarioFuncionalidad.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase Alta. Nos permitirá añadir coches al concesionario.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class Alta extends DialogoPadre {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe como parámetro el concesionario
	 * @param concesionario sobre el que se trabajará
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Alta(Concesionario concesionario) {
		super();
		setTitle("Alta");
		
		cancelButton.setText("Cancelar");
		okButton.setText("Alta");
		btnBuscar.setEnabled(false);
		
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
			}
		});
		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values()));
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadir(concesionario);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				setVisible(false);
			}
		});
	}
	
	/**
	 * Añade el coche al concesionario
	 * @param concesionario con el que se trabaja
	 */
	private void anadir(Concesionario concesionario) {
		try {
			concesionario.annadir(matricula.getText(), getColor(),
					(Modelo) comboBoxModelo.getSelectedItem());
			JOptionPane.showMessageDialog(contentPanel,
					"Se ha añadido el coche");
			concesionario.modificado=true;
			clear();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(contentPanel,
					e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Devuelve un array con los modelos 
	 * @param comboBoxMarca con la marca seleccionada
	 * @return array de modelos
	 */
	private Object[] getModelo(JComboBox<Marca> comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca)
				modelos.add(m);
		}
		return modelos.toArray();
	}
	
	/**
	 * Devuelve el color seleccionado
	 * @return color seleccionado
	 */
	private Color getColor() {
		if (rdbtnPlata.isSelected())
			return Color.PLATA;
		else if (rdbtnRojo.isSelected())
			return Color.ROJO;
		else if (rdbtnAzul.isSelected())
			return Color.AZUL;
		else
			return null;
	}
}
