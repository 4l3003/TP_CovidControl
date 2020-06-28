package ventanas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Esta clase contiene ajustes definidos para las distintas ventanaDialog de la app, todas heredan de esta clase padre.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class VentanaDialog extends JDialog {
	
	private static final long serialVersionUID = 6688822046187725899L;
	private JPanel contentPanel = new JPanel();
	/**
	 * Constructor principal, realiza algunos ajustes de swing. como sacar el borde de la ventana, ajuste de tamaño, entre otros.
	 */
	public VentanaDialog()
	{
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}
}