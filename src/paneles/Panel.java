package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.teamdev.jxmaps.LatLng;

import ventanas.Ventana;
import ventanas.VentanaGrande;
/**
 * Todos los paneles heredan de esta clase, contiene metodos muy utiles para que puedan ser utilizados desde clases hijas.
 * @author Alejo Bocanegra y Martin Molina.
 *
 */
public class Panel extends JPanel {


	private static final long serialVersionUID = 1;
	
	private JLabel Otitulo;
	private Image Oimg;
	private String tituloPrincipal;
	private String imagenFondo;
	/**
	 * Constructor. hace visible nuestro panel
	 */
	public Panel()
	{
		setVisible(true);
	}
	/**
	 * Constructor que iguala los parametros recibidos a los de la clase.
	 * @param tituloPrincipal
	 * @param imagenFondo
	 */
	public Panel(String tituloPrincipal, String imagenFondo)
	{
		this.tituloPrincipal = tituloPrincipal;
		this.imagenFondo = imagenFondo;
	}
	/**
	 * Recibe un titulo y lo inserta en la ventana.
	 * @param titulo
	 */
	public void setTituloPrincipal(String titulo)
	{
		Otitulo = new JLabel(titulo);
		Otitulo.setBounds(450, 100, 459, 69);
		//Otitulo.setHorizontalAlignment(JLabel.CENTER);
		Otitulo.setForeground(Color.white);
		Otitulo.setBackground(Color.black);
		Otitulo.setOpaque(true);
		Otitulo.setFont(new Font("NewsGoth BT", Font.BOLD, 40));
		add(Otitulo);
	}
	
	/**
	 * Nos coloca una imagen de fondo en el panel
	 * @param urlImgFondo
	 */
	public void setImagenFondo(String url)
	{
		 Oimg = new ImageIcon(getClass().getResource(url)).getImage();
	}
	/**
	 * nos coloca en el panel el boton de cierre, le debemos pasar la coordenada X para poder adaptar este metodo a distintos tamaños
	 * de ventana.
	 * @param x
	 * @return JLabel
	 */
	public JLabel botonCerrar(int x)
	{
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres cerrar?","confirmar",JOptionPane.YES_NO_OPTION) == 0)
				{
					Ventana.getVentanaActual().dispose();
					if(Ventana.getVentanaActual() instanceof VentanaGrande)
					{
						Ventana.setVentanaActual(Ventana.getVanterior());
					}
					
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(x, 0, 20, 20);
		
		return lblX;
	}
	
	/**
	 * Soobrescribe el metodo Paint para podes dibujar nuestra imagen de fondo en el panel.
	 * @param g
	 */
	@Override
    public void paint(Graphics g){
        g.drawImage(Oimg, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
	
	
	
	
	
	
}
