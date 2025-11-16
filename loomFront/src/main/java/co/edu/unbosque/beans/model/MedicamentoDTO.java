package co.edu.unbosque.beans.model;


public class MedicamentoDTO {

	private Integer idMedicamento;

	private String nombreComercial;

	private String principioActivo;

	private String dosis;

	private String frecuencia;

	private Integer duracion;

	private Integer idConsulta;

	public MedicamentoDTO() {

	}

	public MedicamentoDTO(String nombreComercial, String principioActivo, String dosis, String frecuencia,
			Integer duracion, Integer idConsulta) {
		super();
		this.nombreComercial = nombreComercial;
		this.principioActivo = principioActivo;
		this.dosis = dosis;
		this.frecuencia = frecuencia;
		this.duracion = duracion;
		this.idConsulta = idConsulta;
	}

	public Integer getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getPrincipioActivo() {
		return principioActivo;
	}

	public void setPrincipioActivo(String principioActivo) {
		this.principioActivo = principioActivo;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	@Override
	public String toString() {
		return "Medicamento [idMedicamento=" + idMedicamento + ", nombreComercial=" + nombreComercial
				+ ", principioActivo=" + principioActivo + ", dosis=" + dosis + ", frecuencia=" + frecuencia
				+ ", duracion=" + duracion + ", consulta=" + idConsulta + "]";
	}

}
