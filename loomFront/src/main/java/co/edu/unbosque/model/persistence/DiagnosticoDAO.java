package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.DiagnosticoDTO;
import co.edu.unbosque.beans.model.TopEnfermedadesDTO;
import co.edu.unbosque.beans.model.TopMedicosDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DiagnosticoDAO {
	
	private List<DiagnosticoDTO> listaDiagnosticos;

	public List<DiagnosticoDTO> getDiagnosticosPorPaciente(String username) {
		listaDiagnosticos = ExternalHTTPRequestHandler.doGetDiagnosticosPorPaciente("http://localhost:8082/diagnostico/mostrarporpaciente?documento="+ username);
		return listaDiagnosticos;
	}
	
	public List<TopEnfermedadesDTO> getTopEnfermedades() {
		return ExternalHTTPRequestHandler.doGetTopEnfermedades("http://localhost:8082/diagnostico/topenfermedades");
	}

}
