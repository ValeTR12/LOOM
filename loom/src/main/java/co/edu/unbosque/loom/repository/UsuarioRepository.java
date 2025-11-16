package co.edu.unbosque.loom.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.loom.model.Paciente;
import co.edu.unbosque.loom.model.Usuario;
import java.util.List;


public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	public Optional<Usuario> findByDocumento(String documento);
	public Optional<Usuario> findByUsername(String username);
	public Optional<Usuario> findByIdUsuario(Integer idUsuario);
	
	

}
