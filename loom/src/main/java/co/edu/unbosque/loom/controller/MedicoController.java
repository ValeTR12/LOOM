package co.edu.unbosque.loom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.loom.dto.ConsultaDTO;
import co.edu.unbosque.loom.dto.MedicoDTO;
import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.model.Consulta;
import co.edu.unbosque.loom.service.MedicoService;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = { "*" })
public class MedicoController {

	@Autowired
	private MedicoService medicoServ;
	

	@GetMapping("/buscarporusuario")
	public ResponseEntity<MedicoDTO> getConsultas(@RequestParam String username) {

		MedicoDTO medico = medicoServ.buscarPorUsername(username);

	    if (medico == null) {
	        return new ResponseEntity<>(medico, HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(medico, HttpStatus.OK);
	}

	@GetMapping("/buscarporid")
	public ResponseEntity<MedicoDTO> getUsuario(@RequestParam Integer id) {

		MedicoDTO medico = medicoServ.buscarPorIdUsuario(id);

	    if (medico == null) {
	        return new ResponseEntity<>(medico, HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(medico, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerporeps")
	public ResponseEntity<List<MedicoDTO>> mostrarPacientesPorEps(@RequestParam String nombreEps) {
		List<MedicoDTO> p = medicoServ.buscarPorEps(nombreEps);
		if (p.isEmpty()) {
			return new ResponseEntity<>(p, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(p, HttpStatus.OK);
		}
	}
}
