package co.edu.unbosque.loom.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.loom.dto.ExamenDTO;
import co.edu.unbosque.loom.model.Examen;
import co.edu.unbosque.loom.repository.ExamenRepository;


@Service
public class ExamenService {

	@Autowired
	private ExamenRepository examenRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	public ExamenService(ExamenRepository examenRepo, ModelMapper modelMapper) {
		super();
		this.examenRepo = examenRepo;
		this.modelMapper = modelMapper;
	}


	public List<ExamenDTO> mostrarPorPaciente(String username) {
		List<Examen> entityList = (List<Examen>) examenRepo.findExamenesByPaciente(username);
		List<ExamenDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ExamenDTO dto = modelMapper.map(entity, ExamenDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}
}

