package co.edu.unbosque.loom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.dto.UsuarioDTO;
import co.edu.unbosque.loom.service.PacienteService;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = { "*" })
public class PacienteController {
	
	@Autowired
	private PacienteService pacienteServ;
	
	public PacienteController() {
		pacienteServ = new PacienteService();
	}
	
	@GetMapping("/obtenerpordocumento")
	public ResponseEntity<PacienteDTO> obtenerPorDocumento(@RequestParam String usuario) {
		PacienteDTO encontrado = pacienteServ.buscarPorUsername(usuario);

		if (encontrado != null) {

			return new ResponseEntity<>(encontrado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
