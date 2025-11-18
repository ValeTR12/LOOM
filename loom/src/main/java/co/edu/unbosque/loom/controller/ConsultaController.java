package co.edu.unbosque.loom.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.loom.service.ConsultaService;
import co.edu.unbosque.loom.dto.ConsultaDTO;
import co.edu.unbosque.loom.dto.EpsCargaMensualDTO;
import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.dto.TopEnfermedadesDTO;
import co.edu.unbosque.loom.dto.TopMedicosDTO;
import co.edu.unbosque.loom.model.Consulta;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = { "*" })
public class ConsultaController {
	
	@Autowired
	private ConsultaService consultaServ;

	public ConsultaController(ConsultaService consultaServ) {
		super();
		this.consultaServ = consultaServ;
	}

	@GetMapping("/mostrartodo")
	public ResponseEntity<ArrayList<ConsultaDTO>> mostrarTodo() {
		ArrayList<ConsultaDTO> usuarios = consultaServ.mostrarTodo();

		if (usuarios.isEmpty()) {
			return new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
	}

	@GetMapping("/mostrarporpaciente")
	public ResponseEntity<List<ConsultaDTO>> mostrarPorPaciente(@RequestParam String documento) {
		List<ConsultaDTO> usuarios = consultaServ.mostrarPorPaciente(documento);

		if (usuarios.isEmpty()) {
			return new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
	}
	
	@GetMapping("/consultas-vigentes")
	public ResponseEntity<List<ConsultaDTO>> getConsultas(@RequestParam Integer idMedico) {

	    List<ConsultaDTO> consultas = consultaServ.obtenerConsultasPacientesVigentes(idMedico);

	    if (consultas.isEmpty()) {
	        return new ResponseEntity<>(consultas, HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(consultas, HttpStatus.OK);
	}
	
	@GetMapping("/pacientes-vigentes")
	public ResponseEntity<List<PacienteDTO>> getPacientes(@RequestParam Integer idMedico) {

	    List<PacienteDTO> pacientes = consultaServ.obtenerPacientesVigentes(idMedico);

	    if (pacientes.isEmpty()) {
	        return new ResponseEntity<>(pacientes, HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(pacientes, HttpStatus.OK);
	}
	
	@GetMapping("/epscargamensual")
	public ResponseEntity<List<EpsCargaMensualDTO>> mostrarEpsCargaMensual() {
		List<EpsCargaMensualDTO> eps = consultaServ.obtenerEpsCargaMensual();

		if (eps.isEmpty()) {
			return new ResponseEntity<>(eps, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(eps, HttpStatus.OK);
		}
	}
	
	@GetMapping("/topmedicos")
	public ResponseEntity<List<TopMedicosDTO>> mostrarTopMedicos() {
		List<TopMedicosDTO> top = consultaServ.obtenerTopMedicos();

		if (top.isEmpty()) {
			return new ResponseEntity<>(top, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(top, HttpStatus.OK);
		}
	}
}
