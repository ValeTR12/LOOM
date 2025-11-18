package co.edu.unbosque.loom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.loom.dto.ConsultaDTO;
import co.edu.unbosque.loom.dto.EpsCargaMensualDTO;
import co.edu.unbosque.loom.dto.EpsCargaMensualView;
import co.edu.unbosque.loom.dto.PacienteDTO;
import co.edu.unbosque.loom.dto.TopEnfermedadesDTO;
import co.edu.unbosque.loom.dto.TopEnfermedadesView;
import co.edu.unbosque.loom.dto.TopMedicosDTO;
import co.edu.unbosque.loom.dto.TopMedicosView;
import co.edu.unbosque.loom.dto.UsuarioDTO;
import co.edu.unbosque.loom.model.Consulta;
import co.edu.unbosque.loom.model.Paciente;
import co.edu.unbosque.loom.model.Usuario;
import co.edu.unbosque.loom.repository.ConsultaRepository;
import co.edu.unbosque.loom.repository.PacienteRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepo;
	@Autowired
	private PacienteRepository pacienteRepo;

	@Autowired
	private ModelMapper modelMapper;

	public ConsultaService(ConsultaRepository consultaRepo, ModelMapper modelMapper) {
		super();
		this.consultaRepo = consultaRepo;
		this.modelMapper = modelMapper;
	}
	
	public ConsultaDTO buscarPorId(Integer id) {
		return consultaRepo.findByIdConsulta(id).map(user -> {
			ConsultaDTO dto = modelMapper.map(user, ConsultaDTO.class);
			return dto;
		}).orElse(null);

	}
	
	public boolean findUsernameAlreadyTaken(Consulta newUser) {
		Optional<Consulta> found = consultaRepo.findByIdConsulta(newUser.getIdConsulta());
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<ConsultaDTO> mostrarTodo() {
		ArrayList<Consulta> entityList = (ArrayList<Consulta>) consultaRepo.findAll();
		ArrayList<ConsultaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ConsultaDTO dto = modelMapper.map(entity, ConsultaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}
	
	public List<ConsultaDTO> mostrarPorPaciente(String username) {
		List<Consulta> entityList = (List<Consulta>) consultaRepo.findByPaciente(username);
		List<ConsultaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ConsultaDTO dto = modelMapper.map(entity, ConsultaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}
	
	public List<ConsultaDTO> obtenerConsultasPacientesVigentes(Integer idMedico) {

        // 1. Obtener pacientes con permiso vigente
        List<Integer> pacientes = consultaRepo
                .findPacientesConPermisoVigente(idMedico);

        if (pacientes.isEmpty()) {
            return List.of();
        }

        List<Consulta> entityList = (List<Consulta>) consultaRepo.findConsultasByPacientes(pacientes);
		List<ConsultaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ConsultaDTO dto = modelMapper.map(entity, ConsultaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
    }
	
	public List<PacienteDTO> obtenerPacientesVigentes(Integer idMedico) {

        // 1. Obtener pacientes con permiso vigente
        List<Integer> pacientes = consultaRepo
                .findPacientesConPermisoVigente(idMedico);
        List<PacienteDTO> pacientesDTO = new ArrayList<>();

        if (pacientes.isEmpty()) {
            return List.of();
        }
        for (Integer integer : pacientes) {
        	PacienteDTO paciente = modelMapper.map(pacienteRepo.findByIdUsuario(integer).get(), PacienteDTO.class);
        	pacientesDTO.add(paciente);
        	
		}

		return pacientesDTO;
    }
	
	public List<EpsCargaMensualDTO> obtenerEpsCargaMensual() {
		List<EpsCargaMensualDTO> dtos = new ArrayList<>();
		List<EpsCargaMensualView> proyecciones = consultaRepo.findEpsCargaMensual();
		for (EpsCargaMensualView proyeccion : proyecciones) {
			dtos.add(toDTOEpsCargaMensual(proyeccion));
		}

		return dtos;
	}
	
	private EpsCargaMensualDTO toDTOEpsCargaMensual(EpsCargaMensualView view) {
		EpsCargaMensualDTO dto = new EpsCargaMensualDTO();
		dto.setMes(view.getMes());
		dto.setNombre(view.getNombre());
		dto.setTotalConsultas(view.getTotalConsultas());
	    return dto;
	}
	
	public List<TopMedicosDTO> obtenerTopMedicos() {
		List<TopMedicosDTO> dtos = new ArrayList<>();
		List<TopMedicosView> proyecciones = consultaRepo.findTopMedicosUltimoAno();
		for (TopMedicosView proyeccion : proyecciones) {
			dtos.add(toDTOTopMedicos(proyeccion));
		}

		return dtos;
	}
	
	private TopMedicosDTO toDTOTopMedicos(TopMedicosView view) {
		TopMedicosDTO dto = new TopMedicosDTO();
		dto.setIdMedico(view.getIdMedico());
		dto.setNombreCompleto(view.getNombreCompleto());
		dto.setTotalConsultas(view.getTotalConsultas());
	    return dto;
	}
}
