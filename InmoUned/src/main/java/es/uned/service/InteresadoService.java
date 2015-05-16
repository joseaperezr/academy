package es.uned.service;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import es.uned.model.Inmueble;
import es.uned.model.Interesado;
import es.uned.dao.IInteresadoDAO;

/**
 * 
 * Interesado Service
 * 
 * @author José Antonio Pérez Reyes.
 * @since 25 Mar 2012
 * @version 1.0.0
 * 
 */
@Transactional(readOnly = true)
public class InteresadoService implements IInteresadoService {

	// InteresadoDAO is injected...
	IInteresadoDAO interesadoDAO;

	/**
	 * Add Interesado
	 * 
	 * @param Interesado
	 *            Interesado
	 */
	@Transactional(readOnly = false)
	@Override
	public void addInteresado(Interesado Interesado) {
		getInteresadoDAO().addInteresado(Interesado);
	}

	/**
	 * Delete Interesado
	 * 
	 * @param Interesado
	 *            Interesado
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteInteresado(Interesado interesado) {
		getInteresadoDAO().deleteInteresado(interesado);
	}

	/**
	 * Update Interesado
	 * 
	 * @param Interesado
	 *            Interesado
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateInteresado(Interesado interesado) {
		getInteresadoDAO().updateInteresado(interesado);
	}

	/**
	 * Get Interesado
	 * 
	 * @param int Interesado Id
	 */
	@Override
	public Interesado getInteresadoById(int id) {
		return getInteresadoDAO().getInteresadoById(id);
	}

	/**
	 * Get Interesado List
	 * 
	 */
	@Override
	public List<Interesado> getInteresados() {
		return getInteresadoDAO().getInteresados();
	}

	/**
	 * Get Interesado DAO
	 * 
	 * @return IInteresadoDAO - Interesado DAO
	 */
	public IInteresadoDAO getInteresadoDAO() {
		return this.interesadoDAO;
	}

	/**
	 * Set Interesado DAO
	 * 
	 * @param IInteresadoDAO
	 *            - Interesado DAO
	 */
	public void setInteresadoDAO(IInteresadoDAO interesadoDAO) {
		this.interesadoDAO = interesadoDAO;
	}

	public Interesado getInteresadoByEmail(String email) {
		return this.interesadoDAO.getInteresadoByEmail(email);

	}

	public List<Interesado> getInteresadosByInmueble(Inmueble inmueble) {
		return this.interesadoDAO.getInteresadosByInmueble(inmueble);
	}

}
