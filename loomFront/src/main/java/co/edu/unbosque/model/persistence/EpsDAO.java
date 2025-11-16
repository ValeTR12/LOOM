package co.edu.unbosque.model.persistence;

import co.edu.unbosque.beans.model.EpsDTO;
import co.edu.unbosque.beans.model.MedicoDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EpsDAO {
	
	public EpsDTO findById(Integer id) {
		return ExternalHTTPRequestHandler.doGetEps("http://localhost:8082/eps/buscarporid?id="+ id);
	}

}
