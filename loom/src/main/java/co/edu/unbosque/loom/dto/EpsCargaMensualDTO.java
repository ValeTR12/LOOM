package co.edu.unbosque.loom.dto;

public class EpsCargaMensualDTO {
	
	private String nombre;
	private Integer mes;
	private Long totalConsultas;
	
	public EpsCargaMensualDTO() {
		// TODO Auto-generated constructor stub
	}

	public EpsCargaMensualDTO(String nombre, Integer mes, Long totalConsultas) {
		super();
		this.nombre = nombre;
		this.mes = mes;
		this.totalConsultas = totalConsultas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Long getTotalConsultas() {
		return totalConsultas;
	}

	public void setTotalConsultas(Long totalConsultas) {
		this.totalConsultas = totalConsultas;
	}
}
