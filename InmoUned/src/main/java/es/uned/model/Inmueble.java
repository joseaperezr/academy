package es.uned.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uned.model.Ubicacion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Clase Entidad Inmueble 
 * 
 * @author José Antonio Pérez Reyes
 *
 */
@Entity
@Table(name = "INMUEBLE")
public class Inmueble implements Serializable {
	
	//
	private static final long serialVersionUID = 432L;

	@Id
	// @Column(name="ID", unique = true, nullable = false)
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	@Column(nullable = true)
	private String tamaño;

	private double precio;

	private int habitaciones;

	private int baños;

	private boolean jardin;

	private boolean parking;

	@Column(nullable = true)
	private String observaciones;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ubicacion", unique = true, nullable = false, updatable = false)
	private Ubicacion ubicacion = new Ubicacion();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="inmueble")
	private List<Foto> fotos = new ArrayList<Foto>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario propietario = new Usuario();

	@ManyToMany(mappedBy = "intereses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name="intereses")
	private List<Interesado> interesados = new ArrayList<Interesado>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTamaño() {
		return tamaño;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	public int getBaños() {
		return baños;
	}

	public void setBaños(int baños) {
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public List<Interesado> getInteresados() {
		return interesados;
	}

	public void setInteresados(List<Interesado> interesados) {
		this.interesados = interesados;
	}

}