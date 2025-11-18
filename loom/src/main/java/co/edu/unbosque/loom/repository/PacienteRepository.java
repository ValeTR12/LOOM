package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.dto.PerfilClinicoView;
import co.edu.unbosque.loom.model.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, Integer>{
	
	public Optional<Paciente> findByDocumento(String documento);
	public Optional<Paciente> findByUsername(String username);
	public Optional<Paciente> findByIdUsuario(Integer idUsuario);

	@Query(value = """
		       SELECT p.id_paciente AS idPaciente,
		              CONCAT(u.primer_nombre, ' ', u.primer_apellido) AS nombreCompleto,
		              COUNT(DISTINCT c.id_consulta) AS totalConsultas,
		              COUNT(DISTINCT d.id_diagnostico) AS totalDiagnosticos,
		              COUNT(DISTINCT m.id_medicamento) AS totalMedicamentos
		       FROM paciente p
		       LEFT JOIN consulta c ON c.id_paciente = p.id_paciente
		       LEFT JOIN diagnostico d ON d.id_consulta = c.id_consulta
		       LEFT JOIN medicamento m ON m.id_consulta = c.id_consulta
		       JOIN usuario u ON u.id_usuario = p.id_paciente
		       WHERE p.id_paciente = :idPaciente
		       GROUP BY p.id_paciente, u.primer_nombre, u.primer_apellido
		""", nativeQuery = true)
		Optional<PerfilClinicoView> findPerfilClinico(@Param("idPaciente") Integer idPaciente);


	@Query("""
		       SELECT p
		       FROM Paciente p
		       JOIN p.eps e
		       WHERE e.nombreEps = :nombreEps
		       """)
		List<Paciente> findPacientesPorEps(@Param("nombreEps") String nombreEps);

	
}
