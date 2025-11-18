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

import co.edu.unbosque.loom.dto.MedicamentoDTO;
import co.edu.unbosque.loom.dto.MedicamentoPorEpsDTO;
import co.edu.unbosque.loom.dto.TopMedicosDTO;
import co.edu.unbosque.loom.service.MedicamentoService;

@RestController
@RequestMapping("/medicamento")
@CrossOrigin(origins = { "*" })
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService medicamentoServ;

	
	
	@GetMapping("/mostrarporpaciente")
	public ResponseEntity<List<MedicamentoDTO>> mostrarPorPaciente(@RequestParam String documento) {
		List<MedicamentoDTO> medicamentos = medicamentoServ.mostrarPorPaciente(documento);

		if (medicamentos.isEmpty()) {
			return new ResponseEntity<>(medicamentos, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(medicamentos, HttpStatus.OK);
		}
	}
	
	@GetMapping("/topmedicamentos")
	public ResponseEntity<List<MedicamentoPorEpsDTO>> mostrarTopMedicamentos() {
		List<MedicamentoPorEpsDTO> top = medicamentoServ.obtenerTopMedicamentos();

		if (top.isEmpty()) {
			return new ResponseEntity<>(top, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(top, HttpStatus.OK);
		}
	}

}

