package clases;

import java.io.Serializable;
/**
 * Contiene todos los atributos de un Usuario, asi tambien como sus metodos de acceso y modificacion.
 * Implementa la interfaz Serializable ya que los usuarios se guardan en el archivo.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class Usuario implements Serializable {
	
	private String nombreU;
	private String clave;
	private Boolean admin;
	
	/**
	 * Constructor que recibe los atributos y los iguala a los de la clase.
	 * @param nombreU
	 * @param clave
	 * @param admin
	 */
	public Usuario(String nombreU, String clave, Boolean admin) {
		this.nombreU = nombreU;
		this.clave = clave;
		this.admin = admin;
		}
	/**
	 * Constructor Que inicializa los atributos a "vacio".
	 */
	public Usuario()
	{
		nombreU = "";
		clave = "";
		admin = false;
	}
	public String getNombreU() {
		return nombreU;
	}

	public void setNombreU(String nombreU) {
		this.nombreU = nombreU;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	/**
	 * Retorna el Objeto Usuario en un String.
	 * return String
	 */
	@Override
	public String toString() {
		return "Nombre =" + nombreU + ", Clave" + clave + ", admin=" + admin;
				 
	}

}
