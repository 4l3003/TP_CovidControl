package paneles;

import java.awt.Color;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;


import clases.ListadoUsuarios;
import clases.Usuario;
import ventanas.Ventana;
import ventanas.VentanaDialogRegister;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

/**
 * Esta clase Hereda de Panel. Contiene todos los metodos y atributos relacionados al funcionamiento del panel
 * Contiene todo lo relacinado a la parte "visual" (Swing) 
 * @author Alejo Bocanegra y Martin Molina.
 *
 */
public class Panel_Login extends Panel {

	private static final long serialVersionUID = 1L;
	private Image img_logo = new ImageIcon(Panel_Login.class.getResource("/img/iconUser.png")).getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon(Panel_Login.class.getResource("/img/Iconusuario.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
	private Image img_clave = new ImageIcon(Panel_Login.class.getResource("/img/iconClave.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
	private Image img_login = new ImageIcon(Panel_Login.class.getResource("/img/iconLogin.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
	private Image img_regis= new ImageIcon(Panel_Login.class.getResource("/img/registrarIcon.png")).getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblLoginMensaje;
	private boolean loguea = false;
	private ListadoUsuarios listadoUsuarios = new ListadoUsuarios();
	private Usuario auxUsuario = new Usuario();
	
	/**
	 * Metodo Constructor. llama al constructor de la clase Padre.
	 * lee el archivo y pasa el contenido del mismo a listadoUsuarios.
	 * Llama a los principales 3 metodos de la clase.
	 */
	public Panel_Login() {
		
		super();
		listadoUsuarios = listadoUsuarios.leerArchivo();
		
		setLayout(null);
		Panel1();
		Panel2();
		Panel3();
		add(super.botonCerrar(570));
		
		
		
	}
	/**
	 * Crea un panel que contiene parte visual del login (Inputs del login).
	 */
	public void Panel1()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(170, 154, 250, 40);
		add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtUsername.getText().equals("Nombre de Usuario"))
				{
					txtUsername.setText("");
				}
				else
				{
					txtUsername.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().equals(""))
				{
					txtUsername.setText("Nombre de Usuario");
				}
			}
		});
		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUsername.setText("Nombre de Usuario");
		txtUsername.setBounds(10, 11, 170, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lbliconUsername = new JLabel("");
		lbliconUsername.setBounds(210, 2, 40, 40);
		lbliconUsername.setIcon(new ImageIcon(img_username));
		panel.add(lbliconUsername);
	}
	/**
	 * Crea un panel que contiene parte visual del login (Reemplaza la contraseña por circulos, entre otras cosas) .
	 */
	public void Panel2()
	{
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(170, 205, 250, 40);
		add(panel_1);
		panel_1.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().equals("Clave"))
				{
					txtPassword.setEchoChar('●');
					txtPassword.setText("");
				}
				else
				{
					txtPassword.selectAll();
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals(""))
				{
					txtPassword.setText("Clave");
					txtPassword.setEchoChar((char)0);
				}
			}
		});
		txtPassword.setBorder(null);
		txtPassword.setEchoChar((char)0);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPassword.setText("Clave");
		txtPassword.setBounds(10, 11, 170, 20);
		panel_1.add(txtPassword);
		
		JLabel lbliconClave = new JLabel("");
		lbliconClave.setBounds(210, 0, 40, 40);
		lbliconClave.setIcon(new ImageIcon(img_clave));
		panel_1.add(lbliconClave);
	}
	
	
	/**
	 * Crea un panel que contiene parte visual del login, llama a una funcion que realiza la validacion de los datos ingresados por el usuario.
	 * Si los mismos son correctos, se instancia un objeto de tipo ventana y al mismo se le asigna el panel menu.
	 */
	public void Panel3()
	{
		JPanel pnl_btnLogin = new JPanel();
		pnl_btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl_btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//comparaciones login
				loguea = loguear();
				if(loguea)
				{
					JOptionPane.showMessageDialog(null,"LOGIN CORRECTO!");
					Ventana.getVentanaActual().dispose();
					Ventana menu = new Ventana();
					Ventana.setVentanaActual(menu);
					
					menu.nuevoPanel(new Panel_Menu(auxUsuario.getAdmin()));
					menu.setVisible(true);
				}
				else
				{
					
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnl_btnLogin.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnl_btnLogin.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnl_btnLogin.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnl_btnLogin.setBackground(new Color(30, 60, 60));
			}
		});
		pnl_btnLogin.setBackground(new Color(47, 79, 79));
		pnl_btnLogin.setBounds(170, 265, 250, 50);
		add(pnl_btnLogin);
		pnl_btnLogin.setLayout(null);
		
		JLabel btnIngresar = new JLabel("INGRESAR");
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnIngresar.setBounds(97, 10, 107, 28);
		pnl_btnLogin.add(btnIngresar);
		
		JLabel lbliconLogin = new JLabel("");
		lbliconLogin.setBounds(56, 0, 50, 50);
		lbliconLogin.setIcon(new ImageIcon(img_login));
		pnl_btnLogin.add(lbliconLogin);
		
		super.botonCerrar(570);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(167, 43, 250, 100);
		add(lblLogo);
		lblLogo.setIcon(new ImageIcon(img_logo));
		
		lblLoginMensaje = new JLabel("");
		lblLoginMensaje.setForeground(Color.RED);
		lblLoginMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMensaje.setBounds(180, 265, 240, 14);
		add(lblLoginMensaje);
		JPanel pnl_btnRegis = new JPanel();
		pnl_btnRegis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnl_btnRegis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VentanaDialogRegister venRegis = new VentanaDialogRegister(listadoUsuarios);
				venRegis.setLocationRelativeTo(null);
				venRegis.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnl_btnRegis.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnl_btnRegis.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnl_btnRegis.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnl_btnRegis.setBackground(new Color(30, 60, 60));
			}
			
		});
		pnl_btnRegis.setLayout(null);
		pnl_btnRegis.setBackground(new Color(47, 79, 79));
		pnl_btnRegis.setBounds(170, 326, 252, 50);
		add(pnl_btnRegis);
		
		JLabel lblRegistrar = new JLabel("REGISTRAR");
		lblRegistrar.setForeground(Color.WHITE);
		lblRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegistrar.setBounds(95, 11, 107, 28);
		pnl_btnRegis.add(lblRegistrar);
		
		JLabel lbliconRegis = new JLabel("");
		lbliconRegis.setIcon(new ImageIcon(img_regis));
		lbliconRegis.setBounds(60, 0, 47, 50);
		pnl_btnRegis.add(lbliconRegis);
		
	}
	/**
	 * Inicializa un Usuario. Verifica si los datos ingresados por el usuario se encuentran registrados en el sistema.
	 * @return boolean
	 */
	public boolean loguear()
	{
		    auxUsuario= new Usuario();
		
			auxUsuario=  listadoUsuarios.buscarUnUsuario(txtUsername.getText());
			if(auxUsuario!=null)
			{
				if(txtPassword.getText().equals(auxUsuario.getClave())){
					
					lblLoginMensaje.setText("");
					listadoUsuarios.escribir(listadoUsuarios);
					loguea = true;
				}
				else {
					JOptionPane.showMessageDialog(null,"CONTRASEÑA INCORRECTA");
					loguea = false;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"NO EXISTE ESE NOMBRE DE USUARIO.");
				loguea = false;
			}		

		return loguea;
	}
}




