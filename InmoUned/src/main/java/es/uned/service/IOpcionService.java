package es.uned.service;

import java.util.List;


import es.uned.model.Opcion;

/**
 * 
 * User Service Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IOpcionService {
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addOpcion(Opcion opcion);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateOpcion(Opcion opcion);

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteOpcion(Opcion opcion);
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public Opcion getOpcionById(String id);
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	public List<Opcion> getOpciones();
	
	
}
