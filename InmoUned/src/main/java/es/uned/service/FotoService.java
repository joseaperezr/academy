package es.uned.service;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import es.uned.model.Foto;
import es.uned.model.Inmueble;
import es.uned.model.Usuario;
import es.uned.dao.IFotoDAO;
import es.uned.helper.BuscadorHelper;

/**
 * 
 * Foto Service
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Agosto 2012
 * @version 1.0.0
 *  
 */
@Transactional(readOnly = true)
public class FotoService implements IFotoService {

	// FotoDAO is injected...
	IFotoDAO fotoDAO;

	/**
	 * Add Foto
	 * 
	 * @param Foto
	 *            Foto
	 */
	@Transactional(readOnly = false)
	@Override
	public void addFoto(Foto foto) {
		getFotoDAO().addFoto(foto);
	}

	/**
	 * Delete Foto
	 * 
	 * @param Foto
	 *            Foto
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteFoto(Foto foto) {
		getFotoDAO().deleteFoto(foto);
	}

	/**
	 * Update Foto
	 * 
	 * @param Foto
	 *            Foto
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateFoto(Foto foto) {
		getFotoDAO().updateFoto(foto);
	}

	/**
	 * Get Foto
	 * 
	 * @param int Foto Id
	 */
	@Override
	public Foto getFotoById(long id) {
		return getFotoDAO().getFotoById(id);
	}

	/**
	 * Get Foto List
	 * 
	 */
	@Override
	public List<Foto> getFotos() {
		return getFotoDAO().getFotos();
	}

	/**
	 * Get Foto DAO
	 * 
	 * @return IFotoDAO - Foto DAO
	 */
	public IFotoDAO getFotoDAO() {
		return fotoDAO;
	}

	/**
	 * Set Foto DAO
	 * 
	 * @param IFotoDAO
	 *            - Foto DAO
	 */
	public void setFotoDAO(IFotoDAO fotoDAO) {
		this.fotoDAO = fotoDAO;
	}

	
	public List<Foto> getFotosByInmueble(Inmueble inmueble) {

		return getFotoDAO().getFotosByInmueble(inmueble);
	}

	public Blob getBlob(byte[] data) {

		return Hibernate.getLobCreator(
				this.fotoDAO.getSessionFactory().getCurrentSession())
				.createBlob(data);

	}

}
