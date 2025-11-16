package co.edu.unbosque.loom.dto;

import java.util.List;


public class EpsDTO {
	
	private Integer idEps;
	private String nombreEps;
	private String tipoRegimen;
	private String correo;
	
	private List<ContactoEPSDTO> contactos;//TEL o DIRE

	public EpsDTO() {
		// TODO Auto-generated constructor stub
	}

	public EpsDTO(String nombreEps, String tipoRegimen, String correo, List<ContactoEPSDTO> contactos) {
		super();
		this.nombreEps = nombreEps;
		this.tipoRegimen = tipoRegimen;
		this.correo = correo;
		this.contactos = contactos;
	}

	public Integer getIdEps() {
		return idEps;
	}

	public void setIdEps(Integer idEps) {
		this.idEps = idEps;
	}

	public String getNombreEps() {
		return nombreEps;
	}

	public void setNombreEps(String nombreEps) {
		this.nombreEps = nombreEps;
	}

	public String getTipoRegimen() {
		return tipoRegimen;
	}

	public void setTipoRegimen(String tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<ContactoEPSDTO> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoEPSDTO> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "Eps [idEps=" + idEps + ", nombreEps=" + nombreEps + ", tipoRegimen=" + tipoRegimen + ", correo="
				+ correo + ", contactos=" + contactos + "]";
	}
	
	
}