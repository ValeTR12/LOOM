package co.edu.unbosque.loom.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "diagnostico")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiagnostico;

    private String codigoCie10;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

   
    public Diagnostico() {
		// TODO Auto-generated constructor stub
	}
    
    public Diagnostico(String codigoCie10, String descripcion, Consulta consulta) {
		super();
		this.codigoCie10 = codigoCie10;
		this.descripcion = descripcion;
		this.consulta = consulta;
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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
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
				+ descripcion + ", consulta=" + consulta + "]";
	}
	
	
}
