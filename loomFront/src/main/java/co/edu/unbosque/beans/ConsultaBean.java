package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.beans.model.ConsultaDTO;
import co.edu.unbosque.beans.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.ConsultaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("consultaBean")
@SessionScoped
public class ConsultaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuarioLogueado = new UsuarioDTO();
	private List<ConsultaDTO> consultas = new ArrayList<>();
	private ConsultaDTO consultaSeleccionada;

	@Inject
	private ConsultaDAO cDAO;

	// @Inject
	// private PacienteDAO pDAO;

	@PostConstruct
	public void init() {
		System.out.println("🌀 Ejecutando init() de ConsultaBean...");
		try {
			obtenerPaciente();
		} catch (Exception e) {
		}
	}

	public void obtenerPaciente() {
		usuarioLogueado = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogueado");
		System.out.println(usuarioLogueado.getUsername());

		// PacienteDTO paciente = pDAO.obtenerPorId(id);

	}

	public String obtenerLogoEps(String nombreEps) {
		if (nombreEps == null) {
			return "default.png";
		}

		switch (nombreEps.toLowerCase()) {
		case "compensar":
			return "compensar.png";
		case "coosalud":
			return "coosalud.png";
		case "famisanar":
			return "famisanar.jpg";
		case "mutual ser":
			return "mutualser.png";
		case "nueva eps":
			return "nuevaeps.png";
		case "salud total":
			return "saludtotal.png";
		case "sanitas":
			return "sanitas.png";
		case "sura":
			return "sura.png";
		default:
			return "default.png";
		}
	}

	public void seleccionarConsulta(ConsultaDTO consulta) {
		this.consultaSeleccionada = consulta;
	}

	public ConsultaDTO getConsultaSeleccionada() {
		return consultaSeleccionada;
	}

	public void consultasPorPaciente() {
		// 🔁 Refrescar el usuario logueado en caso de cambio
		usuarioLogueado = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogueado");

		if (usuarioLogueado == null) {
			System.out.println("⚠️ No hay usuario logueado en sesión.");
			return;
		}

		System.out.println("Consultas cargadas para: " + usuarioLogueado.getUsername());
		consultas = cDAO.getConsultasPorPaciente(usuarioLogueado.getUsername());
		System.out.println(consultas.size());
	}

	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public List<ConsultaDTO> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<ConsultaDTO> consultas) {
		this.consultas = consultas;
	}

	public ConsultaDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(ConsultaDAO cDAO) {
		this.cDAO = cDAO;
	}
}
