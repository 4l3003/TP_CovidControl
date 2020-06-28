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
 *  Define el ListadoPersonas y contiene los metodos para Manejar el mismo, Ademas implementa de la
 *  interfaz PasarArchivo.
 * @author Alejo Bocanegra y Martin Molina
 */
public class ListadoPersonas implements PasarArchivo<ListadoPersonas> {
	
	private Listado<Persona> listadopersona;
	
	
	/**
	 * Constructor.
	 * Inicializa el listadopersona
	 */
	public ListadoPersonas()
	{
		listadopersona = new Listado<>();
	}
	
	/**
	 * Agrega una persona al listado
	 * @param persona
	 */
	public void agregarPersona(Persona persona)
	{
		listadopersona.agregar(persona.getDni(), persona);
	}
	/**
	 * Verifica si existe una persona en el listado
	 * @param dni
	 * @return boolean
	 */
	public boolean existePersona(String dni)
	{
		return listadopersona.existe(dni);
	}
	/**
	 * Elimina una persona del listado.
	 * @param dni
	 */
	public void borrarPersona(String dni)
	{
		listadopersona.eliminar(dni);
	}
	
	/**
	 * Busca una persona y la retorna.
	 * @param dni
	 * @return Persona
	 */
	public Persona buscarUnaPersona(String dni)
	{
		return listadopersona.verElemento(dni);
	}
	
	/**
	 * Lista todas las personas y retorna un String que contiene a las mismas.
	 * @return String
	 */
	public String listarPersonas()
	{
		return listadopersona.listar();
	}
	
	/**
	 * Retorna el listado de personas
	 * @return HashMap<String, Persona>
	 */
	public HashMap<String, Persona> getListadopersona() {
		return listadopersona.getLista();
	}

	
	/**
	 * Escribe todo el listado de Personas en el archivo.
	 * @param  ListadoPersonas listado
	 */
	@Override
	public void escribir(ListadoPersonas listado) {
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream("personas.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			Iterator<Entry<String, Persona>> it = listado.getListadopersona().entrySet().iterator();
			while (it.hasNext())
			{
				
				Map.Entry<String, Persona> e = (Entry<String, Persona>)it.next();
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
	 * Lee todo el archivo, lo guarda en un listado de personas y retorna el mismo
	 * @return ListadoPersonas
	 */
	@Override
	public ListadoPersonas leerArchivo() {
		Persona auxPersona;
		try
		{
			FileInputStream fileInputStream = new FileInputStream("personas.dat");
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			while((auxPersona = (Persona) inputStream.readObject())!=null)
			{
				//System.out.println(auxPersona.toString());
				agregarPersona(auxPersona);	
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
