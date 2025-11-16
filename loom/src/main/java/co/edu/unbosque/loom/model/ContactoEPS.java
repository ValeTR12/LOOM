package co.edu.unbosque.loom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contactoEps")
public class ContactoEPS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContactoEps;

    private String tipo;   // "DIRE" o "MAIL"
    private String valor;

    @ManyToOne
    @JoinColumn(name = "id_eps_fk")
    private Eps eps;

	public ContactoEPS() {
		
	}

	public ContactoEPS(String tipo, String valor, Eps eps) {
		super();
		this.tipo = tipo;
		this.valor = valor;
		this.eps = eps;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}
	

	public Integer getIdContactoEps() {
		return idContactoEps;
	}

	public void setIdContactoEps(Integer idContactoEps) {
		this.idContactoEps = idContactoEps;
	}

	@Override
	public String toString() {
		return "ContactoEPS [idDireccion=" + idContactoEps + ", tipo=" + tipo + ", valor=" + valor + ", eps=" + eps + "]";
	}

	
	
	
}

