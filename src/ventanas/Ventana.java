package ventanas;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import paneles.Panel_Mapa;

/**
 * Esta clase contiene toda la configuracion de una Ventana. 
 * Contiene metodos muy utiles como por ejemplo, cambiar de panel en la misma ventana
 * @author Alejo Bocanegra y Martin Molina.
 *
 */
public class Ventana extends JFrame {
	
	private static Ventana ventanaActual;
	private static final long serialVersionUID = 8235182985586193141L;
	private JPanel contentPane;
	private static Ventana Vanterior;
	
	/**
	 * Constructor, contiene algunos ajustes de swing
	 */
	public Ventana()
	{
		
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 420);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	/**
	 * nos coloca el panel recibido por parametro en la ventana
	 * @param panelActual
	 */
	public void nuevoPanel(JPanel panelActual)
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(panelActual);
		this.getContentPane().repaint();
		this.getContentPane().revalidate();
	}
	/**
	 * nos coloca el panelMapa recibido por parametro en la ventana
	 * @param panelActual
	 */
	public void nuevoPanel(Panel_Mapa panelActual)
	{
		
		this.getContentPane().removeAll();
		this.getContentPane().add(panelActual);
		this.getContentPane().repaint();
		this.getContentPane().revalidate();
	}

	public static Ventana getVentanaActual() {
		return ventanaActual;
	}

	public static void setVentanaActual(Ventana ventanaActual) {
		Ventana.ventanaActual = ventanaActual;
	}
	
	public  static Ventana getVanterior() {
		return Vanterior;
	}
	public static void setVanterior(Ventana vanterior) {
		Vanterior = vanterior;
	}
	
	
	
}
