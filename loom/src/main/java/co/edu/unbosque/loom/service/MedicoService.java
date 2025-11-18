package co.edu.unbosque.loom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.loom.dto.ConsultaDTO;
import co.edu.unbosque.loom.dto.MedicamentoPorEpsDTO;
import co.edu.unbosque.loom.dto.MedicoDTO;
import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.dto.TopMedicosDTO;
import co.edu.unbosque.loom.dto.TopMedicosView;
import co.edu.unbosque.loom.model.Consulta;
import co.edu.unbosque.loom.model.Medico;
import co.edu.unbosque.loom.model.Paciente;
import co.edu.unbosque.loom.repository.ConsultaRepository;
import co.edu.unbosque.loom.repository.MedicoRepository;

@Service
public class MedicoService {


	@Autowired
	private MedicoRepository medicoRepo;
	

	@Autowired
	private ModelMapper modelMapper;


	public MedicoService() {
		// TODO Auto-generated constructor stub
	}

	public MedicoService(MedicoRepository medicoRepo, ModelMapper modelMapper) {
		super();
		this.medicoRepo = medicoRepo;
		this.modelMapper = modelMapper;
	}

	public ArrayList<MedicoDTO> mostrarTodo() {
		ArrayList<Medico> entityList = (ArrayList<Medico>) medicoRepo.findAll();
		ArrayList<MedicoDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			MedicoDTO dto = modelMapper.map(entity, MedicoDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public boolean findUsernameAlreadyTaken(Medico newUser) {
		Optional<Medico> found = medicoRepo.findByUsername(newUser.getUsername());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public MedicoDTO buscarPorUsername(String documento) {
		return medicoRepo.findByUsername(documento).map(user -> {
			MedicoDTO dto = modelMapper.map(user, MedicoDTO.class);
			return dto;
		}).orElse(null);

	}
	
	public MedicoDTO buscarPorIdUsuario(Integer id) {
		return medicoRepo.findByIdUsuario(id).map(user -> {
			MedicoDTO dto = modelMapper.map(user, MedicoDTO.class);
			return dto;
		}).orElse(null);

	}
	
	public List<MedicoDTO> buscarPorEps(String nombreEps) {
		ArrayList<Medico> entityList = (ArrayList<Medico>) medicoRepo.findMedicosPorEps(nombreEps);
		ArrayList<MedicoDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			MedicoDTO dto = modelMapper.map(entity, MedicoDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}
}
