package es.uned.dao;

import java.util.List;

import es.uned.model.Ubicacion;

/**
 * 
 * Ubicacion DAO Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IUbicacionDAO {

	/**
	 * Add Ubicacion
	 * 
	 * @param  Ubicacion Ubicacion
	 */
	public void addUbicacion(Ubicacion ubicacion);
	
	/**
	 * Update Ubicacion
	 * 
	 * @param  Ubicacion Ubicacion
	 */
	public void updateUbicacion(Ubicacion ubicacion);
	
	/**
	 * Delete Ubicacion
	 * 
	 * @param  Ubicacion Ubicacion
	 */
	public void deleteUbicacion(Ubicacion ubicacion);
	
	/**
	 * Get Ubicacion
	 * 
	 * @param  int Ubicacion Id
	 */
	public Ubicacion getUbicacionById(long id);
	
	/**
	 * Get Ubicacion List
	 * 
	 */
	public List<Ubicacion> getUbicacions();
}
