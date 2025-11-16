package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Eps;

public interface EpsRepository extends CrudRepository<Eps, Integer>{

	public Optional<Eps> findByIdEps(Integer idEps);
}
