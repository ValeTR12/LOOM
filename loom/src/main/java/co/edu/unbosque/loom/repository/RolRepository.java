package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer> {
	
	public Optional<Rol> findByIdRol(Integer idRol);
	

}
