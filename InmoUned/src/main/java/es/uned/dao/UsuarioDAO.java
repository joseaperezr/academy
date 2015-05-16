package es.uned.dao;

import java.util.List;


import es.uned.model.Usuario;

import org.hibernate.SessionFactory;

/**
 * 
 * User DAO
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

public class UsuarioDAO implements IUsuarioDAO {
	
	private SessionFactory sessionFactory;

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Set Hibernate Session Factory
	 * 
	 * @param SessionFactory - Hibernate Session Factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Override
	public void addUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().save(usuario);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Override
	public void deleteUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().delete(usuario);
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void updateUsuario(Usuario usuario) {
		//getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().update(usuario);
		//getSessionFactory().getCurrentSession().getTransaction().commit();
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Usuario getUsuarioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				                            .createQuery("from Usuario where id=?")
									        .setParameter(0, id).list();
		return (Usuario)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Usuario> getUsuarios() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Usuario").list();
		return list;
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Usuario getLogin(String email, String password) {
		List list = getSessionFactory().getCurrentSession()
				                            .createQuery("from Usuario where email=? and password=?")
									        .setParameter(0, email)
									        .setParameter(1, password).list();
									        
		return (Usuario)list.get(0);
	}

}
