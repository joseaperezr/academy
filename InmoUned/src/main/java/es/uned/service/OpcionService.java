package es.uned.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.uned.model.Inmueble;
import es.uned.model.Opcion;
import es.uned.dao.IOpcionDAO;

/**
 * 
 * Opcion Service
 * 
 * @author José Antonio Pérez Reyes.
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Transactional(readOnly = true)
public class OpcionService implements IOpcionService {

	// OpcionDAO is injected...
	IOpcionDAO opcionDAO;
	
	/**
	 * Add Opcion
	 * 
	 * @param  Opcion Opcion
	 */
	@Transactional(readOnly = false)
	@Override
	public void addOpcion(Opcion Opcion) {
		getOpcionDAO().addOpcion(Opcion);
	}

	/**
	 * Delete Opcion
	 * 
	 * @param  Opcion Opcion
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteOpcion(Opcion opcion) {
		getOpcionDAO().deleteOpcion(opcion);
	}
	
	/**
	 * Update Opcion
	 * 
	 * @param  Opcion Opcion
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateOpcion(Opcion opcion) {
		getOpcionDAO().updateOpcion(opcion);
	}
	
	/**
	 * Get Opcion
	 * 
	 * @param  int Opcion Id
	 */
	@Override
	public Opcion getOpcionById(String id) {
		return getOpcionDAO().getOpcionById(id);
	}

	/**
	 * Get Opcion List
	 * 
	 */
	@Override
	public List<Opcion> getOpciones() {	
		return getOpcionDAO().getOpciones();
	}

	/**
	 * Get Opcion DAO
	 * 
	 * @return IOpcionDAO - Opcion DAO
	 */
	public IOpcionDAO getOpcionDAO() {
		return this.opcionDAO;
	}

	/**
	 * Set Opcion DAO
	 * 
	 * @param IOpcionDAO - Opcion DAO
	 */
	public void setOpcionDAO(IOpcionDAO opcionDAO) {
		this.opcionDAO = opcionDAO;
	}
	
	
	
	
	
	

}
