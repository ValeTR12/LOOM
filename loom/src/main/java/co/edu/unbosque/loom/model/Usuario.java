package co.edu.unbosque.loom.model;

import jakarta.persistence.Inheritance;

import jakarta.persistence.InheritanceType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	private String documento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;

	private String direccion;
	private String ciudad;
	private String contrasena;
	private String username;

	@ManyToOne // Muchos usuarios a Un rol
	@JoinColumn(name = "id_rol")
	private Rol rol;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Contacto> contactos;

	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String username, String contrasena) {
		super();
		this.contrasena = contrasena;
		this.username = username;
	}

	public Usuario(String username, String documento, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String direccion, String ciudad, String contrasena, Rol rol,
			List<Contacto> contactos) {
		super();
		this.username = username;
		this.documento = documento;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.contrasena = contrasena;
		this.rol = rol;
		this.contactos = contactos;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getPrimerNombre() {
		return primerNombre;
	}


	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}


	public String getSegundoNombre() {
		return segundoNombre;
	}


	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}


	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}


	public String getSegundoApellido() {
		return segundoApellido;
	}


	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	public List<Contacto> getContactos() {
		return contactos;
	}


	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", documento=" + documento + ", primerNombre=" + primerNombre
				+ ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", direccion=" + direccion + ", ciudad=" + ciudad + ", contrasena=" + contrasena
				+ ", rol=" + rol + ", contactos=" + contactos + "]";
	}
	
	

}
