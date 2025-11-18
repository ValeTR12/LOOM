package co.edu.unbosque.model.persistence;

import java.util.List;

import co.edu.unbosque.beans.model.MedicoDTO;
import co.edu.unbosque.beans.model.UsuarioDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
public class UsuarioDAO {

	private List<UsuarioDTO> listaUsuarios;
	
	public List<UsuarioDTO> getListaUsuarios() {
		listaUsuarios = ExternalHTTPRequestHandler.doGetAllUsuarios("http://localhost:8082/usuario/mostrartodo");
		return listaUsuarios;
	}
	
	public String iniciarsesion(UsuarioDTO usuario) {
		String cuerpo = ExternalHTTPRequestHandler.doGetUsuario("http://localhost:8082/usuario/login", usuario);
		return cuerpo;
	}
	
	public String iniciarsesionAdmin(UsuarioDTO usuario) {
		String cuerpo = ExternalHTTPRequestHandler.doGetUsuario("http://localhost:8082/usuario/loginadmin", usuario);
		return cuerpo;
	}

	public String registrar(UsuarioDTO usuario) {
		String cuerpo = ExternalHTTPRequestHandler.doGetUsuario("http://localhost:8082/usuario/register", usuario);
		return cuerpo;
	}
	
	public UsuarioDTO buscarDocumento(String documento) {
		return ExternalHTTPRequestHandler.doGetUsuario("http://localhost:8082/usuario/obtener?documento="+documento); 
	}
	
	public UsuarioDTO buscarPorId(Integer id) {
		return ExternalHTTPRequestHandler.doGetUsuario("http://localhost:8082/usuario/obtenerporid?id="+id); 
	}

}
