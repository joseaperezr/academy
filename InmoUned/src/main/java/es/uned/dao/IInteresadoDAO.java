package es.uned.dao;

import java.util.List;

import es.uned.model.Inmueble;
import es.uned.model.Interesado;

/**
 * 
 * Interesado DAO Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Jul 2012
 * @version 1.0.0
 *
 */
public interface IInteresadoDAO {

	/**
	 * Add Interesado
	 * 
	 * @param  Interesado Interesado
	 */
	public void addInteresado(Interesado interesado);
	
	/**
	 * Update Interesado
	 * 
	 * @param  Interesado Interesado
	 */
	public void updateInteresado(Interesado interesado);
	
	/**
	 * Delete Interesado
	 * 
	 * @param  Interesado Interesado
	 */
	public void deleteInteresado(Interesado interesado);
	
	/**
	 * Get Interesado
	 * 
	 * @param  int Interesado Id
	 */
	public Interesado getInteresadoById(int id);
	
	/**
	 * Get Interesado List
	 * 
	 */
	public List<Interesado> getInteresados();
	
	public Interesado getInteresadoByEmail(String email);
	
	public List<Interesado> getInteresadosByInmueble(Inmueble inmueble);
}
