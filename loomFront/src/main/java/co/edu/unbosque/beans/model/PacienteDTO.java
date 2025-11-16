package co.edu.unbosque.beans.model;

import java.time.LocalDate;


public class PacienteDTO extends UsuarioDTO {

	private Integer idEps;
	private LocalDate fechaAfiliacion;
	private String tipoAfiliacion;

	public PacienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public PacienteDTO(Integer idEps, LocalDate fechaAfiliacion, String tipoAfiliacion) {
		super();
		this.idEps = idEps;
		this.fechaAfiliacion = fechaAfiliacion;
		this.tipoAfiliacion = tipoAfiliacion;
	}

	public Integer getIdEps() {
		return idEps;
	}

	public void setIdEps(Integer idEps) {
		this.idEps = idEps;
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
		return "Paciente [eps=" + idEps + ", fechaAfiliacion=" + fechaAfiliacion + ", tipoAfiliacion=" + tipoAfiliacion
				+ "]";
	}

}
