package concesionarioGUI;

import concesionarioFuncionalidad.Coche;
import concesionarioFuncionalidad.CocheNoExisteException;
import concesionarioFuncionalidad.Concesionario;
import concesionarioFuncionalidad.MatriculaNoValidaException;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Clase Baja. Nos permitirá eliminar coches del concesionario.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class Baja extends DialogoPadre {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe como parámetro el concesionario
	 * @param concesionario sobre el que se trabajará
	 */
	public Baja(Concesionario concesionario) {
		super();
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				setVisible(false);
			}
		});
		cancelButton.setText("Cancelar");
		okButton.setText("Baja");
		setTitle("Baja");

		botonesPorDefecto();
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coche coche = concesionario.get(matricula.getText());
					mostrarCoche(coche);
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
	}

	/**
	 * Pregunta al usuario si realmente desea eliminar el coche
	 * @param concesionario con el que se trabaja
	 */
	private void gestionarRespuesta(Concesionario concesionario){
		switch(JOptionPane.showOptionDialog(contentPanel,
							"¿Está seguro de que desea eliminarlo?", "Confirmar",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null)){
		
		case JOptionPane.YES_OPTION:
			try {
				concesionario.eliminar(matricula.getText());
				concesionario.modificado=true;
				clear();
			} catch (MatriculaNoValidaException e) {
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
	
}
