package ventanas;


import javax.swing.JButton;

import javax.swing.JPanel;


import clases.ListadoPersonas;
import clases.ListadoUsuarios;
import clases.Persona;
import clases.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Esta clase contiene todos los ajustes y el funcionamiento de la ventana emergente que se abre al clickear en el boton Eliminar del menu.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class VentanaDialogEliminar extends VentanaDialog {
	
	private static final long serialVersionUID = 1195896028119176833L;
	private JTextField inputDni;
	private Image random = new ImageIcon(VentanaDialogEliminar.class.getResource("/img/imgrandom.png")).getImage().getScaledInstance(130,130,Image.SCALE_SMOOTH);
	private ListadoPersonas listado;
	private Persona auxPersona;
	private ListadoUsuarios listadoUsuarios = new ListadoUsuarios();
	private Usuario auxUsuario = new Usuario();
	private boolean accion = false;
	private JLabel lblNewLabel;
	/**
	 * Constructor Principal.
	 * llama al constructor de la clase padre.
	 * Setea un tamaño especial para esta ventana
	 * iguala el listado recibido por parametro al listado de esta ventana
	 * setea el titulo de la ventana y llama al metodo principal que inicia todos los componentes de la ventana.
	 * @param listado
	 */
	public VentanaDialogEliminar(ListadoPersonas listado, ListadoUsuarios listadoUsuarios, boolean accion)
	{
		super();
		super.setSize(300,250);
		this.accion = accion;
		this.listado = listado;
		this.listadoUsuarios = listadoUsuarios;
		setTitle("ELIMINAR UNA PERSONA");
		getContentPane().setLayout(null);
		iniciarComponentes();


	}
	
	/**
	 * Contiene todos el estilo visual de la pantalla, y llama a distintos metodos que validan y realizan acciones sobre los
	 * datos ingresados por el usuario
	 */
	public void iniciarComponentes()
	{
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		if(accion==false)
		{
			lblNewLabel= new JLabel("Ingrese DNI de la persona a eliminar:");
		}
		else
		{
			lblNewLabel= new JLabel("Ingrese Nombre del usuario a eliminar:");
		}
		
		lblNewLabel.setFont(new Font("Microsoft Tai Le", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 21, 251, 26);
		panel.add(lblNewLabel);
		
		inputDni = new JTextField();
		inputDni.setBounds(80, 58, 135, 26);
		panel.add(inputDni);
		inputDni.setColumns(10);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!accion)
				{
					eliminarPorDni();
				}
				else
				{
					eliminarUsuario();
				}
				
			}
		});
		btnNewButton.setBounds(34, 157, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(random));
		lblNewLabel_1.setBounds(153, 88, 153, 130);
		panel.add(lblNewLabel_1);
	}
	/**
	 * Busca la persona segun el dni y si existe, procede a eliminar la misma
	 */
	public void eliminarPorDni()
	{
		auxPersona = listado.buscarUnaPersona(inputDni.getText());
		if(auxPersona!=null)
		{
			listado.borrarPersona(auxPersona.getDni());
			JOptionPane.showMessageDialog(null,"FELICITACIONES! LA PERSONA SE HA ELIMINADO CORRECTAMENTE!");
			dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"ERROR! NO SE ENCUENTRA UNA PERSONA CON ESE DNI.");
		}
	}
	/**
	 * Busca un usuario segun su nombre y si existe, procede a eliminar el misma
	 */
	public void eliminarUsuario() {
		
		auxUsuario = listadoUsuarios.buscarUnUsuario(inputDni.getText());
		if(auxUsuario!=null && !auxUsuario.getAdmin())
		{
			listadoUsuarios.borrarUsuario(auxUsuario.getNombreU());
			listadoUsuarios.escribir(listadoUsuarios);
			JOptionPane.showMessageDialog(null,"USUARIO ELIMINADO EXITOSAMENTE!");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"NO EXISTE ESE USUARIO");
		}
				
	}
}
