package co.edu.unbosque.loom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rol")

public class Rol {

	private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Integer idRol;
	private String nombreRol;
	private String descripcion;
	
	public Rol() {
		// TODO Auto-generated constructor stub
	}

	public Rol(String nombreRol, String descripcion) {
		super();
		this.nombreRol = nombreRol;
		this.descripcion = descripcion;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
