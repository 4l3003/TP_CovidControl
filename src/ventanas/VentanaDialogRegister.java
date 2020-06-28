package ventanas;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import clases.ListadoUsuarios;
import clases.Usuario;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Esta clase contiene todos los ajustes y el funcionamiento de la ventana emergente que se abre al clickear en el boton Registrar del Login.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class VentanaDialogRegister extends VentanaDialog{
	
	
	private JPasswordField txtContrasea;
	private JTextField txtNombreDeUsuario;
	private ListadoUsuarios listadoUsuarios;
	private Usuario auxUsuario;
	
	
	/**
	 * Constructor principal, recibe el listado de usuarios, lo iguala al de la clase y contiene todos los estilos 
	 * y el funcionamiento de la ventana.
	 * @param listadoUsuarios
	 */
	public VentanaDialogRegister(ListadoUsuarios listadoUsuarios) {
		getContentPane().setLayout(null);
		
		this.listadoUsuarios = listadoUsuarios;
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 444, 271);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtContrasea = new JPasswordField();
		txtContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrasea.setBounds(183, 114, 113, 23);
		panel.add(txtContrasea);
		txtContrasea.setColumns(10);
		
		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreDeUsuario.setBounds(183, 73, 113, 30);
		panel.add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Registrar");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					auxUsuario =  listadoUsuarios.buscarUnUsuario(txtNombreDeUsuario.getText());
					
					
					if(auxUsuario==null){
					auxUsuario= new Usuario();
					auxUsuario.setAdmin(false);
					auxUsuario.setNombreU(txtNombreDeUsuario.getText());
					auxUsuario.setClave(txtContrasea.getText());
					listadoUsuarios.agregarUsuario(auxUsuario);
					JOptionPane.showMessageDialog(null,"USUARIO REGISTRADO");
					dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"ERROR! ESE NOMBRE DE USUARIO YA EXISTE");
					}
			}
		});
		btnNewButton.setBounds(172, 205, 124, 30);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(134, 11, 196, 37);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de Usuario:");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_1.setBounds(54, 76, 128, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Clave:");
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(119, 113, 79, 23);
		panel.add(lblNewLabel_1_1);
	}
}
