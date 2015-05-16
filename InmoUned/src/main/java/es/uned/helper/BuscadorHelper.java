package es.uned.helper;


import es.uned.model.Ubicacion;



/**
 * 
 * Buscador Helper
 * 
 * @author Jos� Antonio P�rez Reyes.
 * @since 24 Jun 2012
 * @version 1.0.0
 *
 */

public class BuscadorHelper {

	
	private String tama�o;

	private String precioMin;
	
	private String precioMax;

	private String habitaciones;
	
	private String ba�os;

	private boolean jardin;
	
	private boolean parking;
	
	private String provincia;
	

	private Ubicacion ubicacion = new Ubicacion();

	public String getTama�o() {
		return tama�o;
	}

	public void setTama�o(String tama�o) {
		this.tama�o = tama�o;
	}

	public String getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(String precioMin) {
		this.precioMin = precioMin;
	}

	public String getPrecioMax() {
		return precioMax;
	}

	public void setPrecioMax(String precioMax) {
		this.precioMax = precioMax;
	}


	public String getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(String habitaciones) {
		this.habitaciones = habitaciones;
	}

	public String getBa�os() {
		return ba�os;
	}

	public void setBa�os(String ba�os) {
		this.ba�os = ba�os;
	}

	public boolean isJardin() {
		return jardin;
	}

	public void setJardin(boolean jardin) {
		this.jardin = jardin;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	

}
