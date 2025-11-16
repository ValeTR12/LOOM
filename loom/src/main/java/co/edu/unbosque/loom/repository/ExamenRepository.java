package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Integer> {

	public Optional<Examen> findByIdExamen(Integer idExamen);
}
