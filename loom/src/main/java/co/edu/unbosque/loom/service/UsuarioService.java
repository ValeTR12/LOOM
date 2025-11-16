package co.edu.unbosque.loom.service;

import java.util.ArrayList;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.dto.UsuarioDTO;
import co.edu.unbosque.loom.model.Paciente;
import co.edu.unbosque.loom.model.Usuario;
import co.edu.unbosque.loom.repository.UsuarioRepository;
import co.edu.unbosque.loom.util.AESUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	public UsuarioService() {

	}
	
	public UsuarioService(UsuarioRepository userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		super();
		this.usuarioRepo = userRepo;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	public ArrayList<UsuarioDTO> mostrarTodo() {
		ArrayList<Usuario> entityList = (ArrayList<Usuario>) usuarioRepo.findAll();
		ArrayList<UsuarioDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			UsuarioDTO dto = modelMapper.map(entity, UsuarioDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}


	public int create(UsuarioDTO data) {

	    Usuario existente = usuarioRepo.findByUsername(data.getUsername()).get();

	    // Si no existe, devolvemos error (no afiliado)
	    if (existente == null) {
	        return 1;
	    }

	    // Actualizar contraseña
	    existente.setContrasena(passwordEncoder.encode(data.getContrasena()));

	    usuarioRepo.save(existente);
	    return 0;
	}



	public boolean findDocumentoAlreadyTaken(Usuario newUser) {
		Optional<Usuario> found = usuarioRepo.findByDocumento(newUser.getDocumento());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public UsuarioDTO buscarPorDocumento(String documento) {
		return usuarioRepo.findByDocumento(documento).map(user -> {
			UsuarioDTO dto = modelMapper.map(user, UsuarioDTO.class);
			return dto;
		}).orElse(null);

	}
	
	public boolean findUsernameAlreadyTaken(Usuario newUser) {
		Optional<Usuario> found = usuarioRepo.findByUsername(newUser.getUsername());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public UsuarioDTO buscarPorUsername(String documento) {
		return usuarioRepo.findByUsername(documento).map(user -> {
			UsuarioDTO dto = modelMapper.map(user, UsuarioDTO.class);
			return dto;
		}).orElse(null);
	}
	
	public UsuarioDTO buscarPorId(Integer Id) {
		return usuarioRepo.findByIdUsuario(Id).map(user -> {
			UsuarioDTO dto = modelMapper.map(user, UsuarioDTO.class);
			return dto;
		}).orElse(null);
	}
}