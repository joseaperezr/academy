package es.uned.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Clase Entidad Opcion 
 * 
 * @author José Antonio Pérez Reyes
 *
 */
@Entity
@Table(name="OPCION")
public class Opcion implements Serializable {

	@Id
	@Column(name="ID", unique = true, nullable = false)
	private String id;
	
	@Column(nullable = false)
	private boolean habilitada;
	
	
	public Opcion(){
		
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	
}