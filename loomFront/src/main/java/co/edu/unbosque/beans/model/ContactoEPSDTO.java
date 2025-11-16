package co.edu.unbosque.beans.model;

public class ContactoEPSDTO {

    private Integer idContactoEps;

    private String tipo;   // "DIRE" o "MAIL"
    private String valor;
    private Integer idEpsFk;

	public ContactoEPSDTO() {
		
	}

	public ContactoEPSDTO(String tipo, String valor, Integer idEpsFk) {
		super();
		this.tipo = tipo;
		this.valor = valor;
		this.idEpsFk = idEpsFk;
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
	

	public Integer getIdEpsFk() {
		return idEpsFk;
	}

	public void setIdEpsFk(Integer idEpsFk) {
		this.idEpsFk = idEpsFk;
	}

	public Integer getIdContactoEps() {
		return idContactoEps;
	}

	public void setIdContactoEps(Integer idContactoEps) {
		this.idContactoEps = idContactoEps;
	}

	@Override
	public String toString() {
		return "ContactoEPS [idDireccion=" + idContactoEps + ", tipo=" + tipo + ", valor=" + valor + ", eps=" + idEpsFk + "]";
	}	
}
