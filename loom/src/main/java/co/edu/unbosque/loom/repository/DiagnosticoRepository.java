package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Diagnostico;

public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Integer> {

	public Optional<Diagnostico> findByIdDiagnostico(Integer idDiagnostico);
}
