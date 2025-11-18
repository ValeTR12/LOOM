package co.edu.unbosque.loom.dto;

public class TopEnfermedadesDTO {
	
	private String codigoCie10;
    private String descripcion;
    private Long total;
    
    public TopEnfermedadesDTO() {
		// TODO Auto-generated constructor stub
	}

	public TopEnfermedadesDTO(String codigoCie10, String descripcion, Long total) {
		super();
		this.codigoCie10 = codigoCie10;
		this.descripcion = descripcion;
		this.total = total;
	}

	public String getCodigoCie10() {
		return codigoCie10;
	}

	public void setCodigoCie10(String codigoCie10) {
		this.codigoCie10 = codigoCie10;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
