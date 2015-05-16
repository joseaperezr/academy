package es.uned.helper;

import java.io.Serializable;
import org.primefaces.model.StreamedContent;

/**
 * Clase Helper para los datos de la foto
 * 
 * @author José Antonio Pérez Reyes.
 *
 */
public class FotoHelper implements Serializable {

	//
	private static final long serialVersionUID = 4345L;

	private String id;

	private String nombre;

	private String mimeType;

	private StreamedContent foto;

	//private Inmueble inmueble = new Inmueble();

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
	

	/*
	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}*/

}
