package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.PacienteDTO;
import co.edu.unbosque.beans.model.PerfilClinicoDTO;
import co.edu.unbosque.beans.model.TopMedicosDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PacienteDAO {

	private List<PacienteDTO> listaPacientes;

	public List<PacienteDTO> getPacientesPorMedico(Integer id) {
		listaPacientes = ExternalHTTPRequestHandler
				.doGetPacientesPorMedico("http://localhost:8082/consulta/pacientes-vigentes?idMedico=" + id);
		return listaPacientes;
	}

	public PacienteDTO findByUsername(String username) {
		return ExternalHTTPRequestHandler
				.doGetPaciente("http://localhost:8082/paciente/obtenerpordocumento?usuario=" + username);
	}

	public PerfilClinicoDTO getPerfilClinico(Integer id) {
		return ExternalHTTPRequestHandler.doGetPerfilClinico("http://localhost:8082/paciente/perfilclinico?id="+ id);
	}

	public List<PacienteDTO> getPacientesPorEps(String nombreEps) {
		return ExternalHTTPRequestHandler
				.doGetPacientesPorMedico("http://localhost:8082/paciente/obtenerporeps?nombreEps=" + nombreEps);
	}
}
