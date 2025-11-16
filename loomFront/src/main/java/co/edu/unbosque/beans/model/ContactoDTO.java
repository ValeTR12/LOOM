package co.edu.unbosque.beans.model;

public class ContactoDTO {

    private Integer idContacto;
    private String tipo;   // "TEL" o "MAIL"
    private String valor;
    private Integer idUsuario;

	public ContactoDTO() {
		
	}

	public ContactoDTO(String tipo, String valor, Integer idUsuario) {
		super();
		this.tipo = tipo;
		this.valor = valor;
		this.idUsuario = idUsuario;
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

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "ContactoDTO [idContacto=" + idContacto + ", tipo=" + tipo + ", valor=" + valor + ", idUsuario="
				+ idUsuario + "]";
	}

	
	
}