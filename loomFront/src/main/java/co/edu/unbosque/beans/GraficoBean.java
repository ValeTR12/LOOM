package co.edu.unbosque.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.beans.model.EpsCargaMensualDTO;
import co.edu.unbosque.beans.model.MedicamentoPorEpsDTO;
import co.edu.unbosque.beans.model.MedicoDTO;
import co.edu.unbosque.beans.model.PacienteDTO;
import co.edu.unbosque.beans.model.PerfilClinicoDTO;
import co.edu.unbosque.beans.model.TopEnfermedadesDTO;
import co.edu.unbosque.beans.model.TopMedicosDTO;
import co.edu.unbosque.model.persistence.ConsultaDAO;
import co.edu.unbosque.model.persistence.DiagnosticoDAO;
import co.edu.unbosque.model.persistence.MedicamentoDAO;
import co.edu.unbosque.model.persistence.MedicoDAO;
import co.edu.unbosque.model.persistence.PacienteDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

@Named("graficoBean")
@ViewScoped
public class GraficoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EpsCargaMensualDTO> listaEps = new ArrayList<>();
	private List<TopMedicosDTO> listaTopMedicos = new ArrayList<>();
	private List<TopEnfermedadesDTO> listaTopEnfermedades = new ArrayList<>();
	private List<MedicamentoPorEpsDTO> listaTopMedicamentos = new ArrayList<>();
	private PerfilClinicoDTO perfilClinico = new PerfilClinicoDTO();
	private Integer idPaciente;
	private List<PacienteDTO> listaPacientes = new ArrayList<>();
	private String nombreEps;
	private List<MedicoDTO> listaMedicos = new ArrayList<>();
	private String nombreEpsM;
	
	@Inject
	private ConsultaDAO cDAO;
	@Inject
	private DiagnosticoDAO dDAO;
	@Inject
	private MedicamentoDAO mDAO;
	@Inject
	private PacienteDAO pDAO;
	@Inject
	private MedicoDAO meDAO;
	
	@PostConstruct
	public void init() {
		listaEps = cDAO.getEpsCargaMensual();
		listaTopMedicos = cDAO.getTopMedicos();
		listaTopEnfermedades = dDAO.getTopEnfermedades();
		listaTopMedicamentos = mDAO.getMedicamentoPorEps();
	}
	
	public void cargarPerfilClinico() {
		perfilClinico = pDAO.getPerfilClinico(idPaciente);
	}
	
	public void cargarListaPacientes() {
		listaPacientes = pDAO.getPacientesPorEps(nombreEps);
	}
	
	public void cargarListaMedicos() {
		listaMedicos = meDAO.getMedicosPorEps(nombreEpsM);
	}
	
	public String getListaEpsJSON() {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(listaEps);
	    } catch (Exception e) {
	        return "[]";
	    }
	}
	
	public String getListaTopMedicosJSON() {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(listaTopMedicos);
	    } catch (Exception e) {
	        return "[]";
	    }
	}
	
	public String getListaTopEnfermedadesJSON() {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(listaTopEnfermedades);
	    } catch (Exception e) {
	        return "[]";
	    }
	}
	
	public String getListaTopMedicamentosJSON() {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.writeValueAsString(listaTopMedicamentos);
	    } catch (Exception e) {
	        return "[]";
	    }
	}

	public void verificarSesion() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    Object usuarioLogueado = context.getExternalContext().getSessionMap().get("usuarioLogueado");
	    String paginaSolicitada = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();

	    try {
	        if (usuarioLogueado == null) {
	            // No hay usuario logueado → siempre index
	            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/index.xhtml");
	            context.responseComplete();
	            return;
	        }

	        String paginaActual = (String) context.getExternalContext().getSessionMap().get("paginaActual");

	        // Inicializa paginaActual si es null
	        if (paginaActual == null) {
	            paginaActual = paginaSolicitada;
	            context.getExternalContext().getSessionMap().put("paginaActual", paginaActual);
	        }

	        // ADMIN puede ir a cualquier página, pero mantiene la lógica de páginaActual
	        if ("ADMIN".equals(usuarioLogueado)) {
	            if (!paginaSolicitada.equals(paginaActual)) {
	                context.getExternalContext().redirect(paginaActual);
	                context.responseComplete();
	            }
	            return;
	        }

	        // Usuario normal (paciente o médico)
	        if (!paginaSolicitada.contains("paciente.xhtml") && !paginaSolicitada.contains("medico.xhtml")) {
	            // Si intenta entrar a otra página que no sea paciente o médico → redirige a la página actual
	            context.getExternalContext().redirect(paginaActual);
	            context.responseComplete();
	        } else {
	            // Actualiza paginaActual si está navegando dentro de paciente o médico
	            context.getExternalContext().getSessionMap().put("paginaActual", paginaSolicitada);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	
	public List<MedicoDTO> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<MedicoDTO> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public String getNombreEpsM() {
		return nombreEpsM;
	}

	public void setNombreEpsM(String nombreEpsM) {
		this.nombreEpsM = nombreEpsM;
	}

	public String getNombreEps() {
		return nombreEps;
	}

	public void setNombreEps(String nombreEps) {
		this.nombreEps = nombreEps;
	}

	public List<PacienteDTO> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<PacienteDTO> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}
	
	public PerfilClinicoDTO getPerfilClinico() {
		return perfilClinico;
	}

	public void setPerfilClinico(PerfilClinicoDTO perfilClinico) {
		this.perfilClinico = perfilClinico;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public List<EpsCargaMensualDTO> getListaEps() {
		return listaEps;
	}

	public void setListaEps(List<EpsCargaMensualDTO> listaEps) {
		this.listaEps = listaEps;
	}
	
	public List<TopMedicosDTO> getListaTopMedicos() {
		return listaTopMedicos;
	}

	public void setListaTopMedicos(List<TopMedicosDTO> listaTopMedicos) {
		this.listaTopMedicos = listaTopMedicos;
	}

	public List<TopEnfermedadesDTO> getListaTopEnfermedades() {
		return listaTopEnfermedades;
	}

	public void setListaTopEnfermedades(List<TopEnfermedadesDTO> listaTopEnfermedades) {
		this.listaTopEnfermedades = listaTopEnfermedades;
	}
	
	public List<MedicamentoPorEpsDTO> getListaTopMedicamentos() {
		return listaTopMedicamentos;
	}

	public void setListaTopMedicamentos(List<MedicamentoPorEpsDTO> listaTopMedicamentos) {
		this.listaTopMedicamentos = listaTopMedicamentos;
	}
}
