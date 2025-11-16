package co.edu.unbosque.loom.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "eps")
public class Eps {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEps;
	private String nombreEps;
	private String tipoRegimen;
	private String correo;
	
	@OneToMany(mappedBy = "eps", cascade = CascadeType.ALL)
	private List<ContactoEPS> contactos;//TEL o DIRE

	public Eps() {
		// TODO Auto-generated constructor stub
	}

	public Eps(String nombreEps, String tipoRegimen, String correo, List<ContactoEPS> contactos) {
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

	public List<ContactoEPS> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoEPS> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "Eps [idEps=" + idEps + ", nombreEps=" + nombreEps + ", tipoRegimen=" + tipoRegimen + ", correo="
				+ correo + ", contactos=" + contactos + "]";
	}
	
	
}
