package interfaces;


/**
 * Interfaz que contiene los metodos relacionados a la escritura y lectura de archivos.
 * Recibe un tipo generico para poder leer y escribir en distintos archivos.
 * @author Alejo Bocanegra y Martin Molina
 * @param <E>
 */
public interface PasarArchivo<E> {

	public void escribir(E listado);
	
	public E leerArchivo();
	
}
