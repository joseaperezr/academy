package es.uned.dao;

import java.sql.Blob;
import java.util.List;

import es.uned.helper.BuscadorHelper;
import es.uned.model.Inmueble;
import es.uned.model.Usuario;


import org.hibernate.Hibernate;
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

public class InmuebleDAO implements IInmuebleDAO {
	
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
	public void addInmueble(Inmueble inmueble) {
		getSessionFactory().getCurrentSession().save(inmueble);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Override
	public void deleteInmueble(Inmueble inmueble) {
		getSessionFactory().getCurrentSession().delete(inmueble);
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void updateInmueble(Inmueble inmueble) {
		getSessionFactory().getCurrentSession().update(inmueble);
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Inmueble getInmuebleById(long id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from Inmueble where id=?")
									        .setParameter(0, id).list();
		return (Inmueble)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Inmueble> getInmuebles() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Inmueble").list();
		return list;
	}
	
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Inmueble> getInmueblesByBuscador(String query) {
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Inmueble> getInmueblesByPropietario(Usuario usuario) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Inmueble where propietario=?")
		        .setParameter(0, usuario).list();
	
		return list;
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 *
	@Override
	public List<Inmueble> getInmueblesByInteresado(Usuario usuario) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Inmueble where propietario=?")
		        .setParameter(0, usuario).list();
	
		return list;
	}*/

	

}
