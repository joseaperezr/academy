package es.uned.dao;

import java.util.ArrayList;
import java.util.List;


import es.uned.model.Inmueble;
import es.uned.model.Interesado;
import es.uned.model.Usuario;

import org.hibernate.SessionFactory;

/**
 * 
 * User DAO
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Jul 2012
 * @version 1.0.0
 *
 */

public class InteresadoDAO implements IInteresadoDAO {
	
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
	public void addInteresado(Interesado interesado) {
		getSessionFactory().getCurrentSession().save(interesado);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Override
	public void deleteInteresado(Interesado interesado) {
		getSessionFactory().getCurrentSession().delete(interesado);
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void updateInteresado(Interesado interesado) {
		getSessionFactory().getCurrentSession().update(interesado);
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Interesado getInteresadoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				                            .createQuery("from Interesado where id=?")
									        .setParameter(0, id).list();
		return (Interesado)list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Interesado> getInteresados() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Interesado").list();
		return list;
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public Interesado getInteresadoByEmail(String email) {
		List list = new ArrayList();
		list = getSessionFactory().getCurrentSession()
				                            .createQuery("from Interesado where email=? ")
									        .setParameter(0, email).list();
		if (!list.isEmpty()){
		   return (Interesado)list.get(0);
		}
		else{
          System.out.println("nulo");
		  return null;
		}	
	}
	
	
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Interesado> getInteresadosByInmueble(Inmueble inmueble) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Interesado where intereses in (?)")
		        .setParameter(0, inmueble).list();
	
		return list;
	}

}
