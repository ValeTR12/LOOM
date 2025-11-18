package co.edu.unbosque.model.persistence;

import java.util.List;
import co.edu.unbosque.beans.model.MedicoDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicoDAO {

	public MedicoDTO findByUsername(String username) {
		return ExternalHTTPRequestHandler.doGetMedico("http://localhost:8082/medico/buscarporusuario?username="+ username);
	}
	
	public MedicoDTO findById(Integer id) {
		return ExternalHTTPRequestHandler.doGetMedico("http://localhost:8082/medico/buscarporid?id="+ id);
	}	
	
	public List<MedicoDTO> getMedicosPorEps(String nombreEps) {
		return ExternalHTTPRequestHandler
				.doGetMedicosPorEps("http://localhost:8082/medico/obtenerporeps?nombreEps=" + nombreEps);
	}
}
