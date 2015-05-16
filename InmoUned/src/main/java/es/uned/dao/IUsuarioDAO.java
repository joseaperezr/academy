package es.uned.dao;

import java.util.List;

import es.uned.model.Usuario;

/**
 * 
 * Usuario DAO Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IUsuarioDAO {

	/**
	 * Add Usuario
	 * 
	 * @param  Usuario Usuario
	 */
	public void addUsuario(Usuario usuario);
	
	/**
	 * Update Usuario
	 * 
	 * @param  Usuario Usuario
	 */
	public void updateUsuario(Usuario usuario);
	
	/**
	 * Delete Usuario
	 * 
	 * @param  Usuario Usuario
	 */
	public void deleteUsuario(Usuario usuario);
	
	/**
	 * Get Usuario
	 * 
	 * @param  int Usuario Id
	 */
	public Usuario getUsuarioById(int id);
	
	/**
	 * Get Usuario List
	 * 
	 */
	public List<Usuario> getUsuarios();
	
	public Usuario getLogin(String email, String password);
}
