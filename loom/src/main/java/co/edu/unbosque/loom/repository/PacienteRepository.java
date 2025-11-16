package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, Integer>{
	
	public Optional<Paciente> findByDocumento(String documento);
	public Optional<Paciente> findByUsername(String username);
	public Optional<Paciente> findByIdUsuario(Integer idUsuario);

	
}
