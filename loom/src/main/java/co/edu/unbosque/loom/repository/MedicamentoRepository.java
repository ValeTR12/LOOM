package co.edu.unbosque.loom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.loom.dto.MedicamentoPorEpsView;
import co.edu.unbosque.loom.model.Medicamento;


public interface MedicamentoRepository extends CrudRepository<Medicamento, Integer> {

	public Optional<Medicamento> findByIdMedicamento(Integer idMedicamento);
	
	@Query("""
	        SELECT m
	        FROM Consulta c
	        JOIN c.medicamentos m
	        WHERE c.paciente.username = :username
	    """)
	List<Medicamento> findMedicamentosByPaciente(@Param("username") String username);
	
	@Query(value = """
		       SELECT e.nombre_eps AS nombreEps,
		              m.nombre_comercial AS medicamento,
		              COUNT(m.id_medicamento) AS total
		       FROM medicamento m
		       JOIN consulta c ON c.id_consulta = m.id_consulta
		       JOIN medico med ON med.id_medico = c.id_medico
		       JOIN eps e ON e.id_eps = med.id_eps
		       GROUP BY e.nombre_eps, m.nombre_comercial
		       ORDER BY total DESC
		       LIMIT 7
		""", nativeQuery = true)
		List<MedicamentoPorEpsView> findMedicamentosMasRecetadosPorEps();


}
