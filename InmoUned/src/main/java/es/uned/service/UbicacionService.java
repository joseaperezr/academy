package es.uned.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.uned.model.Ubicacion;
import es.uned.dao.IUbicacionDAO;

/**
 * 
 * Ubicacion Service
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Transactional(readOnly = true)
public class UbicacionService implements IUbicacionService {

	// UbicacionDAO is injected...
	IUbicacionDAO UbicacionDAO;
	
	/**
	 * Add Ubicacion
	 * 
	 * @param  Ubicacion Ubicacion
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUbicacion(Ubicacion ubicacion) {
		getUbicacionDAO().addUbicacion(ubicacion);
	}

	/**
	 * Delete Ubicacion
	 * 
	 * @param  Ubicacion Ubicacion
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteUbicacion(Ubicacion ubicacion) {
		getUbicacionDAO().deleteUbicacion(ubicacion);
	}
	
	/**
	 * Update Ubicacion
	 * 
	 * @param  Ubicacion Ubicacion
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateUbicacion(Ubicacion ubicacion) {
		getUbicacionDAO().updateUbicacion(ubicacion);
	}
	
	/**
	 * Get Ubicacion
	 * 
	 * @param  int Ubicacion Id
	 */
	@Override
	public Ubicacion getUbicacionById(long id) {
		return getUbicacionDAO().getUbicacionById(id);
	}

	/**
	 * Get Ubicacion List
	 * 
	 */
	@Override
	public List<Ubicacion> getUbicacions() {	
		return getUbicacionDAO().getUbicacions();
	}

	/**
	 * Get Ubicacion DAO
	 * 
	 * @return IUbicacionDAO - Ubicacion DAO
	 */
	public IUbicacionDAO getUbicacionDAO() {
		return UbicacionDAO;
	}

	/**
	 * Set Ubicacion DAO
	 * 
	 * @param IUbicacionDAO - Ubicacion DAO
	 */
	public void setUbicacionDAO(IUbicacionDAO ubicacionDAO) {
		this.UbicacionDAO = ubicacionDAO;
	}

}
