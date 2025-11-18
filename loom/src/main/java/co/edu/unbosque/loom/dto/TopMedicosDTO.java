package co.edu.unbosque.loom.dto;

public class TopMedicosDTO {
	
	private Integer idMedico;
    private String nombreCompleto;
    private Long totalConsultas;
    
    public TopMedicosDTO() {
		// TODO Auto-generated constructor stub
	}

	public TopMedicosDTO(Integer idMedico, String nombreCompleto, Long totalConsultas) {
		super();
		this.idMedico = idMedico;
		this.nombreCompleto = nombreCompleto;
		this.totalConsultas = totalConsultas;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
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
}
