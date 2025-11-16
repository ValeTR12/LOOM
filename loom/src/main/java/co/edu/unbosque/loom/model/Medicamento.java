package co.edu.unbosque.loom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicamento;

    private String nombreComercial;

    private String principioActivo;

    private String dosis;

    private String frecuencia;

    private Integer duracion; 

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;

    public Medicamento() {
    	
    }
    

    public Medicamento(String nombreComercial, String principioActivo, String dosis, String frecuencia,
			Integer duracion, Consulta consulta) {
		super();
		this.nombreComercial = nombreComercial;
		this.principioActivo = principioActivo;
		this.dosis = dosis;
		this.frecuencia = frecuencia;
		this.duracion = duracion;
		this.consulta = consulta;
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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }


	@Override
	public String toString() {
		return "Medicamento [idMedicamento=" + idMedicamento + ", nombreComercial=" + nombreComercial
				+ ", principioActivo=" + principioActivo + ", dosis=" + dosis + ", frecuencia=" + frecuencia
				+ ", duracion=" + duracion + ", consulta=" + consulta + "]";
	}
    
    
}
