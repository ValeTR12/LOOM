package co.edu.unbosque.beans;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.beans.model.ConsultaDTO;
import co.edu.unbosque.beans.model.DiagnosticoDTO;
import co.edu.unbosque.beans.model.EpsDTO;
import co.edu.unbosque.beans.model.ExamenDTO;
import co.edu.unbosque.beans.model.MedicamentoDTO;
import co.edu.unbosque.beans.model.PacienteDTO;
import co.edu.unbosque.beans.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.ConsultaDAO;
import co.edu.unbosque.model.persistence.DiagnosticoDAO;
import co.edu.unbosque.model.persistence.EpsDAO;
import co.edu.unbosque.model.persistence.ExamenDAO;
import co.edu.unbosque.model.persistence.MedicamentoDAO;
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
	private List<ConsultaDTO> consultas = new ArrayList<>();
	private List<DiagnosticoDTO> diagnosticos = new ArrayList<>();
	private List<MedicamentoDTO> medicamentos = new ArrayList<>();
	private List<ExamenDTO> examenes = new ArrayList<>();
	private ConsultaDTO consultaSeleccionada = new ConsultaDTO();
	private MedicamentoDTO medicamentoSeleccionado = new MedicamentoDTO();
	private ExamenDTO examenSeleccionado = new ExamenDTO();

	@Inject
	private ConsultaDAO cDAO;
	@Inject
	private PacienteDAO pDAO;
	@Inject
	private EpsDAO eDAO;
	@Inject
	private DiagnosticoDAO dDAO;
	@Inject
	private MedicamentoDAO mDAO;
	@Inject
	private ExamenDAO exDAO;

	@PostConstruct
	public void init() {
		System.out.println("🌀 Ejecutando init() de PacienteBean...");
		try {
			obtenerPaciente();
			System.out.println(pacienteActual.getPrimerNombre());
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
	}

	public void obtenerPaciente() {

		usuarioLogueado = (UsuarioDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuarioLogueado");
		pacienteActual = pDAO.findByUsername(usuarioLogueado.getUsername());
		System.out.println(pacienteActual.getPrimerNombre() + "KIMBY");
		epsActual = eDAO.findById(pacienteActual.getIdEps());
	}

	public void consultasPorPaciente() {
		if (usuarioLogueado == null) {
			System.out.println("⚠️ No hay usuario logueado en sesión.");
			return;
		}

		System.out.println("Consultas cargadas para: " + usuarioLogueado.getUsername());
		consultas = cDAO.getConsultasPorPaciente(usuarioLogueado.getUsername());
		System.out.println(consultas.size());
	}

	public void diagnosticosPorPaciente() {
		if (usuarioLogueado == null) {
			System.out.println("⚠️ No hay usuario logueado en sesión.");
			return;
		}

		System.out.println("Consultas cargadas para: " + usuarioLogueado.getUsername());
		diagnosticos = dDAO.getDiagnosticosPorPaciente(usuarioLogueado.getUsername());
		System.out.println(consultas.size());
	}

	public void medicamentosPorPaciente() {
		if (usuarioLogueado == null) {
			System.out.println("⚠️ No hay usuario logueado en sesión.");
			return;
		}

		System.out.println("Consultas cargadas para: " + usuarioLogueado.getUsername());
		medicamentos = mDAO.getMedicamentosPorPaciente(usuarioLogueado.getUsername());
		System.out.println(consultas.size());
	}

	public void examenesPorPaciente() {
		if (usuarioLogueado == null) {
			System.out.println("⚠️ No hay usuario logueado en sesión.");
			return;
		}

		System.out.println("Consultas cargadas para: " + usuarioLogueado.getUsername());
		examenes = exDAO.getExamenesPorPaciente(usuarioLogueado.getUsername());
		System.out.println(consultas.size());
	}

	public String mostrarFecha(Integer idConsulta) {
		for (ConsultaDTO consultaDTO : consultas) {
			if (consultaDTO.getIdConsulta().equals(idConsulta)) {
				return consultaDTO.getFechaConsulta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			}
		}
		return "";
	}
	
	public String mostrarFechaExamen(Integer idExamen) {
		for (ExamenDTO examenDTO : examenes) {
			if (examenDTO.getIdExamen().equals(idExamen)) {
				return examenDTO.getFechaExamen().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			}
		}
		return "";
	}

	public void cambiarVista(String vista) {
		switch (vista) {
		case "info":
			this.vista = "info";
			break;

		case "consultas":
			consultasPorPaciente();
			this.vista = "consultas";
			break;

		case "diagnosticos":

			diagnosticosPorPaciente();
			this.vista = "diagnosticos";
			break;

		case "medicamentos":
			medicamentosPorPaciente();
			this.vista = "medicamentos";
			break;

		case "examenes":
			examenesPorPaciente();
			this.vista = "examenes";
			break;

		default:
			this.vista = "info";
			break;
		}
	}

	public void seleccionarMedicamento(MedicamentoDTO medicamento) {
		System.out.println(medicamento.getIdConsulta());
		this.medicamentoSeleccionado = medicamento;
	}

	public MedicamentoDTO getMedicamentoSeleccionado() {
		return medicamentoSeleccionado;
	}

	public void setMedicamentoSeleccionado(MedicamentoDTO medicamentoSeleccionado) {
		this.medicamentoSeleccionado = medicamentoSeleccionado;
	}

	public void seleccionarExamen(ExamenDTO examen) {
		System.out.println(examen.getIdConsulta());
		this.examenSeleccionado = examen;
	}

	public List<ExamenDTO> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<ExamenDTO> examenes) {
		this.examenes = examenes;
	}

	public ExamenDTO getExamenSeleccionado() {
		return examenSeleccionado;
	}

	public void setExamenSeleccionado(ExamenDTO examenSeleccionado) {
		this.examenSeleccionado = examenSeleccionado;
	}

	public ExamenDAO getExDAO() {
		return exDAO;
	}

	public void setExDAO(ExamenDAO exDAO) {
		this.exDAO = exDAO;
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

	public void setConsultaSeleccionada(ConsultaDTO consultaSeleccionada) {
		this.consultaSeleccionada = consultaSeleccionada;
	}

	public void seleccionarConsulta(ConsultaDTO consulta) {
		System.out.println(consulta.getIdConsulta());
		this.consultaSeleccionada = consulta;
	}

	public ConsultaDTO getConsultaSeleccionada() {
		return consultaSeleccionada;
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

	public List<DiagnosticoDTO> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<DiagnosticoDTO> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public DiagnosticoDAO getdDAO() {
		return dDAO;
	}

	public void setdDAO(DiagnosticoDAO dDAO) {
		this.dDAO = dDAO;
	}

	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public MedicamentoDAO getmDAO() {
		return mDAO;
	}

	public void setmDAO(MedicamentoDAO mDAO) {
		this.mDAO = mDAO;
	}

}
