package es.uned.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Clase Entidad Persona 
 * 
 * @author José Antonio Pérez Reyes
 *
 */
@Entity
@Table(name="PERSONA")
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona {

	@Id
	//@Column(name="ID", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment" , strategy="increment")
	private int id;

	private String dni;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private String movil;

	private String fijo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

}