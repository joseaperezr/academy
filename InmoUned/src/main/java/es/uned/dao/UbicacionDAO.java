package es.uned.dao;

import java.util.List;

import es.uned.model.Ubicacion;


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

public class UbicacionDAO implements IUbicacionDAO {
	
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
	public void addUbicacion(Ubicacion ubicacion) {
		getSessionFactory().getCurrentSession().save(ubicacion);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Override
	public void deleteUbicacion(Ubicacion ubicacion) {
		getSessionFactory().getCurrentSession().delete(ubicacion);
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void updateUbicacion(Ubicacion ubicacion) {
		getSessionFactory().getCurrentSession().update(ubicacion);
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Ubicacion getUbicacionById(long id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from Ubicacion where id=?")
									        .setParameter(0, id).list();
		return (Ubicacion)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Ubicacion> getUbicacions() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Ubicacion").list();
		return list;
	}

}
