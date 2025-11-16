package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.PacienteDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PacienteDAO {

	private List<PacienteDTO> listaPacientes;

	public List<PacienteDTO> getPacientesPorMedico(Integer id) {
		System.out.println("Buscando consultas para ID usuario: " + id);
		listaPacientes = ExternalHTTPRequestHandler
				.doGetPacientesPorMedico("http://localhost:8082/consulta/pacientes-vigentes?idMedico=" + id);
		System.out.println(listaPacientes.size() + "tam");
		return listaPacientes;
	}

	public PacienteDTO findByUsername(String username) {
		return ExternalHTTPRequestHandler
				.doGetPaciente("http://localhost:8082/paciente/obtenerpordocumento?usuario=" + username);
	}

}
