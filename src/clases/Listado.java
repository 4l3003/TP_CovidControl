package clases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Clase generica que contiene todos los metodos relacionados al listado generico.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class Listado<E> {

	private HashMap<String,E> lista;
	
	/**
	 * Constructor
	 */
	public Listado()
	{
		lista = new HashMap<String,E>();
	}
	
	/**
	 * Agrega al HashMap lista un elemento.
	 * @param key
	 * @param elemento
	 */
	public void agregar(String key, E elemento)
	{
		 lista.put(key, elemento);
	}
	
	/**
	 * Cuenta los elementos de la lista.
	 * @return lista.size()
	 */
	public int contar()
	{
		return lista.size();
	}
	
	/**
	 * Elimina un elemento de la lista buscando por la clave
	 * @param key
	 */
	public void eliminar(String key)
	{
		lista.remove(key);
	}
	
	/**
	 * Retorna el valor correspondiente a la clave que recibe
	 * @param key
	 * @return elemento
	 */
	public E verElemento(String key)
	{
		return lista.get(key);
	}
	
	/**
	 * Verifica si existe un elemento en la lista.
	 * @param key
	 * @return boolean
	 */
	public boolean existe(String key)
	{
		return lista.containsKey(key);
	}
	
	/**
	 * Recorre la lista y retorna un String con toda la lista.
	 * @return String
	 */
	public String listar()
	{
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, E>> it = lista.entrySet().iterator();
		while (it.hasNext())
		{
			
			Map.Entry<String, E> e = (Entry<String, E>)it.next();
			
			sb.append(e.getKey() + "--" + e.getValue());
		}
		return sb.toString();
	}
	
	/**
	 * Retorna la lista.
	 * @return HashMap<String, E>
	 */
	public HashMap<String, E> getLista() {
		return lista;
	}
	
	/**
	 * Setea la lista recibida por parametro.
	 * @param lista
	 */
	public void setLista(HashMap<String, E> lista) {
		this.lista = lista;
	}

	/**
	 * ToString de la lista.
	 */
	@Override
	public String toString() {
		return "Listado [lista=" + lista + "]";
	}
}
