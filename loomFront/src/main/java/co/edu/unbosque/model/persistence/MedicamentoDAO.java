package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.MedicamentoDTO;
import co.edu.unbosque.beans.model.MedicamentoPorEpsDTO;
import co.edu.unbosque.beans.model.TopMedicosDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicamentoDAO {
	
	private List<MedicamentoDTO> listaMedicamentos;

	public List<MedicamentoDTO> getMedicamentosPorPaciente(String username) {
		listaMedicamentos = ExternalHTTPRequestHandler.doGetMedicamentosPorPaciente("http://localhost:8082/medicamento/mostrarporpaciente?documento="+ username);
		return listaMedicamentos;
	}
	
	public List<MedicamentoPorEpsDTO> getMedicamentoPorEps() {
		return ExternalHTTPRequestHandler.doGetMedicamentoPorEps("http://localhost:8082/medicamento/topmedicamentos");
	}

}
