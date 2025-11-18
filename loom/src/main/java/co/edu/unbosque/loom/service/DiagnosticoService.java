package co.edu.unbosque.loom.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.loom.dto.ConsultaDTO;
import co.edu.unbosque.loom.dto.DiagnosticoDTO;
import co.edu.unbosque.loom.dto.TopEnfermedadesDTO;
import co.edu.unbosque.loom.dto.TopEnfermedadesView;
import co.edu.unbosque.loom.model.Consulta;
import co.edu.unbosque.loom.model.Diagnostico;
import co.edu.unbosque.loom.repository.DiagnosticoRepository;

@Service
public class DiagnosticoService {

	@Autowired
	private DiagnosticoRepository diagnosticoRepo;
	@Autowired
	private ModelMapper modelMapper;

	public DiagnosticoService(DiagnosticoRepository diagnosticoRepo, ModelMapper modelMapper) {
		super();
		this.diagnosticoRepo = diagnosticoRepo;
		this.modelMapper = modelMapper;
	}

	public List<DiagnosticoDTO> mostrarPorPaciente(String username) {
		List<Diagnostico> entityList = (List<Diagnostico>) diagnosticoRepo.findDiagnosticosByPaciente(username);
		List<DiagnosticoDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			DiagnosticoDTO dto = modelMapper.map(entity, DiagnosticoDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	public List<TopEnfermedadesDTO> obtenerTopEnfermedades() {
		List<TopEnfermedadesDTO> dtos = new ArrayList<>();
		List<TopEnfermedadesView> proyecciones = diagnosticoRepo.findTop5Enfermedades();
		for (TopEnfermedadesView proyeccion : proyecciones) {
			dtos.add(toDTOTopEnfermedades(proyeccion));
		}

		return dtos;
	}
	
	private TopEnfermedadesDTO toDTOTopEnfermedades(TopEnfermedadesView view) {
	    TopEnfermedadesDTO dto = new TopEnfermedadesDTO();
	    dto.setCodigoCie10(view.getCodigoCie10());
	    dto.setDescripcion(view.getDescripcion());
	    dto.setTotal(view.getTotal());
	    return dto;
	}

}
