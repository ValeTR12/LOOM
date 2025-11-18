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
import co.edu.unbosque.loom.dto.DiagnosticoDTO;
import co.edu.unbosque.loom.dto.TopEnfermedadesDTO;
import co.edu.unbosque.loom.service.DiagnosticoService;

@RestController
@RequestMapping("/diagnostico")
@CrossOrigin(origins = { "*" })
public class DiagnosticoController {
	
	@Autowired
	private DiagnosticoService diagnosticoServ;

	public DiagnosticoController(DiagnosticoService diagnosticoServ) {
		super();
		this.diagnosticoServ = diagnosticoServ;
	}
	
	@GetMapping("/mostrarporpaciente")
	public ResponseEntity<List<DiagnosticoDTO>> mostrarPorPaciente(@RequestParam String documento) {
		List<DiagnosticoDTO> diagnosticos = diagnosticoServ.mostrarPorPaciente(documento);

		if (diagnosticos.isEmpty()) {
			return new ResponseEntity<>(diagnosticos, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
		}
	}

	@GetMapping("/topenfermedades")
	public ResponseEntity<List<TopEnfermedadesDTO>> mostrarTopEnfermedades() {
		List<TopEnfermedadesDTO> top = diagnosticoServ.obtenerTopEnfermedades();

		if (top.isEmpty()) {
			return new ResponseEntity<>(top, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(top, HttpStatus.OK);
		}
	}
}
