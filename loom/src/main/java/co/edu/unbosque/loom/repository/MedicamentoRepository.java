package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Medicamento;


public interface MedicamentoRepository extends CrudRepository<Medicamento, Integer> {

	public Optional<Medicamento> findByIdMedicamento(Integer idMedicamento);
}
