package es.uned.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import es.uned.model.Inmueble;
import es.uned.model.Usuario;

/**
 * 
 * Inmueble DAO Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface IInmuebleDAO {

	/**
	 * Add Inmueble
	 * 
	 * @param  Inmueble Inmueble
	 */
	public void addInmueble(Inmueble inmueble);
	
	/**
	 * Update Inmueble
	 * 
	 * @param  Inmueble Inmueble
	 */
	public void updateInmueble(Inmueble inmueble);
	
	/**
	 * Delete Inmueble
	 * 
	 * @param  Inmueble Inmueble
	 */
	public void deleteInmueble(Inmueble inmueble);
	
	/**
	 * Get Inmueble
	 * 
	 * @param  int Inmueble Id
	 */
	public Inmueble getInmuebleById(long id);
	
	/**
	 * Get Inmueble List
	 * 
	 */
	public List<Inmueble> getInmuebles();
	
	
	public List<Inmueble> getInmueblesByBuscador(String query) ;
	
	public List<Inmueble> getInmueblesByPropietario(Usuario usuario) ;
	
	public SessionFactory getSessionFactory() ;
	
	public void setSessionFactory(SessionFactory sessionFactory);
}
