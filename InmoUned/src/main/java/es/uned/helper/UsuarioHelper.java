package es.uned.helper;

import es.uned.model.TiposUsuarios;


/**
 * 
 * Helper Usuario
 * 
 * @author José Antonio Pérez Reyes.
 * @since 24 Jun 2012
 * @version 1.0.0
 *
 */

public class UsuarioHelper {

	private int id;

	private String dni;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private String email;

	private String movil;

	private String fijo;
	
	private String password;

	private String tipoUsuario = TiposUsuarios.CLIENTE.name();
	
	
	public UsuarioHelper() {
		super();
	}

	public UsuarioHelper(Integer id, String dni, String nombre,
			String apellido1, String apellido2, String email, String movil,
			String fijo, String password, String userName, String tipoUsuario) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.movil = movil;
		this.fijo = fijo;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
