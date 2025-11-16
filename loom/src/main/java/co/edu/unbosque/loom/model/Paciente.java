package co.edu.unbosque.loom.model;

import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "id_paciente")
public class Paciente extends Usuario {

	@ManyToOne
	@JoinColumn(name = "id_eps")
	private Eps eps;
	private LocalDate fechaAfiliacion;
	private String tipoAfiliacion;

	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	public Paciente(LocalDate fechaAfiliacion, String tipoAfiliacion) {
		super();
		this.fechaAfiliacion = fechaAfiliacion;
		this.tipoAfiliacion = tipoAfiliacion;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public LocalDate getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(LocalDate fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public String getTipoAfiliacion() {
		return tipoAfiliacion;
	}

	public void setTipoAfiliacion(String tipoAfiliacion) {
		this.tipoAfiliacion = tipoAfiliacion;
	}

	@Override
	public String toString() {
		return "Paciente [eps=" + eps + ", fechaAfiliacion=" + fechaAfiliacion + ", tipoAfiliacion=" + tipoAfiliacion
				+ "]";
	}

}
