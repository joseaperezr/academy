package es.uned.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import javax.persistence.Table;



/**
 * Clase Entidad Usuario 
 * 
 * @author José Antonio Pérez Reyes
 *
 */
@Entity
@Table(name="USUARIO")
//@PrimaryKeyJoinColumn(name="personaId")
public class Usuario  extends Persona {

	//*@Id
	//@Column(name="ID", unique = true, nullable = false )
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//*@GeneratedValue(generator="increment")
	//*@GenericGenerator(name="increment" , strategy="increment")
	//@GeneratedValue(generator="system-uuid")
	//@GenericGenerator(name="system-uuid", strategy = "uuid")
	//private String id;
	//*private int id;
	
	private String password;

	//private String userName;
	@Column(unique = true, nullable = false)
	private String email;
	
	private String tipoUsuario = TiposUsuarios.CLIENTE.name();
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="propietario")
	private List<Inmueble> inmuebles = new ArrayList<Inmueble>();
/*	
	@ManyToMany
	@JoinTable(name="USU_INM",   joinColumns=
            @JoinColumn(name="USUARIO_ID", referencedColumnName="ID"),
        inverseJoinColumns=
            @JoinColumn(name="INMUEBLE_ID", referencedColumnName="ID")
        )
	private List<Inmueble> intereses = new ArrayList<Inmueble>();
	*/

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
/*
	public List<Inmueble> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Inmueble> intereses) {
		this.intereses = intereses;
	}
*/	
}