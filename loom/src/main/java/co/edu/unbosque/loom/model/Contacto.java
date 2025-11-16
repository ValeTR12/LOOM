package co.edu.unbosque.loom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContacto;

    private String tipo;   // "TEL" o "MAIL" o "DIRE"
    private String valor;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

	public Contacto() {
		
	}

	public Contacto(String tipo, String valor, Usuario usuario) {
		super();
		this.tipo = tipo;
		this.valor = valor;
		this.usuario = usuario;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Contacto [idContacto=" + idContacto + ", tipo=" + tipo + ", valor=" + valor + ", usuario=" + usuario
				+ "]";
	}
	
	
}







