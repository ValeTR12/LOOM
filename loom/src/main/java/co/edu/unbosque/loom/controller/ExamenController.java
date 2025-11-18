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

import co.edu.unbosque.loom.dto.ExamenDTO;
import co.edu.unbosque.loom.service.ExamenService;


@RestController
@RequestMapping("/examen")
@CrossOrigin(origins = { "*" })
public class ExamenController {
	
	@Autowired
	private ExamenService medicamentoServ;

	
	
	@GetMapping("/mostrarporpaciente")
	public ResponseEntity<List<ExamenDTO>> mostrarPorPaciente(@RequestParam String documento) {
		List<ExamenDTO> examenes = medicamentoServ.mostrarPorPaciente(documento);

		if (examenes.isEmpty()) {
			return new ResponseEntity<>(examenes, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(examenes, HttpStatus.OK);
		}
	}

}

