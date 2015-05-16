package es.uned.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import es.uned.service.IOpcionService;

/**
 * 
 * Opciones Menu Managed Bean
 * 
 * @author José Antonio Pérez Reyes
 * @since 24 Jun 2012
 * @version 1.0.0
 * 
 */
@ManagedBean(name = "opcionesMenuMB")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class OpcionesMenuManagedBean implements Serializable {

	//
	private static final long serialVersionUID = 155L;
    //
	@ManagedProperty(value = "#{OpcionService}")
	private IOpcionService opcionService;
    //
	private boolean menuMiAreaMisDatos;
	//
	private boolean menuMiAreaMisPropiedades;
    //
	private boolean menuMiAreaRegistrarInmueble;
	
	
	@PostConstruct
	public void init() {
		loadOpciones();
	}

	public IOpcionService getOpcionService() {
		return opcionService;
	}

	public void setOpcionService(IOpcionService opcionService) {
		this.opcionService = opcionService;
	}

	public boolean getMenuMiAreaMisDatos() {
		return menuMiAreaMisDatos;
	}

	public void setMenuMiAreaMisDatos(boolean menuMiAreaMisDatos) {
		this.menuMiAreaMisDatos = menuMiAreaMisDatos;
	}
	
	public boolean getMenuMiAreaMisPropiedades() {
		return menuMiAreaMisPropiedades;
	}

	public void setMenuMiAreaMisPropiedades(boolean menuMiAreaMisPropiedades) {
		this.menuMiAreaMisPropiedades = menuMiAreaMisPropiedades;
	}

	public boolean getMenuMiAreaRegistrarInmueble() {
		return menuMiAreaRegistrarInmueble;
	}

	public void setMenuMiAreaRegistrarInmueble(boolean menuMiAreaRegistrarInmueble) {
		this.menuMiAreaRegistrarInmueble = menuMiAreaRegistrarInmueble;
	}

	public void loadOpciones(){
		this.menuMiAreaMisDatos = getOpcionService().getOpcionById("MENU_MA_MISDATOS")
				.isHabilitada();
		
		this.menuMiAreaMisPropiedades = getOpcionService().getOpcionById("MENU_MA_MISPROPIEDADES")
				.isHabilitada();
		
		this.menuMiAreaRegistrarInmueble = getOpcionService().getOpcionById("MENU_MA_REGINMUEBLE")
				.isHabilitada();
	}

}
