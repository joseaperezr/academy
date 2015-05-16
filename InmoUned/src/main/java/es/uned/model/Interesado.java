package es.uned.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * Clase Entidad Interesado 
 * 
 * @author José Antonio Pérez Reyes
 *
 */
@Entity
@Table(name="INTERESADO")
//@PrimaryKeyJoinColumn(name="personaId")
public class Interesado  extends Persona {

	//*@Id
	//@Column(name="ID", unique = true, nullable = false )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//*@GeneratedValue(generator="increment")
	//*@GenericGenerator(name="increment" , strategy="increment")
	//@GeneratedValue(generator="system-uuid")
	//@GenericGenerator(name="system-uuid", strategy = "uuid")
	//private String id;
	//*private int id;
	
	

	//private String userName;
	@Column(unique = true, nullable = false)
	private String email;
	
	//private int tipoUsuario;
	 
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="INT_INM",   joinColumns=
            @JoinColumn(name="INTERESADO_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="INMUEBLE_ID", referencedColumnName="ID")
        )
	private List<Inmueble> intereses = new ArrayList<Inmueble>();






	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

	public List<Inmueble> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Inmueble> intereses) {
		this.intereses = intereses;
	}
	
}