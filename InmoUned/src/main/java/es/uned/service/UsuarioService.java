package es.uned.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.uned.model.Inmueble;
import es.uned.model.Usuario;
import es.uned.dao.IUsuarioDAO;

/**
 * 
 * Usuario Service
 * 
 * @author José Antonio Pérez Reyes.
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {

	// UsuarioDAO is injected...
	IUsuarioDAO usuarioDAO;
	
	/**
	 * Add Usuario
	 * 
	 * @param  Usuario Usuario
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUsuario(Usuario Usuario) {
		getUsuarioDAO().addUsuario(Usuario);
	}

	/**
	 * Delete Usuario
	 * 
	 * @param  Usuario Usuario
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteUsuario(Usuario usuario) {
		getUsuarioDAO().deleteUsuario(usuario);
	}
	
	/**
	 * Update Usuario
	 * 
	 * @param  Usuario Usuario
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateUsuario(Usuario usuario) {
		getUsuarioDAO().updateUsuario(usuario);
	}
	
	/**
	 * Get Usuario
	 * 
	 * @param  int Usuario Id
	 */
	@Override
	public Usuario getUsuarioById(int id) {
		return getUsuarioDAO().getUsuarioById(id);
	}

	/**
	 * Get Usuario List
	 * 
	 */
	@Override
	public List<Usuario> getUsuarios() {
		return getUsuarioDAO().getUsuarios();
	}

	/**
	 * Get Usuario DAO
	 * 
	 * @return IUsuarioDAO - Usuario DAO
	 */
	public IUsuarioDAO getUsuarioDAO() {
		return this.usuarioDAO;
	}

	/**
	 * Set Usuario DAO
	 * 
	 * @param IUsuarioDAO - Usuario DAO
	 */
	public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	
	public Usuario getLogin(String email, String password) {
		return this.usuarioDAO.getLogin(email, password);
		
	}
	
	
	

}
