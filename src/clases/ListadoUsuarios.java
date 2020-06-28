package clases;

import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import interfaces.PasarArchivo;

/**
 *  Define el ListadoUsuarios y contiene los metodos para Manejar el mismo, Ademas implementa de la
 *  interfaz PasarArchivo.
 * @author Alejo Bocanegra y Martin Molina
 */
public class ListadoUsuarios implements PasarArchivo<ListadoUsuarios> {
	
	private Listado<Usuario> listadoUsuarios;	
	
	/**
	 * Constructor.
	 * Inicializa el listadopersona
	 */
	public ListadoUsuarios() {
		listadoUsuarios = new Listado<>();
	}
	
	/**
	 * Agrega un usuario al listado
	 * @param Usuario
	 */
	public void agregarUsuario(Usuario usuario)
	{
		listadoUsuarios.agregar(usuario.getNombreU(),usuario);
	}
	
	/**
	 * Elimina un usuario del listado.
	 * @param dni
	 */
	public void borrarUsuario(String nombreU)
	{
		listadoUsuarios.eliminar(nombreU);
	}
	
	/**
	 * Busca un Usuario y nos retorna el mismo en String.
	 * @param String nombreUsuario
	 * @return String 
	 */
	public String verUnUsuario(String nombreU)
	{
		return listadoUsuarios.verElemento(nombreU).toString();
	}
	

	/**
	 * Lista todos los usuarios y retorna un String que contiene a los mismos.
	 * @return String
	 */
	public String listarUsuarios()
	{
		return listadoUsuarios.listar();
	}
	
	/**
	 * Busca un usuario y lo retorna.
	 * @param String nombre
	 * @return Usuario
	 */
	public Usuario buscarUnUsuario(String nombre)
	{
		return listadoUsuarios.verElemento(nombre);
	}

	/**
	 * Retorna el HashMap de usuarios.
	 * @return HashMap<String, Usuario>
	 */
	public HashMap<String, Usuario> getListadoUsuarios() {
		return listadoUsuarios.getLista();
	}

	/**
	 * Escribe todo el listado de usuarios en el archivo.
	 * @param  ListadoUsuarios listado
	 */
	@Override
	public void escribir(ListadoUsuarios listado) {
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream("usuarios.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			Iterator<Entry<String, Usuario>> it = listado.getListadoUsuarios().entrySet().iterator();
			while (it.hasNext())
			{
				
				Map.Entry<String, Usuario> e = (Entry<String, Usuario>)it.next();
				objectOutputStream.writeObject(e.getValue());
			}
			
			objectOutputStream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Lee todo el archivo, lo guarda en un listado de Usuarios y retorna el mismo.
	 * @return ListadoUsuarios
	 */
	@Override
	public ListadoUsuarios leerArchivo() {
		Usuario auxUsuario;
		try
		{
			FileInputStream fileInputStream = new FileInputStream("usuarios.dat");
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			while((auxUsuario = (Usuario) inputStream.readObject())!=null)
			{
				System.out.println(auxUsuario.toString());
				agregarUsuario(auxUsuario);	
			}
			inputStream.close();
		}
		catch(EOFException e)
		{
			System.out.println("fin del archivo");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Archivo no existe, se creara uno");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return this;
		
	}


	
	
	
	
}
	
	

