package ventanas;



import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JPanel;


import clases.ListadoPersonas;
import clases.Persona;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

/**
 * Esta clase contiene todos los ajustes y el funcionamiento de la ventana emergente que se abre al clickear en el boton editar del menu.
 * @author Alejo Bocanegra y Martin Molina
 */
public class VentanaDialogEditar extends VentanaDialog {

	
	private static final long serialVersionUID = 932556896353154572L;
	private JTextField inputDni;
	private JTextField nombreUser;
	private JPanel panel_1;
	private ListadoPersonas listado;
	private Persona auxPersona;
	private JTextField inputApellido;
	private JRadioButton rdbtnEnTesteo;
	private JRadioButton rdbtnPositivo;
	private JRadioButton rdbtnNegativo;
	private boolean flag;
	
	/**
	 * Constructor Principal.
	 * llama al constructor de la clase padre.
	 * iguala el listado recibido por parametro al listado de esta ventana
	 * setea el titulo de la ventana y llama al metodo principal que inicia todos los componentes de la ventana.
	 * @param listado
	 */
	public VentanaDialogEditar(ListadoPersonas listado) {
		super();
		this.listado = listado;
		setTitle("MODIFICACION DE PERSONAS");
		
		iniciarComp();
		
		
	}
	/**
	 * Contiene todos el estilo visual de la pantalla, asi como tambien lee y compara los campos ingresados por el usuario.
	 */
	public void iniciarComp()
	{
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 434, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Ingrese DNI de la persona a buscar:");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 11, 252, 47);
		panel.add(lblNewLabel);
		
		inputDni = new JTextField();
		inputDni.setBackground(new Color(0, 128, 128));
		inputDni.setBounds(286, 22, 117, 29);
		panel.add(inputDni);
		inputDni.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flag = validar_Dni();
				
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBounds(158, 59, 92, 23);
		panel.add(btnBuscar);
		
		panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 139, 139));
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(23, 107, 385, 105);
		panel.add(panel_1);
		panel_1.setVisible(false);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
		lblNombre.setBounds(10, 26, 79, 29);
		panel_1.add(lblNombre);
		
		nombreUser = new JTextField();
		nombreUser.setBackground(new Color(0, 139, 139));
		nombreUser.setColumns(10);
		nombreUser.setBounds(65, 31, 96, 20);
		panel_1.add(nombreUser);
		
		rdbtnEnTesteo = new JRadioButton("En testeo");
		rdbtnEnTesteo.setBackground(new Color(0, 128, 128));
		rdbtnEnTesteo.setBounds(10, 75, 109, 23);
		panel_1.add(rdbtnEnTesteo);
		
		 rdbtnPositivo = new JRadioButton("Positivo");
		rdbtnPositivo.setBackground(new Color(0, 128, 128));
		rdbtnPositivo.setBounds(136, 75, 109, 23);
		panel_1.add(rdbtnPositivo);
		
		rdbtnNegativo = new JRadioButton("Negativo");
		rdbtnNegativo.setBackground(new Color(0, 128, 128));
		rdbtnNegativo.setBounds(247, 75, 96, 23);
		panel_1.add(rdbtnNegativo);
		ButtonGroup grupoestados = new ButtonGroup();
		grupoestados.add(rdbtnEnTesteo);
		grupoestados.add(rdbtnPositivo);
		grupoestados.add(rdbtnNegativo);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(176, 26, 98, 29);
		panel_1.add(lblNewLabel_1_1);
		
		inputApellido = new JTextField();
		inputApellido.setColumns(10);
		inputApellido.setBackground(new Color(0, 139, 139));
		inputApellido.setBounds(239, 31, 96, 20);
		panel_1.add(inputApellido);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// BOTON GUARDAR
				guardarEdicion();
				dispose();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(319, 220, 92, 23);
		panel.add(btnGuardar);
	}
	
	/**
	 * Verifica si existe el dni de la persona, para poder editarlo.
	 * @return boolean
	 */
	public boolean validar_Dni()
	{
		
		auxPersona =  listado.buscarUnaPersona(inputDni.getText());
		if(auxPersona!=null)
		{
			panel_1.setVisible(true);
			nombreUser.setText(auxPersona.getNombre());
			inputApellido.setText(auxPersona.getApellido());
			validarRadioButton();
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"ERROR! NO SE ENCUENTRA UNA PERSONA CON ESE DNI.");
			return false;
		}
		
		
	}
	/**
	 * Selecciona el RadioButton, segun el estado de la persona
	 */
	public void validarRadioButton()
	{
		if(auxPersona.getEstado()=="En testeo")
		{
			rdbtnEnTesteo.setSelected(true);
		
		}else if(auxPersona.getEstado()=="Positivo")
		{
			rdbtnPositivo.setSelected(true);
			
		}
		else
		{
			rdbtnNegativo.setSelected(true);
			
		}
	}
	/**
	 * detecta el radio button que ingreso el usuario, para retornar el estado
	 * @param rdbtnEnTesteo
	 * @param rdbtnPositivo
	 * @return String
	 */
	public String detectarSeleccion(JRadioButton rdbtnEnTesteo,JRadioButton rdbtnPositivo)
	{
		if(rdbtnEnTesteo.isSelected())
		{
			return "En testeo";
		}
		else if(rdbtnPositivo.isSelected())
		{
			return "Positivo";
		}
		else
		{
			return "Negativo";
		}
	}
	/**
	 * reemplaza los datos que el usuario estuvo editando sobre la persona.
	 */
	public void guardarEdicion()
	{
		if(flag==true)
		{
			auxPersona.setNombre(nombreUser.getText());
			auxPersona.setApellido(inputApellido.getText());
			auxPersona.setEstado(detectarSeleccion(rdbtnEnTesteo, rdbtnPositivo));
				
			//System.out.println(botonSelecc);
			listado.agregarPersona(auxPersona);
		}
		else
		{
			JOptionPane.showMessageDialog(null,"ERROR! NO HAY DATOS QUE GUARDAR.");
		}
	}
	
	
	
}
