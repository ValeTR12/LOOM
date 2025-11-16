package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.ConsultaDTO;
import co.edu.unbosque.beans.model.MedicoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
public class ConsultaDAO {

	private List<ConsultaDTO> listaConsultas;

	public List<ConsultaDTO> getConsultasPorPaciente(String username) {
		System.out.println("Buscando consultas para ID usuario: " + username);
		listaConsultas = ExternalHTTPRequestHandler.doGetPacientes("http://localhost:8082/consulta/mostrarporpaciente?documento="+ username);
		System.out.println(listaConsultas.size() + "tam");
		return listaConsultas;
	}
	
	public List<ConsultaDTO> getConsultasPorPacientePorMedico(Integer id) {
		System.out.println("Buscando consultas para ID usuario: " + id);
		listaConsultas = ExternalHTTPRequestHandler.doGetPacientes("http://localhost:8082/consulta/consultas-vigentes?idMedico="+ id);
		System.out.println(listaConsultas.size() + "tam");
		return listaConsultas;
	}

}
