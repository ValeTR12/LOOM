package co.edu.unbosque.loom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.loom.dto.EpsDTO;
import co.edu.unbosque.loom.service.EpsService;

@RestController
@RequestMapping("/eps")
@CrossOrigin(origins = { "*" })
public class EpsController {
	
	@Autowired
	private EpsService medicoServ;
	

	@GetMapping("/buscarporid")
	public ResponseEntity<EpsDTO> getConsultas(@RequestParam Integer id) {

		EpsDTO eps = medicoServ.buscarPorId(id);

	    if (eps == null) {
	        return new ResponseEntity<>(eps, HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(eps, HttpStatus.OK);
	}


}
