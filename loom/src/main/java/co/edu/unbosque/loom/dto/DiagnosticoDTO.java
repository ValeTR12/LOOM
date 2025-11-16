package co.edu.unbosque.loom.dto;

public class DiagnosticoDTO {

    private Integer idDiagnostico;
    private String codigoCie10;
    private String descripcion;
    private Integer idConsulta;

   
    public DiagnosticoDTO() {
		// TODO Auto-generated constructor stub
	}
    
    public DiagnosticoDTO(String codigoCie10, String descripcion, Integer idConsulta) {
		super();
		this.codigoCie10 = codigoCie10;
		this.descripcion = descripcion;
		this.idConsulta = idConsulta;
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

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Integer getIdDiagnostico() {
		return idDiagnostico;
	}

	public void setIdDiagnostico(Integer idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}

	public int obtenerDiasValidez() {
        return switch (codigoCie10) {
            case "J11" -> 7;    // influenza
            case "K21" -> 30;   // reflujo gastroesofágico
            case "E11" -> 90;   // diabetes
            default -> 30;      // por defecto
        };
    }

	@Override
	public String toString() {
		return "Diagnostico [idDiagnostico=" + idDiagnostico + ", codigoCie10=" + codigoCie10 + ", descripcion="
				+ descripcion + ", consulta=" + idConsulta + "]";
	}
	
	
}


