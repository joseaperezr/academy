package es.uned.dao;

import java.util.List;

import es.uned.model.Opcion;

/**
 * 
 * Opcion DAO Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Jul 2012
 * @version 1.0.0
 *
 */
public interface IOpcionDAO {

	/**
	 * Add Opcion
	 * 
	 * @param  Opcion Opcion
	 */
	public void addOpcion(Opcion opcion);
	
	/**
	 * Update Opcion
	 * 
	 * @param  Opcion Opcion
	 */
	public void updateOpcion(Opcion opcion);
	
	/**
	 * Delete Opcion
	 * 
	 * @param  Opcion Opcion
	 */
	public void deleteOpcion(Opcion opcion);
	
	/**
	 * Get Opcion
	 * 
	 * @param  int Opcion Id
	 */
	public Opcion getOpcionById(String id);
	
	/**
	 * Get Opcion List
	 * 
	 */
	public List<Opcion> getOpciones();
	
	
}
