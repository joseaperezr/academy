package es.uned.dao;

import java.sql.Blob;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import es.uned.helper.BuscadorHelper;
import es.uned.model.Foto;
import es.uned.model.Inmueble;
import es.uned.model.Usuario;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;

/**
 * 
 * Foto DAO
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Agosto 2012
 * @version 1.0.0
 * 
 */

public class FotoDAO implements IFotoDAO {

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
	 * @param SessionFactory
	 *            - Hibernate Session Factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Add User
	 * 
	 * @param User
	 *            user
	 */
	@Override
	public void addFoto(Foto foto) {
		getSessionFactory().getCurrentSession().save(foto);
	}

	/**
	 * Delete User
	 * 
	 * @param User
	 *            user
	 */
	@Override
	public void deleteFoto(Foto foto) {
		getSessionFactory().getCurrentSession().delete(foto);
	}

	/**
	 * Update User
	 * 
	 * @param User
	 *            user
	 */
	@Override
	public void updateFoto(Foto foto) {
		getSessionFactory().getCurrentSession().update(foto);
	}

	/**
	 * Get User
	 * 
	 * @param int User Id
	 * @return User
	 */
	@Override
	public Foto getFotoById(long id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Foto where id=?").setParameter(0, id).list();
		return (Foto) list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Foto> getFotos() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Foto").list();
		return list;
	}

	

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<Foto> getFotosByInmueble(Inmueble inmueble) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Foto where inmueble=?")
				.setParameter(0, inmueble).list();

		return list;
	}

}
