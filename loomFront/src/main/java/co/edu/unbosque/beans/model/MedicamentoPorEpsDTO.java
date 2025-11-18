package co.edu.unbosque.beans.model;

public class MedicamentoPorEpsDTO {
	
	private String nombreEps;
	private String medicamento;
	private Integer total;
	
	public MedicamentoPorEpsDTO() {
		// TODO Auto-generated constructor stub
	}

	public MedicamentoPorEpsDTO(String nombreEps, String medicamento, Integer total) {
		super();
		this.nombreEps = nombreEps;
		this.medicamento = medicamento;
		this.total = total;
	}

	public String getNombreEps() {
		return nombreEps;
	}

	public void setNombreEps(String nombreEps) {
		this.nombreEps = nombreEps;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
