package co.edu.unbosque.loom.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.model.Paciente;
import co.edu.unbosque.loom.repository.PacienteRepository;

@Service
public class PacienteService {
	
	
	@Autowired
	private PacienteRepository pacienteRepo;

	@Autowired
	private ModelMapper modelMapper;
	

	public PacienteService() {
		// TODO Auto-generated constructor stub
	}

	public PacienteService(PacienteRepository pacienteRepo, ModelMapper modelMapper) {
		super();
		this.pacienteRepo = pacienteRepo;
		this.modelMapper = modelMapper;
	}
	
	public ArrayList<PacienteDTO> mostrarTodo() {
		ArrayList<Paciente> entityList = (ArrayList<Paciente>) pacienteRepo.findAll();
		ArrayList<PacienteDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			PacienteDTO dto = modelMapper.map(entity, PacienteDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public boolean findUsernameAlreadyTaken(Paciente newUser) {
		Optional<Paciente> found = pacienteRepo.findByUsername(newUser.getUsername());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public PacienteDTO buscarPorUsername(String documento) {
		return pacienteRepo.findByUsername(documento).map(user -> {
			PacienteDTO dto = modelMapper.map(user, PacienteDTO.class);
			return dto;
		}).orElse(null);

	}
	
	public PacienteDTO buscarPorId(Integer Id) {
		return pacienteRepo.findByIdUsuario(Id).map(user -> {
			PacienteDTO dto = modelMapper.map(user, PacienteDTO.class);
			return dto;
		}).orElse(null);
	}

}

