package co.edu.unbosque.loom.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.loom.dto.EpsDTO;
import co.edu.unbosque.loom.repository.EpsRepository;

@Service
public class EpsService {
	
	@Autowired
	private EpsRepository epsRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public EpsService() {
		// TODO Auto-generated constructor stub
	}
	
	public EpsDTO buscarPorId(Integer id) {
		return epsRepo.findByIdEps(id).map(user -> {
			EpsDTO dto = modelMapper.map(user, EpsDTO.class);
			return dto;
		}).orElse(null);

	}
}
