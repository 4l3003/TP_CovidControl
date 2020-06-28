package clases;

import java.io.Serializable;



import com.teamdev.jxmaps.LatLng;
/**
 * Contiene todos los atributos de una Persona, asi tambien como sus metodos de acceso y modificacion.
 * Implementa la interfaz Serializable ya que las personas se guardan en el archivo.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 4820552935264201659L;
	private String dni;
	private String nombre;
	private String apellido;
	private String estado;
	private String coord;
	
	/**
	 * Constructor que inicializa a todos los atributos en vacio.
	 */
	public Persona()
	{
		this.dni = "";
		this.nombre = "";
		this.apellido = "";
		this.estado = "";
		this.coord = "";
	}
	/**
	 * Constructor que recibe los atributos y los iguala a los de la clase.
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param estado
	 * @param coord
	 */
	public Persona(String dni, String nombre, String apellido, String estado, String coord) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estado = estado;
		this.coord = coord;
	}
	
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Lee las Coordenadas de la persona y Retorna las mismas en un objeto LatLng
	 * @return LatLng
	 */
	public LatLng getCoord() {
		String aux = null;
		aux = coord.substring(1,coord.length()-1);
		String[] latLng = aux.split(",");
		
		return (new LatLng(Double.parseDouble(latLng[0]), Double.parseDouble(latLng[1])));
	}
	

	/**
	 * retorna el Objeto persona en un String.
	 * @return String
	 */
	@Override
	public String toString() {
		return "[dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", estado=" + estado
				+ ", coord=" + coord + "]";
	}
	
	
}
