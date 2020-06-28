package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Se encuentran todos los metodos relacionados a la Exportacion de archivo JSON que contiene las personas.
 * @author Alejo Bocanegra y Martin Molina
 *
 */
public class ExportarJson {
	
	private JSONArray jsonArray;
	private ListadoPersonas listadoPersonas = new ListadoPersonas();

	/**
	 * Constructor
	 * @param listadoPersonas
	 */
	public ExportarJson(ListadoPersonas listadoPersonas)
	{
		this.listadoPersonas = listadoPersonas;
		jsonArray = new JSONArray();
		
		pasarJson();
	}
	
	
	

	/**
	 * Recorre el Listado de personas mediante un iterator, los mete en un jsonArray para luego llamar al metodo grabar que nos genera el archivo
	 * .json con esos datos
	 */
	public void pasarJson()
	{
		
		Iterator<Entry<String, Persona>> it = listadoPersonas.getListadopersona().entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<String, Persona> e = (Entry<String, Persona>)it.next();
			
			try {
				jsonArray.put(getFormatoJSON(e.getValue()));
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			
		}
		grabar(jsonArray);
		
	}
	
	/**
	 * Crea un ObjetoJson y guarda en el los datos de la persona.
	 * @param persona
	 * @return jsonObject 
	 * @throws JSONException
	 */
	public JSONObject getFormatoJSON(Persona persona) throws JSONException
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("dni", persona.getDni());
		jsonObject.put("nombre",persona.getNombre());
		jsonObject.put("apellido",persona.getApellido());
		jsonObject.put("estado",persona.getEstado());
		jsonObject.put("coord",persona.getCoord());
		
		
		return jsonObject;
	}
	/**
	 * Genera el archivo .json, grabando en el, el jsonArray que recibe por parametro
	 * @param array
	 */
	public  void grabar(JSONArray array) {
		try {
			FileWriter file = new FileWriter("personas.json");
			file.write(array.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
