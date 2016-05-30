package concesionarioFuncionalidad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase Fichero. Contiene los métodos necesarios para la gestión de ficheros del concesionario.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class Fichero {
	/**
	 * Abre un fichero y devuelve el concesionario almacenado dentro de éste.
	 * @param file
	 * @return concesionario
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Concesionario leerFichero(File file) throws ClassNotFoundException, IOException{
		file = comprobarExtension(file);
		try(ObjectInputStream in=new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			return (Concesionario) in.readObject();
		}
	}
	
	/**
	 * Guarda el concesionario en un fichero
	 * @param file
	 * @param concesionario
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void escribirFichero(File file, Concesionario concesionario) throws FileNotFoundException, IOException{
		file=comprobarExtension(file);
		try(ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(concesionario);
		}
	}
	
	/**
	 * Comprueba si el fichero existe
	 * @param file
	 * @return true si existe y false si no
	 */
	public static boolean existe(File file) {
		file=comprobarExtension(file);
		return file.exists();
	}
	
	

	/**
	 * Comprueba que la extensión sea válida
	 * @param file
	 * @return file
	 */
	static File comprobarExtension(File file) {
		String path=file.getPath();
	    if(!path.endsWith(".obj"))
	        return new File(path + ".obj");
	    return file;
	}
}
