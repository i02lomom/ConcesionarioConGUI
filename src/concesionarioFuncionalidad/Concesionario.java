package concesionarioFuncionalidad;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * No pueden existir dos coches con la misma matr�cula en el almac�n del concesinario
 * no puede a�adirse un coche al concecionario con alguno de sus atributos inv�lidos. Han de conocerse todas sus caracter�sticas 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * Representa un concesionario de coches.
 * 
 * L�gicamente, no podr� a�adirse un coche inv�lido almac�n del concesinario)
 * 
 * Han de conocerse todas sus caracter�sticas Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Miguel Angel L�pez Moyano
 * @version 1.0
 */
public class Concesionario implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Almac�n de los coches del concesionario
	 */
	public ArrayList<Coche> almacen = new ArrayList<Coche>();
	/**
	 * Nombre del concesionario
	 */
	private final String nombre = "IES Gran Capit�n";
	
	public boolean modificado=false;
	
	
	public ArrayList<Coche> getAlmacen() {
		return almacen;
	}
	
	public boolean estaModificado() {
		return modificado;
	}

	/**
	 * Establece el valor de modificado (true o false)
	 * @param modificado
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	// P:Por qu� no se necesita que annadir devuelva boolean??????
	// P:Por qu� no se especifican todas las excepciones de forma
	// expl�cita??????
	/**
	 * A�ade un coche al concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a a�adir
	 * @param color
	 *            Color del coche a a�adir
	 * @param modelo
	 *            Modelo del coche a a�adir
	 * @throws Exception
	 *             Si no se ha podido a�adir el coche al concesionario, porque
	 *             ya hay otro con la misma matr�cula o porque faltan datos
	 */
	public void annadir(String matricula, Color color, Modelo modelo) throws Exception {
		// Coche coche = Coche.instanciarCoche(matricula, color, modelo);

		// if (coche == null || almacen.contains(coche))
		// return false;
		// return almacen.add(coche);
		Coche coche = new Coche(matricula, color, modelo);
		if (!almacen.contains(coche))
			almacen.add(coche);
		else
			throw new CocheYaExisteException(
					"El coche ya existe en el concesionario. ");
	}

	/**
	 * Elimina un coche del concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a eliminar
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida en su formato
	 * @return true si se ha eliminado. false en otro caso
	 */
	public boolean eliminar(String matricula) throws MatriculaNoValidaException {
		return almacen.remove(new Coche(matricula));
	}

	/**
	 * Devuelve el n�mero de coches en el almac�n del concesionario
	 * 
	 * @return N�mero de coches en el almac�n del concesionario
	 */
	int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almac�n indicado por la matr�cula
	 * 
	 * @param matricula
	 *            Matr�cula a buscar
	 * @return Coche contenido en el almac�n.
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida
	 */
	public Coche get(String matricula) throws MatriculaNoValidaException,
			CocheNoExisteException {
	
		try {
			return almacen.get(almacen.indexOf(new Coche(matricula)));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CocheNoExisteException(
					"El coche no est� en el concesionario.");
		}

		// }
		// return null;
	}
	
	/**
	 * Modifica el coche que tiene la misma matr�cula que el coche ya modificado (pasado por
	 * par�metro). B�sicamente lo sustituye.
	 * @param coche
	 */
	public void modificar(Coche coche){
		almacen.set(almacen.indexOf(coche), coche);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

}
