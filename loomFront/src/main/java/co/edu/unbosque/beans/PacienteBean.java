package co.edu.unbosque.beans;

import java.io.Serializable;

import co.edu.unbosque.beans.model.EpsDTO;
import co.edu.unbosque.beans.model.PacienteDTO;
import co.edu.unbosque.beans.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.EpsDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("pacienteBean")
@SessionScoped
public class PacienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuarioLogueado = new UsuarioDTO();
	private PacienteDTO pacienteActual = new PacienteDTO();
	private String vista = "info";
	private EpsDTO epsActual = new EpsDTO();

	@Inject
	private PacienteDAO pDAO;
	@Inject
	private EpsDAO eDAO;

	@PostConstruct
	public void init() {
		System.out.println("🌀 Ejecutando init() de PacienteBean...");
		try {
			obtenerPaciente();
			System.out.println(pacienteActual.getPrimerNombre());
		} catch (Exception e) {
		}
	}

	public void obtenerPaciente() {
		usuarioLogueado = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogueado");
		pacienteActual = pDAO.findByUsername(usuarioLogueado.getUsername());
		epsActual = eDAO.findById(pacienteActual.getIdEps());
	}

	public void cambiarVista(String vista) {
		switch (vista) {
		case "info":
			this.vista = "info";
		case "consultas":
			this.vista = "consultas";
		case "diagnosticos":
			this.vista = "diagnosticos";
		case "medicamentos":
			this.vista = "medicamentos";
		case "examenes":
			this.vista = "examenes";
		}
	}

	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public PacienteDTO getPacienteActual() {
		return pacienteActual;
	}

	public void setPacienteActual(PacienteDTO pacienteActual) {
		this.pacienteActual = pacienteActual;
	}

	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

	public EpsDTO getEpsActual() {
		return epsActual;
	}

	public void setEpsActual(EpsDTO epsActual) {
		this.epsActual = epsActual;
	}

	public PacienteDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(PacienteDAO pDAO) {
		this.pDAO = pDAO;
	}

	public EpsDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(EpsDAO eDAO) {
		this.eDAO = eDAO;
	}
}
