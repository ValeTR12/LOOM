package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.loom.model.Medico;

public interface MedicoRepository extends CrudRepository<Medico, Integer>{

	public Optional<Medico> findByDocumento(String documento);
	public Optional<Medico> findByTarjetaProfesional(String tarjetaProfesional);  
	public Optional<Medico> findByUsername(String username);
	public Optional<Medico> findByIdUsuario(Integer idUsuario);

	
}
