package concesionarioGUI;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Clase AcercaDe. Nos da información acerca del programa
 * 
 * @author Miguel Angel López Moyano
 * @version 1.0
 */
public class AcercaDe extends JDialog {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de AcercaDe
	 */
	AcercaDe(){
		setResizable(false);
		setModal(true);
		setTitle("Acerca de Concesionario");
		setBounds(100, 100, 434, 300);
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnOk.setBounds(157, 232, 119, 29);
		getContentPane().add(btnOk);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AcercaDe.class.getResource("/imagenes/AcercaDe.jpg")));
		lblNewLabel.setBounds(0, 0, 434, 232);
		getContentPane().add(lblNewLabel);
		
	}
}
