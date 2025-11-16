package co.edu.unbosque.loom.dto;

import java.time.LocalDate;

public class ExamenDTO {

	private Integer idExamen;

	private String tipoExamen;

	private String resultado;

	private LocalDate fechaExamen;

	private Integer idConsulta;

	public ExamenDTO() {

	}

	public ExamenDTO(String tipoExamen, String resultado, LocalDate fechaExamen, Integer idConsulta) {
		super();
		this.tipoExamen = tipoExamen;
		this.resultado = resultado;
		this.fechaExamen = fechaExamen;
		this.idConsulta = idConsulta;
	}

	public Integer getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}

	public String getTipoExamen() {
		return tipoExamen;
	}

	public void setTipoExamen(String tipoExamen) {
		this.tipoExamen = tipoExamen;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public LocalDate getFechaExamen() {
		return fechaExamen;
	}

	public void setFechaExamen(LocalDate fechaExamen) {
		this.fechaExamen = fechaExamen;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	@Override
	public String toString() {
		return "Examen [idExamen=" + idExamen + ", tipoExamen=" + tipoExamen + ", resultado=" + resultado
				+ ", fechaExamen=" + fechaExamen + ", consulta=" + idConsulta + "]";
	}

}
