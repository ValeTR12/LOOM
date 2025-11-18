package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.ExamenDTO;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ExamenDAO {
	
	private List<ExamenDTO> listaExamenes;

	public List<ExamenDTO> getExamenesPorPaciente(String username) {
		listaExamenes = ExternalHTTPRequestHandler.doGetExamenesPorPaciente("http://localhost:8082/examen/mostrarporpaciente?documento="+ username);
		return listaExamenes;
	}

}