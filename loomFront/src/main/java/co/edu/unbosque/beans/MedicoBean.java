package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.beans.model.ConsultaDTO;
import co.edu.unbosque.beans.model.EpsDTO;
import co.edu.unbosque.beans.model.MedicoDTO;
import co.edu.unbosque.beans.model.PacienteDTO;
import co.edu.unbosque.beans.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.ConsultaDAO;
import co.edu.unbosque.model.persistence.EpsDAO;
import co.edu.unbosque.model.persistence.MedicoDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("medicoBean")
@SessionScoped
public class MedicoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioDTO usuarioLogueado = new UsuarioDTO();
	private List<ConsultaDTO> consultasPorMedico = new ArrayList<>();
	private List<PacienteDTO> pacientesPorMedico = new ArrayList<>();
	private ConsultaDTO consultaSeleccionadaMedico;
	private PacienteDTO pacienteSeleccionadoMedico;
	private ConsultaDTO consultaSeleccionada;
	private MedicoDTO medicoActual = new MedicoDTO();
	private EpsDTO epsActual = new EpsDTO();
	private EpsDTO epsPacienteSeleccionado = new EpsDTO();
	private String vista = "info";

	@Inject
	private ConsultaDAO cDAO;
	@Inject
	private MedicoDAO mDAO;
	@Inject
	private EpsDAO eDAO;
	@Inject
	private PacienteDAO pDAO;

	@PostConstruct
	public void init() {
		System.out.println("🌀 Ejecutando init() de ConsultaBean...");
		try {
			obtenerMedico();
			System.out.println(medicoActual.getPrimerNombre());
		} catch (Exception e) {
		}
	}

	public void obtenerMedico() {
		usuarioLogueado = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogueado");
		System.out.println(usuarioLogueado.getUsername());

		medicoActual = mDAO.findByUsername(usuarioLogueado.getUsername());
		epsActual = eDAO.findById(medicoActual.getIdEps());

	}

	public void consultasPorPacientePorMedico() {
		usuarioLogueado = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogueado");

		if (usuarioLogueado == null) {
			System.out.println("⚠️ No hay usuario logueado en sesión.");
			return;
		}

		System.out.println("ID MEDICO:" + medicoActual.getIdUsuario());
		consultasPorMedico = cDAO.getConsultasPorPacientePorMedico(medicoActual.getIdUsuario());
		vista = "consultas";

	}

	public void pacientesPorMedico() {
		pacientesPorMedico = pDAO.getPacientesPorMedico(medicoActual.getIdUsuario());
		System.out.println(pacientesPorMedico.size());
	}

	// 🔹 Al dar click en un card
	public void seleccionarConsultaMedico(ConsultaDTO consulta) {
		this.consultaSeleccionadaMedico = consulta;

	}

	public void seleccionarPacienteMedico(PacienteDTO paciente) {
		this.pacienteSeleccionadoMedico = paciente;
		this.epsPacienteSeleccionado = eDAO.findById(paciente.getIdEps());

	}

	public void mostrarPacientes() {
		pacientesPorMedico(); // Cargar pacientes
		vista = "pacientes"; // Cambiar vista
	}

	public String obtenerEspecialidad(Integer id) {
		MedicoDTO med = mDAO.findById(id);
		return med.getEspecialidad();
	}
	
	public void cambiarVista(String vista) {
		this.vista = vista;
	}

	public String getVista() {
		return vista;
	}

	public PacienteDTO getPacienteSeleccionadoMedico() {
		return pacienteSeleccionadoMedico;
	}

	public void setPacienteSeleccionadoMedico(PacienteDTO pacienteSeleccionadoMedico) {
		this.pacienteSeleccionadoMedico = pacienteSeleccionadoMedico;
	}

	public EpsDTO getEpsPacienteSeleccionado() {
		return epsPacienteSeleccionado;
	}

	public void setEpsPacienteSeleccionado(EpsDTO epsPacienteSeleccionado) {
		this.epsPacienteSeleccionado = epsPacienteSeleccionado;
	}

	public EpsDTO getEpsActual() {
		return epsActual;
	}

	public void setEpsActual(EpsDTO epsActual) {
		this.epsActual = epsActual;
	}

	public EpsDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(EpsDAO eDAO) {
		this.eDAO = eDAO;
	}

	public List<ConsultaDTO> getConsultasPorMedico() {
		return consultasPorMedico;
	}

	public void setConsultasPorMedico(List<ConsultaDTO> consultasPorMedico) {
		this.consultasPorMedico = consultasPorMedico;
	}

	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public ConsultaDTO getConsultaSeleccionadaMedico() {
		return consultaSeleccionadaMedico;
	}

	public void setConsultaSeleccionadaMedico(ConsultaDTO consultaSeleccionadaMedico) {
		this.consultaSeleccionadaMedico = consultaSeleccionadaMedico;
	}

	public ConsultaDTO getConsultaSeleccionada() {
		return consultaSeleccionada;
	}

	public void setConsultaSeleccionada(ConsultaDTO consultaSeleccionada) {
		this.consultaSeleccionada = consultaSeleccionada;
	}

	public MedicoDTO getMedicoActual() {
		return medicoActual;
	}

	public void setMedicoActual(MedicoDTO medicoActual) {
		this.medicoActual = medicoActual;
	}

	public ConsultaDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(ConsultaDAO cDAO) {
		this.cDAO = cDAO;
	}

	public MedicoDAO getmDAO() {
		return mDAO;
	}

	public void setmDAO(MedicoDAO mDAO) {
		this.mDAO = mDAO;
	}

	public List<PacienteDTO> getPacientesPorMedico() {
		return pacientesPorMedico;
	}

	public void setPacientesPorMedico(List<PacienteDTO> pacientesPorMedico) {
		this.pacientesPorMedico = pacientesPorMedico;
	}

	public PacienteDAO getpDAO() {
		return pDAO;
	}

	public void setpDAO(PacienteDAO pDAO) {
		this.pDAO = pDAO;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

}
