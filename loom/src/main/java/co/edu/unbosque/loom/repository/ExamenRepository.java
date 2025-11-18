package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.model.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Integer> {

	public Optional<Examen> findByIdExamen(Integer idExamen);
	
	@Query("""
	        SELECT e
	        FROM Consulta c
	        JOIN c.examenes e
	        WHERE c.paciente.username = :username
	    """)
	List<Examen> findExamenesByPaciente(@Param("username") String username);

}
