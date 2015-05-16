package es.uned.service;

import java.sql.Blob;
import java.util.List;

import es.uned.model.Foto;
import es.uned.model.Inmueble;

/**
 * 
 * Foto Service Interface
 * 
 * @author José Antonio Pérez Reyes
 * @since 25 Agosto 2012
 * @version 1.0.0
 * 
 */
public interface IFotoService {

	/**
	 * Add Foto
	 * 
	 * @param Foto
	 *            Foto
	 */
	public void addFoto(Foto foto);

	/**
	 * Update Foto
	 * 
	 * @param Foto
	 *            Foto
	 */
	public void updateFoto(Foto foto);

	/**
	 * Delete Foto
	 * 
	 * @param Foto
	 *            Foto
	 */
	public void deleteFoto(Foto foto);

	/**
	 * Get Foto
	 * 
	 * @param int Foto Id
	 */
	public Foto getFotoById(long id);

	/**
	 * Get Foto List
	 * 
	 * @return List - Foto list
	 */
	public List<Foto> getFotos();

	
	public List<Foto> getFotosByInmueble(Inmueble inmueble) ;
	
	public Blob getBlob(byte[] data) ;
	
	
}
