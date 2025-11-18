package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.dto.TopEnfermedadesView;
import co.edu.unbosque.loom.model.Diagnostico;

public interface DiagnosticoRepository extends CrudRepository<Diagnostico, Integer> {

	public Optional<Diagnostico> findByIdDiagnostico(Integer idDiagnostico);
	
	@Query("""
	        SELECT d
	        FROM Consulta c
	        JOIN c.diagnosticos d
	        WHERE c.paciente.username = :username
	    """)
	List<Diagnostico> findDiagnosticosByPaciente(@Param("username") String username);

	@Query("""
		       SELECT d.codigoCie10 AS codigoCie10,
		              d.descripcion AS descripcion,
		              COUNT(d.idDiagnostico) AS total
		       FROM Diagnostico d
		       GROUP BY d.codigoCie10, d.descripcion
		       ORDER BY total DESC
		       LIMIT 5
		""")
		List<TopEnfermedadesView> findTop5Enfermedades();

}



