package concesionarioGUI;

import concesionarioFuncionalidad.Concesionario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase MostrarConcesionario. Nos permitir� ver todos los coches del concesionario
 * 
 * @author Miguel Angel L�pez Moyano
 * @version 1.0
 */
public class MostrarConcesionario extends DialogoPadre {
	private static final long serialVersionUID = 1L;
	private int indiceCoche = -1;
	
	/**
	 * Constructor que recibe como par�metro el concesionario
	 * @param concesionario sobre el que se trabajar�
	 */
	public MostrarConcesionario(Concesionario concesionario){
		super();
		setTitle("Mostrar concesionario");
		matricula.setEnabled(false);
		cancelButton.setText("Siguiente");
		okButton.setText("Anterior");
		
		botonesPorDefecto();		
		inicializar(concesionario);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior(concesionario);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente(concesionario);
			}
		});
	}

	/**
	 * Habilita y deshabilita botones dej�ndolos como deben estar inicialmente en esta ventana
	 */
	private void botonesPorDefecto() {
		btnBuscar.setEnabled(false);
		btnBuscar.setVisible(false);
		okButton.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnAzul.setEnabled(false);		
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
	}
	
	/**
	 * Inicializa el �ndice y muestra el primer coche del concesionario
	 * @param concesionario con el que se trabaja
	 */
	private void inicializar(Concesionario concesionario){
		if(concesionario.getAlmacen().size()==0){
			cancelButton.setEnabled(false);
			return;
		}
		if(concesionario.getAlmacen().size()==1)
			cancelButton.setEnabled(false);
		indiceCoche=0;
		mostrarCoche(concesionario.getAlmacen().get(indiceCoche));
	}
	
	/**
	 * Comprueba si es el primer coche o el �ltimo. En funci�n de eso se habilitar�n o deshabiltar�n
	 * los botones Anterior y Siguiente
	 * @param concesionario con el que se trabaja
	 */
	private void comprobarBotones(Concesionario concesionario) {
		if(indiceCoche==0)
			okButton.setEnabled(false);
		else
			okButton.setEnabled(true);
		
		if(indiceCoche==concesionario.getAlmacen().size()-1)
			cancelButton.setEnabled(false);
		else
			cancelButton.setEnabled(true);
	}
	
	/**
	 * Muestra el siguiente coche del concesionario
	 * @param concesionario con el que se trabaja
	 */
	private void mostrarSiguiente(Concesionario concesionario){
		mostrarCoche(concesionario.getAlmacen().get(++indiceCoche));
		comprobarBotones(concesionario);
	
	}
	
	/**
	 * Muestra el anterior coche del concesionario
	 * @param concesionario con el que se trabaja
	 */
	private void mostrarAnterior(Concesionario concesionario){
		mostrarCoche(concesionario.getAlmacen().get(--indiceCoche));
		comprobarBotones(concesionario);
	}
}
