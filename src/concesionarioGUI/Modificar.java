package concesionarioGUI;

import concesionarioFuncionalidad.Coche;
import concesionarioFuncionalidad.CocheNoExisteException;
import concesionarioFuncionalidad.Color;
import concesionarioFuncionalidad.ColorNoValidoException;
import concesionarioFuncionalidad.Concesionario;
import concesionarioFuncionalidad.MatriculaNoValidaException;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Clase Modificar. Nos permitirá modificar el color de un coche.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class Modificar extends DialogoPadre {
	private static final long serialVersionUID = 1L;
	private Coche coche;
	
	/**
	 * Constructor que recibe como parámetro el concesionario
	 * @param concesionario sobre el que se trabajará
	 */
	public Modificar(Concesionario concesionario){
		super();
		setTitle("Modificar");
		cancelButton.setText("Cancelar");
		okButton.setText("Modificar");
		
		botonesPorDefecto();
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					coche = concesionario.get(matricula.getText());
					mostrarCoche(coche);
					habilitarBotonesColores();
					okButton.setEnabled(true);
				} catch (MatriculaNoValidaException | CocheNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionarRespuesta(concesionario);
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
	 * Pregunta al usuario si realmente desea eliminar el coche
	 * @param concesionario con el que se trabaja
	 */
	private void gestionarRespuesta(Concesionario concesionario){
		switch(JOptionPane.showOptionDialog(contentPanel,
				"¿Está seguro de que desea modificarlo?", "Confirmar",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null)){

		case JOptionPane.YES_OPTION:
			try {
				coche.setColor(getColor());
				concesionario.almacen.set(concesionario.almacen.indexOf(coche), coche);
				concesionario.modificado=true;
				clear();
				botonesPorDefecto();
			} catch (ColorNoValidoException e) {
				JOptionPane.showMessageDialog(contentPanel,
						e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		default:
			clear();
			botonesPorDefecto();
		}
	}
	
	/**
	 * Habilita y deshabilita botones dejándolos como deben estar inicialmente en esta ventana
	 */
	private void botonesPorDefecto() {
		btnBuscar.setEnabled(true);
		okButton.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
	}
	
	/**
	 * Habilita los botones de selección del color
	 */
	private void habilitarBotonesColores(){
		rdbtnPlata.setEnabled(true);
		rdbtnRojo.setEnabled(true);
		rdbtnAzul.setEnabled(true);
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
