package es.uned.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;


import es.uned.model.Opcion;
import es.uned.service.IOpcionService;

/**
 * 
 * Controler Control de  Opciones 
 * 
 * @author José Antonio Pérez Reyes
 * @since 24 Jun 2012
 * @version 1.0.0
 * 
 */
@ManagedBean(name = "opcionesMB")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class OpcionesManagedBean implements Serializable {

	//
	private static final long serialVersionUID = 154L;

	@ManagedProperty(value = "#{OpcionService}")
	private IOpcionService opcionService;
	
	@ManagedProperty(value = "#{opcionesMenuMB}")
	private OpcionesMenuManagedBean  opcionesMenuManagedBean;

	private boolean menuMiAreaMisDatos;

	private boolean menuMiAreaMisPropiedades;

	private boolean menuMiAreaRegistrarInmueble;

	@PostConstruct
	public void init() {

		//
		this.menuMiAreaMisDatos = getOpcionService().getOpcionById(
				"MENU_MA_MISDATOS").isHabilitada();

		this.menuMiAreaMisPropiedades = getOpcionService().getOpcionById(
				"MENU_MA_MISPROPIEDADES").isHabilitada();

		this.menuMiAreaRegistrarInmueble = getOpcionService().getOpcionById(
				"MENU_MA_REGINMUEBLE").isHabilitada();

	}

	public IOpcionService getOpcionService() {
		return opcionService;
	}

	public void setOpcionService(IOpcionService opcionService) {
		this.opcionService = opcionService;
	}

	public boolean isMenuMiAreaMisDatos() {
		return this.menuMiAreaMisDatos;
	}

	public void setMenuMiAreaMisDatos(boolean menuMiAreaMisDatos) {
		this.menuMiAreaMisDatos = menuMiAreaMisDatos;
	}

	public boolean isMenuMiAreaMisPropiedades() {
		return menuMiAreaMisPropiedades;
	}

	public void setMenuMiAreaMisPropiedades(boolean menuMiAreaMisPropiedades) {
		this.menuMiAreaMisPropiedades = menuMiAreaMisPropiedades;
	}

	public boolean isMenuMiAreaRegistrarInmueble() {
		return menuMiAreaRegistrarInmueble;
	}

	public void setMenuMiAreaRegistrarInmueble(
			boolean menuMiAreaRegistrarInmueble) {
		this.menuMiAreaRegistrarInmueble = menuMiAreaRegistrarInmueble;
	}

	public OpcionesMenuManagedBean getOpcionesMenuManagedBean() {
		return opcionesMenuManagedBean;
	}

	public void setOpcionesMenuManagedBean(
			OpcionesMenuManagedBean opcionesMenuManagedBean) {
		this.opcionesMenuManagedBean = opcionesMenuManagedBean;
	}

	/**
	 * Update 
	 * 
	 * @return String - Response Message
	 */
	public void updOpciones() {
		System.out.print("****************************************Actualizando control de opciones...");
		try {

			Opcion opcion = getOpcionService()
					.getOpcionById("MENU_MA_MISDATOS");

			opcion.setHabilitada(this.menuMiAreaMisDatos);

			getOpcionService().updateOpcion(opcion);

			opcion = getOpcionService().getOpcionById("MENU_MA_MISPROPIEDADES");

			opcion.setHabilitada(this.menuMiAreaMisPropiedades);

			getOpcionService().updateOpcion(opcion);

			opcion = getOpcionService().getOpcionById("MENU_MA_REGINMUEBLE");

			opcion.setHabilitada(this.menuMiAreaRegistrarInmueble);

			getOpcionService().updateOpcion(opcion);
			
			getOpcionesMenuManagedBean().loadOpciones();

			FacesMessage msg = new FacesMessage("+info",
					"Modificaciones realizadas con éxito." );
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

}
