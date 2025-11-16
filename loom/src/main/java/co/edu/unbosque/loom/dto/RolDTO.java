package co.edu.unbosque.loom.dto;


public class RolDTO {

	private Integer idRol;
	private String nombreRol;
	private String descripcion;
	
	public RolDTO() {
		// TODO Auto-generated constructor stub
	}

	public RolDTO(String nombreRol, String descripcion) {
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

	@Override
	public String toString() {
		return "RolDTO [idRol=" + idRol + ", nombreRol=" + nombreRol + ", descripcion=" + descripcion + "]";
	}
	
	
}

