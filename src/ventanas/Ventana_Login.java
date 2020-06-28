package ventanas;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import paneles.Panel_Login;

import java.awt.Color;


/**
 * Esta clase contiene todos los estilos que se muestran en la primera ventana que figura al abrir la app.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class Ventana_Login extends Ventana {

	private static final long serialVersionUID = 3520837245418716210L;
	private JPanel contentPane;

	
	/**
	 * Constructor, inicializa la ventana con algunos estilos.
	 */
	public Ventana_Login() {
		super();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 139, 139));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Ventana.setVentanaActual(this);
		Panel_Login panel = new Panel_Login();
		panel.setBackground(new Color(0, 139, 139));
		contentPane.add(panel, BorderLayout.CENTER);
		
		setVisible(true);
		
	}
	
	

}
