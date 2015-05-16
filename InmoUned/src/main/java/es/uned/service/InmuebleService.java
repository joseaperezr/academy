package es.uned.service;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import es.uned.model.Inmueble;
import es.uned.model.Usuario;
import es.uned.dao.IInmuebleDAO;
import es.uned.helper.BuscadorHelper;
import es.uned.managed.bean.UsuarioManagedBean;
import org.apache.log4j.Logger;

/**
 *
 * Inmueble Service
 *
 * @author José Antonio Pérez Reyes
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Transactional(readOnly = true)
public class InmuebleService implements IInmuebleService {

    // InmuebleDAO is injected...
    IInmuebleDAO inmuebleDAO;
    //
    private Logger logger = Logger.getLogger(InmuebleService.class);

    /**
     * Add Inmueble
     *
     * @param Inmueble Inmueble
     */
    @Transactional(readOnly = false)
    @Override
    public void addInmueble(Inmueble inmueble) {
        getInmuebleDAO().addInmueble(inmueble);
    }

    /**
     * Delete Inmueble
     *
     * @param Inmueble Inmueble
     */
    @Transactional(readOnly = false)
    @Override
    public void deleteInmueble(Inmueble inmueble) {
        getInmuebleDAO().deleteInmueble(inmueble);
    }

    /**
     * Update Inmueble
     *
     * @param Inmueble Inmueble
     */
    @Transactional(readOnly = false)
    @Override
    public void updateInmueble(Inmueble inmueble) {
        getInmuebleDAO().updateInmueble(inmueble);
    }

    /**
     * Get Inmueble
     *
     * @param int Inmueble Id
     */
    @Override
    public Inmueble getInmuebleById(long id) {
        return getInmuebleDAO().getInmuebleById(id);
    }

    /**
     * Get Inmueble List
     *
     */
    @Override
    public List<Inmueble> getInmuebles() {
        return getInmuebleDAO().getInmuebles();
    }

    /**
     * Get Inmueble DAO
     *
     * @return IInmuebleDAO - Inmueble DAO
     */
    public IInmuebleDAO getInmuebleDAO() {
        return inmuebleDAO;
    }

    /**
     * Set Inmueble DAO
     *
     * @param IInmuebleDAO - Inmueble DAO
     */
    public void setInmuebleDAO(IInmuebleDAO inmuebleDAO) {
        this.inmuebleDAO = inmuebleDAO;
    }

    /**
     *
     * @param buscador
     * @return
     */
    @Override
    public List<Inmueble> getInmueblesByBuscador(BuscadorHelper buscador) {
        String query = "from Inmueble  where " + " ubicacion.provincia = '"
                + buscador.getProvincia() + "'" + " and precio >= "
                + buscador.getPrecioMin() + " and precio <= "
                + buscador.getPrecioMax();
        if (buscador.getTamaño() != "") {
            query += " and tamaño = '" + buscador.getTamaño() + "'";
        }
        if (buscador.getHabitaciones() != "") {
            query += " and habitaciones = " + buscador.getHabitaciones() + "";
        }
        if (buscador.getBaños() != "") {
            query += " and baños " + buscador.getBaños() + "";
        }
        if (buscador.getUbicacion().getNombreVia() != "") {
            String nombreVia = buscador.getUbicacion().getNombreVia().toLowerCase().trim().replace("or", "");
            nombreVia = nombreVia.toLowerCase().trim().replace("and", "");
            nombreVia = nombreVia.toLowerCase().trim().replace("select", "");
            nombreVia = nombreVia.toLowerCase().trim().replace("from", "");
            nombreVia = nombreVia.toLowerCase().trim().replace("=", "");
            nombreVia = nombreVia.toLowerCase().trim().replace("'", "");
            query += " and ubicacion.nombreVia = '" + nombreVia + "' ";
        }
        logger.debug(query);
        return getInmuebleDAO().getInmueblesByBuscador(query);
    }

    public List<Inmueble> getInmueblesByPropietario(Usuario usuario) {

        return getInmuebleDAO().getInmueblesByPropietario(usuario);
    }

    public Blob getBlob(byte[] data) {
        return Hibernate.getLobCreator(
                this.inmuebleDAO.getSessionFactory().getCurrentSession())
                .createBlob(data);
    }
}
