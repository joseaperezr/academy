package es.uned.dao;

import java.util.List;


import es.uned.model.Opcion;

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

public class OpcionDAO implements IOpcionDAO {
	
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
	public void addOpcion(Opcion opcion) {
		getSessionFactory().getCurrentSession().save(opcion);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Override
	public void deleteOpcion(Opcion opcion) {
		getSessionFactory().getCurrentSession().delete(opcion);
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void updateOpcion(Opcion opcion) {
		//getSessionFactory().getCurrentSession().beginTransaction();
		getSessionFactory().getCurrentSession().update(opcion);
		//getSessionFactory().getCurrentSession().getTransaction().commit();
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Opcion getOpcionById(String id) {
		List list = getSessionFactory().getCurrentSession()
				                            .createQuery("from Opcion where id=?")
									        .setParameter(0, id).list();
		return (Opcion)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Opcion> getOpciones() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Opcion").list();
		return list;
	}
	
	
}
