package ventanas;



import javax.swing.JFrame;

/**
 * esta clase inicializa una ventana de tamaño bastante grande, es la que se usa para mostrar el mapa.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class VentanaGrande extends Ventana {
	
	private static final long serialVersionUID = -9134616727776990927L;
	
	/**
	 * Constructor Principal.
	 * invoca al constructor de la clase padre.
	 * setea una posicion de la ventana y centra la misma en el medio de la pantalla
	 */
	public VentanaGrande()
	{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		
		
		
	}
}
