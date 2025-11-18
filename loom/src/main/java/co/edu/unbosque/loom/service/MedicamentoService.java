package co.edu.unbosque.loom.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.loom.dto.MedicamentoDTO;
import co.edu.unbosque.loom.dto.MedicamentoPorEpsDTO;
import co.edu.unbosque.loom.dto.MedicamentoPorEpsView;
import co.edu.unbosque.loom.dto.TopMedicosDTO;
import co.edu.unbosque.loom.dto.TopMedicosView;
import co.edu.unbosque.loom.model.Medicamento;
import co.edu.unbosque.loom.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository medicamentoRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	public MedicamentoService(MedicamentoRepository medicamentoRepo, ModelMapper modelMapper) {
		super();
		this.medicamentoRepo = medicamentoRepo;
		this.modelMapper = modelMapper;
	}



	public List<MedicamentoDTO> mostrarPorPaciente(String username) {
		List<Medicamento> entityList = (List<Medicamento>) medicamentoRepo.findMedicamentosByPaciente(username);
		List<MedicamentoDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			MedicamentoDTO dto = modelMapper.map(entity, MedicamentoDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}
	
	public List<MedicamentoPorEpsDTO> obtenerTopMedicamentos() {
		List<MedicamentoPorEpsDTO> dtos = new ArrayList<>();
		List<MedicamentoPorEpsView> proyecciones = medicamentoRepo.findMedicamentosMasRecetadosPorEps();
		for (MedicamentoPorEpsView proyeccion : proyecciones) {
			dtos.add(toDTOTopMedicamentos(proyeccion));
		}

		return dtos;
	}
	
	private MedicamentoPorEpsDTO toDTOTopMedicamentos(MedicamentoPorEpsView view) {
		MedicamentoPorEpsDTO dto = new MedicamentoPorEpsDTO();
		dto.setMedicamento(view.getMedicamento());
		dto.setNombreEps(view.getNombreEps());
		dto.setTotal(view.getTotal());
	    return dto;
	}
}

