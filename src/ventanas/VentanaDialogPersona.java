package ventanas;


import java.awt.Color;


import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JPanel;


import com.teamdev.jxmaps.LatLng;

import clases.ListadoPersonas;
import clases.Persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Esta clase contiene todos los ajustes y el funcionamiento de la ventana emergente que se abre al clickear en el boton Registrar del mapa.
 * @author Alejo Bocanegra y Martin Molina

 */
public class VentanaDialogPersona extends VentanaDialog  {

	private static final long serialVersionUID = 7667138047728699031L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Persona persona;
	private LatLng coord;
	private int Nbtn;
	private ListadoPersonas listado;
	
	/**
	 * Constructor Principal
	 * llama al constructor del padre.
	 * iguala los parametros recibidos a los de la clase.
	 * Setea un titulo de ventana y inicia toda la parte visual.
	 * @param coord
	 * @param Nbtn
	 * @param listado
	 */
	public VentanaDialogPersona(LatLng coord, int Nbtn, ListadoPersonas listado) {
		super();
		this.listado = listado;
		this.coord = coord;
		this.Nbtn = Nbtn;
		setTitle("REGISTRAR PERSONA TESTEADA");
		iniciarComponentes();
		
	}
	/**
	 * Contiene todos el estilo visual de la pantalla, y llama a distintos metodos que validan y realizan acciones sobre los
	 * datos ingresados por el usuario
	 */
	public void iniciarComponentes()
	{
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(0, 139, 139));
		JLabel lblNewLabel = new JLabel("Dni:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(111, 45, 98, 29);
		panel.add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.setBounds(158, 48, 136, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(158, 83, 136, 24);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(158, 118, 136, 24);
		panel.add(textField_2);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
		lblNombre.setBounds(89, 80, 79, 29);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Arial", Font.BOLD, 12));
		lblApellido.setBounds(89, 115, 79, 29);
		panel.add(lblApellido);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Arial", Font.BOLD, 12));
		lblEstado.setBounds(89, 167, 56, 24);
		panel.add(lblEstado);
		
		ButtonGroup grupoestados = new ButtonGroup();
		
		JRadioButton rdbtnPositivo = new JRadioButton("En testeo");
		rdbtnPositivo.setBounds(287, 169, 79, 23);
		rdbtnPositivo.setBackground(new Color(0, 139, 139));
		panel.add(rdbtnPositivo);
		
		JRadioButton rdbtnNegativo = new JRadioButton("Positivo");
		rdbtnNegativo.setBounds(133, 169, 63, 23);
		rdbtnNegativo.setBackground(new Color(0, 139, 139));
		panel.add(rdbtnNegativo);
		
		JRadioButton rdbtnEnTesteo = new JRadioButton("Negativo");
		rdbtnEnTesteo.setBounds(206, 169, 69, 23);
		rdbtnEnTesteo.setBackground(new Color(0, 139, 139));
		panel.add(rdbtnEnTesteo);
		
		
		grupoestados.add(rdbtnPositivo);
		grupoestados.add(rdbtnNegativo);
		grupoestados.add(rdbtnEnTesteo);
		
		
		if(Nbtn == 1)
		{
			rdbtnPositivo.setSelected(true);
			rdbtnNegativo.setEnabled(false);
			rdbtnEnTesteo.setEnabled(false);
		}
		else if (Nbtn == 2)
		{
			rdbtnNegativo.setSelected(true);
			rdbtnPositivo.setEnabled(false);
			rdbtnEnTesteo.setEnabled(false);
		}
		else 
		{
			rdbtnEnTesteo.setSelected(true);
			rdbtnPositivo.setEnabled(false);
			rdbtnNegativo.setEnabled(false);
		}
		
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
				String state = ValidarRButton(rdbtnPositivo,rdbtnNegativo,rdbtnEnTesteo);
				
				persona = new Persona(textField.getText(), textField_1.getText(), textField_2.getText(),state, coord.toString());
				if(!listado.existePersona(persona.getDni()))
				{
					dispose();
					setPersona(persona);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"ERROR! YA EXISTE UNA PERSONA CON ESE DNI.");
				}

			}
		});
		
		btnNewButton.setBounds(169, 227, 89, 23);
		panel.add(btnNewButton);
	}
	/**
	 * Valida que boton selecciono el usuario
	 * @param btn1
	 * @param btn2
	 * @param btn3
	 * @return String
	 */
	public String ValidarRButton(JRadioButton btn1, JRadioButton btn2, JRadioButton btn3)
	{
		if(btn1.isSelected())
		{
			return "En testeo";
		}
		else if(btn2.isSelected())
		{
			return "Positivo";
		}
		else
		{
			return "Negativo";
		}
	}


	public Persona getPersona()
	{
		return persona;
	}
	public void setPersona(Persona persona)
	{
		this.persona = persona;
	}

	
	
}

