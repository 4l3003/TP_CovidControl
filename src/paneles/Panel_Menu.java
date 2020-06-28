package paneles;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ventanas.Ventana;
import ventanas.VentanaDialogEditar;
import ventanas.VentanaDialogEliminar;

import ventanas.VentanaGrande;


import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.border.LineBorder;

import clases.ExportarJson;
import clases.ListadoPersonas;
import clases.ListadoUsuarios;
import clases.Persona;
import clases.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.UIManager;
import java.awt.Insets;
/**
 * Esta clase contiene todos los metodos relacionados a la gestion del menu principal de la aplicacion, el funcionamiento de los botones,
 * gestion de las coleccion principal de personas.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class Panel_Menu extends Panel {
	
	private static final long serialVersionUID = -5188408969854418197L;
	private Image img_menu1 = new ImageIcon(Panel_Login.class.getResource("/img/iconVerMapa.png")).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
	private Image img_menu2 = new ImageIcon(Panel_Login.class.getResource("/img/iconMarcador.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
	private Image img_menu3 = new ImageIcon(Panel_Login.class.getResource("/img/iconEditarPunto.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
	private Image img_menu4 = new ImageIcon(Panel_Login.class.getResource("/img/iconBorrarPunto.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
	private ListadoPersonas listadoPerso = new ListadoPersonas();
	private ExportarJson json;
	private ListadoUsuarios listadoUsuarios = new ListadoUsuarios();
	private boolean superAdmin = false;
	/**
	 * Constructor Principal. llama al constructor de su clase padre, iguala el listado de personas al contenido del archivo y exporta un json
	 * con lo que contiene la coleccion. Inicia los componentes del menu.
	 */
	
	public Panel_Menu(boolean superAdmin) {
		super();
		
		this.superAdmin = superAdmin;
		setLayout(null);
		SalirYguardar();
		listadoPerso = listadoPerso.leerArchivo();
		json = new ExportarJson(listadoPerso);
		listadoUsuarios= listadoUsuarios.leerArchivo();
		iniciarComponentes();
		
		
	}
	/**
	 * Contiene toda la interfaz grafica y la distribucion de los distintos paneles de la ventana.
	 */
	public void iniciarComponentes()
	{
		
		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("ver mapa");
				 Ventana.setVanterior(Ventana.getVentanaActual());
			     Ventana mapa = new VentanaGrande();
			     Ventana.setVentanaActual(mapa);
		         Panel_Mapa Pmapa = new Panel_Mapa(listadoPerso, false);
		         
		         mapa.nuevoPanel(Pmapa);
		         
		         
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(new Color(169, 169, 169));
			}
		});
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 6));
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(119, 79, 173, 142);
		add(panel);
		panel.setLayout(null);
		//VER MAPA
		JLabel lblverMapa = new JLabel("VER MAPA");
		lblverMapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblverMapa.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblverMapa.setBounds(0, 94, 173, 48);
		panel.add(lblverMapa);
		
		
			JLabel lblimg1 = new JLabel("");
			lblimg1.setHorizontalAlignment(SwingConstants.CENTER);
			lblimg1.setBounds(0, 11, 173, 93);
			lblimg1.setIcon(new ImageIcon(img_menu1));
			panel.add(lblimg1);
			
		if(superAdmin)
		{
			JPanel panel_1 = new JPanel();
			panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.out.println("agregar");
					Ventana agregarMarcador = new VentanaGrande();
					Ventana.setVanterior(Ventana.getVentanaActual());
					Ventana.setVentanaActual(agregarMarcador);
					Panel_Mapa map = new Panel_Mapa(listadoPerso, true);
					agregarMarcador.nuevoPanel(map);
					map.agregar();	
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					panel_1.setBackground(new Color(47, 79, 79));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					panel_1.setBackground(new Color(169, 169, 169));
				}
			});
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 6));
			panel_1.setBackground(new Color(169, 169, 169));
			panel_1.setBounds(317, 79, 173, 142);
			add(panel_1);
			panel_1.setLayout(null);
			
				JLabel lblAddPunto = new JLabel("");
				lblAddPunto.setHorizontalAlignment(SwingConstants.CENTER);
				lblAddPunto.setHorizontalTextPosition(SwingConstants.CENTER);
				lblAddPunto.setBounds(0, 11, 173, 94);
				lblAddPunto.setIcon(new ImageIcon(img_menu2));
				panel_1.add(lblAddPunto);
				//AGREGAR
				JLabel lblAgregarSospechoso = new JLabel("AGREGAR");
				lblAgregarSospechoso.setHorizontalAlignment(SwingConstants.CENTER);
				lblAgregarSospechoso.setFont(new Font("Arial Black", Font.BOLD, 18));
				lblAgregarSospechoso.setBounds(0, 94, 173, 48);
				panel_1.add(lblAgregarSospechoso);
				
				JPanel panel_2 = new JPanel();
				panel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
					System.out.println("listadoperso:"+listadoPerso.listarPersonas());
				    VentanaDialogEditar Veditar = new VentanaDialogEditar(listadoPerso);
				    Veditar.setLocationRelativeTo(null);
					Veditar.setVisible(true);
					
						
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
						panel_2.setBackground(new Color(47, 79, 79));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panel_2.setBackground(new Color(169, 169, 169));
					}
				});
				panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 6));
				panel_2.setBackground(new Color(169, 169, 169));
				panel_2.setBounds(119, 232, 173, 142);
				add(panel_2);
				panel_2.setLayout(null);
				
				
				//EDITAR
				JLabel lblEditar = new JLabel("EDITAR");
				lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
				lblEditar.setFont(new Font("Arial Black", Font.BOLD, 18));
				lblEditar.setBounds(0, 94, 173, 48);
				
				panel_2.add(lblEditar);
				
				JLabel lblimgEditar = new JLabel("");
				lblimgEditar.setHorizontalAlignment(SwingConstants.CENTER);
				lblimgEditar.setBounds(10, 11, 153, 85);
				lblimgEditar.setIcon(new ImageIcon(img_menu3));
				panel_2.add(lblimgEditar);
				
				JPanel panel_3 = new JPanel();
				panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				panel_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						 
						VentanaDialogEliminar Veliminar = new VentanaDialogEliminar(listadoPerso, listadoUsuarios, false);
						Veliminar.setLocationRelativeTo(null);
						Veliminar.setVisible(true); 
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
						panel_3.setBackground(new Color(47, 79, 79));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						panel_3.setBackground(new Color(169, 169, 169));
					}
				});
				panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
				panel_3.setBackground(new Color(169, 169, 169));
				panel_3.setBounds(317, 232, 173, 142);
				add(panel_3);
				panel_3.setLayout(null);
				
				
				//ELIMINAR
				JLabel lblEliminar = new JLabel("ELIMINAR");
				lblEliminar.setBounds(0, 97, 173, 45);
				lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
				lblEliminar.setFont(new Font("Arial Black", Font.BOLD, 18));
				panel_3.add(lblEliminar);
				
				JLabel lblimgEliminar = new JLabel("");
				lblimgEliminar.setHorizontalAlignment(SwingConstants.CENTER);
				lblimgEliminar.setBounds(0, 22, 173, 80);
				lblimgEliminar.setIcon(new ImageIcon(img_menu4));
				panel_3.add(lblimgEliminar);
			
				
			
			
			

			JButton btnElimUser = new JButton("ELIM USER");
			btnElimUser.setBorder(UIManager.getBorder("Button.border"));
			btnElimUser.setIgnoreRepaint(true);
			btnElimUser.setBackground(Color.GRAY);
			btnElimUser.setMargin(new Insets(0, 0, 0, 0));
			btnElimUser.setFocusable(false);
			btnElimUser.setFocusTraversalKeysEnabled(false);
			btnElimUser.setFocusPainted(false);
			btnElimUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnElimUser.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnElimUser.setBackground(Color.LIGHT_GRAY);
			btnElimUser.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnElimUser.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					VentanaDialogEliminar elimUser = new VentanaDialogEliminar(listadoPerso, listadoUsuarios, true);
					elimUser.setLocationRelativeTo(null);
					elimUser.setVisible(true); 	
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnElimUser.setBackground(new Color(47, 79, 79));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnElimUser.setBackground(new Color(169, 169, 169));
				}
				
			});
			btnElimUser.setBounds(248, 379, 109, 27);
			add(btnElimUser);
		}
		
		JLabel lblNewLabel = new JLabel("PANEL DE GESTION DE COVID-CONTROL");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 22));
		lblNewLabel.setBounds(102, 26, 648, 42);
		add(lblNewLabel);
}
		
		

	/**
	 * recibe una persona y la agrega a la coleccion.
	 * @param persona
	 */
	public void agregarUnaPersona(Persona persona)
	{
		listadoPerso.agregarPersona(persona);
	}
	public ListadoPersonas getListadoPerso() {
		return listadoPerso;
	}

	public void setListadoPerso(ListadoPersonas listadoPerso) {
		this.listadoPerso = listadoPerso;
	}
	/**
	 * nos muestra el boton cerrar de la ventana, y realiza la accion de guardar en el archivo el listado al cerrar el programa.
	 */
	public void SalirYguardar()
	{
		JLabel label = super.botonCerrar(570);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listadoPerso.escribir(listadoPerso);
			}
		});
		add(label);
	}
}
