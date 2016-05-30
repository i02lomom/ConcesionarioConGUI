package concesionarioGUI;

import concesionarioFuncionalidad.Coche;
import concesionarioFuncionalidad.CocheNoExisteException;
import concesionarioFuncionalidad.Concesionario;
import concesionarioFuncionalidad.MatriculaNoValidaException;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Clase MostrarPorMatricula. Muestra el coche a trav�s de su matr�cula.
 * 
 * @author Miguel Angel L�pez Moyano
 * @version 1.0
 */
public class MostrarPorMatricula extends DialogoPadre {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor que recibe como par�metro el concesionario
	 * @param concesionario sobre el que se trabajar�
	 */
	public MostrarPorMatricula(Concesionario concesionario){
		super();
		setTitle("Buscar por matr�cula");
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
	 * Habilita y deshabilita botones dej�ndolos como deben estar inicialmente en esta ventana
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
