package concesionarioFuncionalidad;

import java.io.File;
import java.io.IOException;

/**
 * Clase Gestion. Nos permite gestionar distintos aspectos del concesionario, la mayoría relativa a ficheros.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class Gestion {
	protected static File file;
	protected static boolean modificado=false;
	public static Concesionario concesionario = new Concesionario();
	
	/**
	 * Indica si se ha modificado el concesionario
	 * @return true si se ha modificado y false si no.
	 */
	public static boolean estaModificado() {
		return modificado;
	}

	/**
	 * Establece el valor de modificado (true o false)
	 * @param modificado
	 */
	public static void setModificado(boolean modificado) {
		Gestion.modificado = modificado;
	}
	
	/**
	 * Obtiene el file
	 * @return file
	 */
	protected static File getFile() {
		return file;
	}

	/**
	 * Establece el file
	 * @param file
	 */
	protected static void setFile(File file) {
		Gestion.file = file;
	}
	
	/**
	 * Se crea un nuevo concesionario comprobando si se han hecho cambios en el actual,
	 * en ese caso pregunta al usuario si desea guardar los cambios
	 */
	/*static void nuevo() {
		if(comprobarCambios()){
			inicializar();  //Creamos un concesionario nuevo y ponemos modificado a false
			Gestion.setFile(null);	//Ponemos el file a null para indicar que el fichero es nuevo
		}
	}*/

	/**
	 * Crea un nuevo concesionario estableciendo modificado a false.
	 */
	/*private static void inicializar() {
		Gestion.setModificado(false);
		Gestion.concesionario = new Concesionario();
	}*/
	
	/**
	 * Nos permite leer un concesionario guardado en un fichero.
	 */
	public static void abrir(File file) {
		try {
			Gestion.concesionario = Fichero.leerFichero(file);
			Gestion.setFile(file); //Establecemos como fichero el indicado por el usuario
		} catch (ClassNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * Guarda en un fichero el concesionario actual. Si ya se ha establecido un nombre de fichero
	 * no se le pedirá al usuario. En caso contrario el usuario deberá dar un nombre al fichero
	 * para guardarlo
	 */
	/*static void guardar() {
		if(Gestion.getFile()==null)	//Si no hemos guardado previamente preguntamos el nombre del fichero
			guardarComo();
		else {
			try {
				Fichero.escribirFichero(Gestion.getFile(), Gestion.concesionario);
				Gestion.setModificado(false);
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}*/
	
	/**
	 * Guarda en un fichero el concesionario actual. Obligatoriamente se le pedirá al usuario un nombre
	 * de fichero para guardarlo.
	 */
	/*static void guardarComo() {
		try {
			File file = new File(Teclado.leerCadena("Introduzca el nombre del fichero: "));
			if (Fichero.existe(file)) {
				char respuesta = Character.toLowerCase(Teclado
						.leerCaracter("El fichero ya existe. ¿Desea sobreescribirlo? (s/n)"));
				if(respuesta=='n') 
					return;
			}
			Fichero.escribirFichero(file, Gestion.concesionario);
			Gestion.setModificado(false);
			Gestion.setFile(file);
		} catch (IOException e) {
			System.out.println("No se puede guardar el fichero.");
		}
	}*/
}
