package es.uned.service;

import java.sql.Blob;
import java.util.List;

import es.uned.helper.BuscadorHelper;
import es.uned.model.Inmueble;
import es.uned.model.Usuario;

/**
 * 
 * Inmueble Service Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 * 
 */
public interface IInmuebleService {

	/**
	 * Add Inmueble
	 * 
	 * @param Inmueble
	 *            Inmueble
	 */
	public void addInmueble(Inmueble inmueble);

	/**
	 * Update Inmueble
	 * 
	 * @param Inmueble
	 *            Inmueble
	 */
	public void updateInmueble(Inmueble inmueble);

	/**
	 * Delete Inmueble
	 * 
	 * @param Inmueble
	 *            Inmueble
	 */
	public void deleteInmueble(Inmueble inmueble);

	/**
	 * Get Inmueble
	 * 
	 * @param int Inmueble Id
	 */
	public Inmueble getInmuebleById(long id);

	/**
	 * Get Inmueble List
	 * 
	 * @return List - Inmueble list
	 */
	public List<Inmueble> getInmuebles();

	public List<Inmueble> getInmueblesByBuscador(BuscadorHelper buscador);

	public List<Inmueble> getInmueblesByPropietario(Usuario usuario);
	
	public Blob getBlob(byte[] data) ;
	
	
}
