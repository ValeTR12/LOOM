package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.dto.EpsCargaMensualView;
import co.edu.unbosque.loom.dto.TopMedicosView;
import co.edu.unbosque.loom.model.Consulta;

public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {

	public Optional<Consulta> findByIdConsulta(Integer idConsulta);

	@Query("""
			    SELECT c
			    FROM Consulta c
			    WHERE c.paciente.username = :username
			""")
	List<Consulta> findByPaciente(@Param("username") String username);
	
	//MEDICO
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

	@Query(value = """
		    SELECT m.id_eps AS idEps,
		           e.nombre_eps AS nombre,
		           MONTH(c.fecha_consulta) AS mes,
		           COUNT(c.id_consulta) AS totalConsultas
		    FROM consulta c
		    JOIN medico m ON c.id_medico = m.id_medico
		    JOIN eps e ON m.id_eps = e.id_eps
		    GROUP BY m.id_eps, e.nombre_eps, MONTH(c.fecha_consulta)
		    ORDER BY totalConsultas DESC
		""", nativeQuery = true)
		List<EpsCargaMensualView> findEpsCargaMensual();

	
	@Query(value = """
		       SELECT m.id_medico AS idMedico,
		              CONCAT(u.primer_nombre, ' ', u.primer_apellido) AS nombreCompleto,
		              COUNT(c.id_consulta) AS totalConsultas
		       FROM consulta c
		       JOIN medico m ON m.id_medico = c.id_medico
		       JOIN usuario u ON u.id_usuario = m.id_medico
		       WHERE c.fecha_consulta >= CURRENT_DATE - INTERVAL 365 DAY
		       GROUP BY m.id_medico, u.primer_nombre, u.primer_apellido
		       ORDER BY totalConsultas DESC
		       LIMIT 10
		""", nativeQuery = true)
		List<TopMedicosView> findTopMedicosUltimoAno();

}
