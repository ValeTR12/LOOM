package co.edu.unbosque.loom.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.edu.unbosque.loom.service.UsuarioService;
import co.edu.unbosque.loom.dto.UsuarioDTO;
import co.edu.unbosque.loom.model.Rol;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	// LOGIN
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioDTO loginRequest) {
		try {
			UsuarioDTO usuario = usuarioService.buscarPorUsername(loginRequest.getUsername());
			
			if (usuario == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado o no afiliado");
			}

			// Primera vez → no tiene contraseña
			if (usuario.getContrasena() == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body("Primera vez. Regístrese para crear su contraseña.");
			}

			// ✅ Validar contraseña encriptada
			if (!passwordEncoder.matches(loginRequest.getContrasena(), usuario.getContrasena())&& loginRequest.getUsername().equals(usuario.getUsername())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
			}
			
			System.out.println("Usted entro como:" + usuario.getRol().getNombreRol() + "\nSu nombre es:" + usuario.getPrimerNombre());

			// ✅ Login exitoso → devolver rol o token
			return ResponseEntity.ok(new Rol(usuario.getRol().getNombreRol(), usuario.getRol().getDescripcion()));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
		}
	}

	// REGISTER
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UsuarioDTO registerRequest) {

		UsuarioDTO existente = usuarioService.buscarPorUsername(registerRequest.getUsername());
		
		if (existente == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No estás afiliado a nuestras EPS aliadas.");
		}

		if (existente.getContrasena() != null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ya se encuentra registrado.");
		}

		int result = usuarioService.create(registerRequest);

		if (result != 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error actualizando contraseña");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso. Contraseña actualizada.");
	}

	// MOSTRAR
	@GetMapping("/mostrartodo")
	public ResponseEntity<ArrayList<UsuarioDTO>> mostrarTodo() {
		ArrayList<UsuarioDTO> usuarios = usuarioService.mostrarTodo();

		if (usuarios.isEmpty()) {
			return new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
	}
	
	@GetMapping("/obtener")
	public ResponseEntity<UsuarioDTO> obtenerPorDocumento(@RequestParam String usuario) {
		UsuarioDTO encontrado = usuarioService.buscarPorDocumento(usuario);

		if (encontrado != null) {

			return new ResponseEntity<>(encontrado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/obtenerporid")
	public ResponseEntity<UsuarioDTO> obtenerPorIdUsuario(@RequestParam Integer id) {
		UsuarioDTO encontrado = usuarioService.buscarPorId(id);

		if (encontrado != null) {

			return new ResponseEntity<>(encontrado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
