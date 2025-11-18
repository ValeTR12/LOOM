package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.model.Medico;

public interface MedicoRepository extends CrudRepository<Medico, Integer> {

	public Optional<Medico> findByDocumento(String documento);

	public Optional<Medico> findByTarjetaProfesional(String tarjetaProfesional);

	public Optional<Medico> findByUsername(String username);

	public Optional<Medico> findByIdUsuario(Integer idUsuario);

	@Query("""
			SELECT m
			FROM Medico m
			JOIN m.eps e
			WHERE e.nombreEps = :nombreEps
			""")
	List<Medico> findMedicosPorEps(@Param("nombreEps") String nombreEps);

}
