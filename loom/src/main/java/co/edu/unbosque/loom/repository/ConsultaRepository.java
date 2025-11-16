package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.model.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {

	public Optional<Consulta> findByIdConsulta(Integer idConsulta);

	@Query("""
			    SELECT c
			    FROM Consulta c
			    WHERE c.paciente.username = :username
			""")
	List<Consulta> findByPaciente(@Param("username") String username);
	
	@Query(value = """
	        SELECT *
	        FROM consulta
	        WHERE id_paciente IN (:idsPacientes)
	          AND fecha_validez >= CURRENT_DATE
	    """, nativeQuery = true)
	List<Consulta> findConsultasByPacientes(List<Integer> idsPacientes);

	
	@Query(value = """
	        SELECT DISTINCT c.id_paciente
	        FROM consulta c
	        WHERE c.id_medico = :idMedico
	          AND c.fecha_validez >= CURRENT_DATE
	    """, nativeQuery = true)
	List<Integer> findPacientesConPermisoVigente(Integer idMedico);



}
