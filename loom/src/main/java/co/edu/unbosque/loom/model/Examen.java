package co.edu.unbosque.loom.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExamen;

    private String tipoExamen;

    private String resultado;

    private LocalDate fechaExamen;

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;

    public Examen() {
    	
    }

    
    public Examen(String tipoExamen, String resultado, LocalDate fechaExamen, Consulta consulta) {
		super();
		this.tipoExamen = tipoExamen;
		this.resultado = resultado;
		this.fechaExamen = fechaExamen;
		this.consulta = consulta;
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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }


	@Override
	public String toString() {
		return "Examen [idExamen=" + idExamen + ", tipoExamen=" + tipoExamen + ", resultado=" + resultado
				+ ", fechaExamen=" + fechaExamen + ", consulta=" + consulta + "]";
	}
    
    
}

