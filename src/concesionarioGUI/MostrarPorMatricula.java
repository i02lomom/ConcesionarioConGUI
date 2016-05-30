package concesionarioGUI;

import concesionarioFuncionalidad.Coche;
import concesionarioFuncionalidad.CocheNoExisteException;
import concesionarioFuncionalidad.Concesionario;
import concesionarioFuncionalidad.MatriculaNoValidaException;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Clase MostrarPorMatricula. Muestra el coche a través de su matrícula.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class MostrarPorMatricula extends DialogoPadre {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor que recibe como parámetro el concesionario
	 * @param concesionario sobre el que se trabajará
	 */
	public MostrarPorMatricula(Concesionario concesionario){
		super();
		setTitle("Buscar por matrícula");
		cancelButton.setText("Salir");
		okButton.setText("Ok");
		
		botonesPorDefecto();
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coche coche = concesionario.get(matricula.getText());
					mostrarCoche(coche);
				} catch (MatriculaNoValidaException | CocheNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
