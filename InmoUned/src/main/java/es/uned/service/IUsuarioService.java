package es.uned.service;

import java.util.List;


import es.uned.model.Usuario;

/**
 * 
 * User Service Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IUsuarioService {
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addUsuario(Usuario usuario);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateUsuario(Usuario usuario);

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteUsuario(Usuario usuario);
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public Usuario getUsuarioById(int id);
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	public List<Usuario> getUsuarios();
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Usuario getLogin(String email, String password) ;
}
