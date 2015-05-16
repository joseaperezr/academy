package es.uned.helper;


import es.uned.model.Ubicacion;



/**
 * 
 * Buscador Helper
 * 
 * @author José Antonio Pérez Reyes.
 * @since 24 Jun 2012
 * @version 1.0.0
 *
 */

public class BuscadorHelper {

	
	private String tamaño;

	private String precioMin;
	
	private String precioMax;

	private String habitaciones;
	
	private String baños;

	private boolean jardin;
	
	private boolean parking;
	
	private String provincia;
	

	private Ubicacion ubicacion = new Ubicacion();

	public String getTamaño() {
		return tamaño;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
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

	public String getBaños() {
		return baños;
	}

	public void setBaños(String baños) {
		this.baños = baños;
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
