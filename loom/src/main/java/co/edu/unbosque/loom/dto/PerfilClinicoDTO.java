package co.edu.unbosque.loom.dto;

public class PerfilClinicoDTO {
	
	private Integer idPaciente;
	private String nombreCompleto;
	private Long totalConsultas;
	private Long totalDiagnosticos;
	private Long totalMedicamentos;
	
	public PerfilClinicoDTO() {
		// TODO Auto-generated constructor stub
	}

	public PerfilClinicoDTO(Integer idPaciente, String nombreCompleto, Long totalConsultas, Long totalDiagnosticos,
			Long totalMedicamentos) {
		super();
		this.idPaciente = idPaciente;
		this.nombreCompleto = nombreCompleto;
		this.totalConsultas = totalConsultas;
		this.totalDiagnosticos = totalDiagnosticos;
		this.totalMedicamentos = totalMedicamentos;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Long getTotalConsultas() {
		return totalConsultas;
	}

	public void setTotalConsultas(Long totalConsultas) {
		this.totalConsultas = totalConsultas;
	}

	public Long getTotalDiagnosticos() {
		return totalDiagnosticos;
	}

	public void setTotalDiagnosticos(Long totalDiagnosticos) {
		this.totalDiagnosticos = totalDiagnosticos;
	}

	public Long getTotalMedicamentos() {
		return totalMedicamentos;
	}

	public void setTotalMedicamentos(Long totalMedicamentos) {
		this.totalMedicamentos = totalMedicamentos;
	}
}
