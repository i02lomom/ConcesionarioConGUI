package concesionarioGUI;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import concesionarioFuncionalidad.Concesionario;
import concesionarioFuncionalidad.Fichero;

/**
 * Menú principal de nuestro programa.
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class Principal {

	private JFrame frame;
	private File file;
	private Concesionario concesionario = new Concesionario();
	private JFileChooser fileChooser = new JFileChooser();

	private Alta alta;
	private Baja baja;
	private Modificar modificar;
	private MostrarConcesionario mostrarConcesionario;
	private MostrarPorMatricula mostrarPorMatricula;
	private AcercaDe acercaDe;

	/**
	 * Lanza la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicación
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Inicializa los contenidos del frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Sin título: Concesionario IES Gran Capitan");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('a');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprobarCambios();
				file=obtenerFichero();
				if(file.exists()&&file!=null){
					abrir(file);
					frame.setTitle(file.getName()+": Concesionario IES Gran Capitan");
				}
				else
					JOptionPane.showMessageDialog(frame,
						    "No existe el fichero",
						    "Fichero no encontrado",
						    JOptionPane.PLAIN_MESSAGE);
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(concesionario.estaModificado())
					guardar();
				System.exit(0);
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);

		JMenu mnCoche = new JMenu("Coche");
		mnCoche.setMnemonic('c');
		menuBar.add(mnCoche);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta();
			}
		});
		mnCoche.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baja();
			}
		});
		mnCoche.add(mntmBaja);

		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar Concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarConcesionario();
			}
		});

		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		mnCoche.add(mntmModificar);
		mnCoche.add(mntmMostrarConcesionario);

		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('b');
		menuBar.add(mnBuscar);

		JMenuItem mntmBuscarPorMatricula = new JMenuItem("Buscar por matricula");
		mntmBuscarPorMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPorMatricula();
			}
		});
		mnBuscar.add(mntmBuscarPorMatricula);

		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('y');
		menuBar.add(mnAyuda);

		JMenuItem mntmVerLaAyuda = new JMenuItem("Ver la ayuda");
		mntmVerLaAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            File archivo = new File ("C:\\Users\\d15lomom\\workspace\\ConcesionarioGui2\\doc\\index.html");
		            Desktop.getDesktop().open(archivo);
				}catch (IOException e1) {
		    	 e1.getStackTrace();
			}
		}});
		mnAyuda.add(mntmVerLaAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acercaDe= new AcercaDe();
				acercaDe.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);
	}
	
	/**
	 * Crea una nueva ventana Alta y la pone visible
	 */
	private void alta() {
		alta = new Alta(concesionario);
		alta.setVisible(true);
	}
	
	/**
	 * Crea una nueva ventana Baja y la pone visible
	 */
	private void baja() {
		baja = new Baja(concesionario);
		baja.setVisible(true);
	}
	
	/**
	 * Crea una nueva ventana Modificar y la pone visible
	 */
	private void modificar() {
		modificar = new Modificar(concesionario);
		modificar.setVisible(true);
	}
	
	/**
	 * Crea una nueva ventana MostrarPorMatricula y la pone visible
	 */
	private void mostrarPorMatricula(){
		mostrarPorMatricula=new MostrarPorMatricula(concesionario);
		mostrarPorMatricula.setVisible(true);
	}
	/**
	 * Crea una nueva ventana MostrarConcesionario y la pone visible
	 */
	private void mostrarConcesionario(){
		mostrarConcesionario=new MostrarConcesionario(concesionario);
		mostrarConcesionario.setVisible(true);
	}

	/**
	 * Devuelve el fichero seleccionado en el filechooser
	 * @return file seleccionado en el filechooser
	 */
	private File obtenerFichero(){
		FileFilter filter = new FileNameExtensionFilter("Archivos de objeto (*.obj)", "obj");
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return fileChooser.getSelectedFile();
		return null;
	}
	
	/**
	 * Si se ha modificado el concesionario pregunta al usuario si desea guardar los cambios
	 * @return true si se guardan y false si no.
	 */
	private boolean comprobarCambios() {
		if (concesionario.estaModificado()) {
			if (JOptionPane.showOptionDialog(frame, "¿Desea guardar los cambios?", "Confirmar",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
					null) == JOptionPane.YES_OPTION)
				guardar();
				return true;
		}
		return false;
	}
	
	/**
	 * Comprueba si se ha modificado el concesionario, se guarda si así lo desea el usuario y crea un
	 * nuevo concesionario (vacío)
	 */
	void nuevo() {
		if(comprobarCambios()){
			inicializar();  //Creamos un concesionario nuevo y ponemos modificado a false
			file=null;	//Ponemos el file a null para indicar que el fichero es nuevo
		}
		frame.setTitle("Sin título: Concesionario IES Gran Capitan");
	}

	/**
	 * Crea un nuevo concesionario estableciendo modificado a false.
	 */
	private void inicializar() {
		concesionario.setModificado(false);
		concesionario = new Concesionario();
	}

	/**
	 * Nos permite leer un concesionario guardado en un fichero.
	 */
	public void abrir(File file) {
	try {
		concesionario = Fichero.leerFichero(file);
		JOptionPane.showMessageDialog(frame,
			    "Fichero cargado con éxito",
			    "Fichero cargado",
			    JOptionPane.PLAIN_MESSAGE);
	} catch (ClassNotFoundException e) {
		JOptionPane.showMessageDialog(frame,
			    "No existe el fichero",
			    "Fichero no encontrado",
			    JOptionPane.PLAIN_MESSAGE);
	} catch (IOException e) {
		e.getStackTrace();
	}
}

	/**
	 * Guarda en un fichero el concesionario actual. Si ya se ha establecido un nombre de fichero
	 * no se le pedirá al usuario. En caso contrario el usuario deberá dar un nombre al fichero
	 * para guardarlo
	 */
	void guardar() {
		if(file==null)	//Si no hemos guardado previamente preguntamos el nombre del fichero
			guardarComo();
		else {
			try {
				Fichero.escribirFichero(file, concesionario);
				concesionario.setModificado(false);
				JOptionPane.showMessageDialog(frame,
					    "Fichero guardado con éxito",
					    "Fichero guardado",
					    JOptionPane.PLAIN_MESSAGE);
				frame.setTitle(file.getName()+": Concesionario IES Gran Capitan");
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	/**
	 * Guarda en un fichero el concesionario actual. Obligatoriamente se le pedirá al usuario un nombre
	 * de fichero para guardarlo.
	 */
	void guardarComo() {
		try {
			file = obtenerFichero();
			if (Fichero.existe(file)) {
				switch(JOptionPane.showOptionDialog(frame,
						"¿Está seguro de que desea sobreescribirlo?", "Confirmar",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null)){
				case JOptionPane.NO_OPTION:
					return;
				}
			}
			Fichero.escribirFichero(file, concesionario);
			concesionario.setModificado(false);
			JOptionPane.showMessageDialog(frame,
				    "Fichero guardado con éxito",
				    "Fichero guardado",
				    JOptionPane.PLAIN_MESSAGE);
			frame.setTitle(file.getName()+": Concesionario IES Gran Capitan");
		} catch (IOException e) {
			System.out.println("No se puede guardar el fichero.");
		}
	}
}
