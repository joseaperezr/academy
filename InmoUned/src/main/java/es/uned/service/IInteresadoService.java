package es.uned.service;

import java.util.List;


import es.uned.model.Inmueble;
import es.uned.model.Interesado;

/**
 * 
 * User Service Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IInteresadoService {
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addInteresado(Interesado interesado);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateInteresado(Interesado interesado);

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteInteresado(Interesado interesado);
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public Interesado getInteresadoById(int id);
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	public List<Interesado> getInteresados();
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Interesado getInteresadoByEmail(String email) ;
	
	
	
	public List<Interesado> getInteresadosByInmueble(Inmueble inmueble);
}
