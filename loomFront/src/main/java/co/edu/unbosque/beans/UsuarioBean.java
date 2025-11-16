package co.edu.unbosque.beans;

import java.io.IOException;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import co.edu.unbosque.beans.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<UsuarioDTO> usuarios;
	private UsuarioDTO loginUsuario;
	private UsuarioDTO usuarioLogueado;
	private String contrasena;
	private String confirmarContrasena;

	private UsuarioDTO nuevoUsuario = new UsuarioDTO();
	private boolean registroExitoso = false;

	@Inject
	private UsuarioDAO uDAO;

	@PostConstruct
	public void init() {
		this.usuarios = uDAO.getListaUsuarios();
		loginUsuario = new UsuarioDTO();
		usuarioLogueado = new UsuarioDTO();
		nuevoUsuario = new UsuarioDTO();
	}

	public String registrar() {
		
		if (getPaginaActual().equals("iniciosesionP.xhtml")) {

			if (nuevoUsuario.getUsername().contains("TP")) {
				mostrarMensaje("Error", "Usuario invalido para paciente.");
				registroExitoso = false;
				return null;
			}
		}

		if (getPaginaActual().equals("iniciosesionM.xhtml")) {

			if (!(nuevoUsuario.getUsername().contains("TP"))) {
				mostrarMensaje("Error", "Usuario invalido para medico.");
				registroExitoso = false;
				return null;
			}
		}
		// 1. Validar contraseñas iguales
		if (!contrasena.equals(confirmarContrasena)) {
			mostrarMensaje("Error", "Las contraseñas no coinciden.");
			registroExitoso = false;
			return null;
		}

		nuevoUsuario.setContrasena(confirmarContrasena);

		// 2. Validar seguridad de la contraseña
		if (!esContrasenaSegura(nuevoUsuario.getContrasena())) {
			mostrarMensaje("Error",
					"La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número.");
			registroExitoso = false;
			return null;
		}

		String cuerpo = uDAO.registrar(nuevoUsuario);

		if (cuerpo.equals("Registro exitoso. Contraseña actualizada.")) {
			//System.out.println(nuevoUsuario.getRol().getNombreRol());
			mostrarMensaje("Éxito", cuerpo);
			registroExitoso = true;
			// adminBean.cargarUsuarios();
			this.nuevoUsuario = new UsuarioDTO();
			return "iniciosesionP.xhtml?faces-redirect=true";
		} else {
			mostrarMensaje("Error", cuerpo);
			registroExitoso = false;
			return "iniciosesionP.xhtml?faces-redirect=true";
		}
	}

	private void mostrarMensaje(String resumen, String detalle) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(resumen, detalle));
	}

	private boolean esContrasenaSegura(String contrasena) {
		return contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
	}

	public String login() {
		
		if (getPaginaActual().equals("iniciosesionP.xhtml")) {

			if (loginUsuario.getUsername().contains("TP")) {
				mostrarMensaje("Error", "Usuario invalido para paciente.");
				registroExitoso = false;
				return null;
			}
		}

		if (getPaginaActual().equals("iniciosesionM.xhtml")) {

			if (!(loginUsuario.getUsername().contains("TP"))) {
				mostrarMensaje("Error", "Usuario invalido para medico.");
				registroExitoso = false;
				return null;
			}
		}
		
		String response = uDAO.iniciarsesion(loginUsuario); 
		if (response != null && response.equals("MEDICO")) {
			usuarioLogueado = loginUsuario;
			System.out.println("Guardando en sesión: " + usuarioLogueado.getUsername());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado",
					usuarioLogueado);
			loginUsuario = new UsuarioDTO();
			return "medico.xhtml?faces-redirect=true";
		} else if (response != null && response.equals("PACIENTE")) {
			usuarioLogueado = loginUsuario;
			System.out.println("Guardando en sesión: " + usuarioLogueado.getUsername());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado",
					usuarioLogueado);
			loginUsuario = new UsuarioDTO();
			return "paciente.xhtml?faces-redirect=true";
		} else {
			mostrarMensaje("Error", response);
			return null;
		}
	}

	public void logueado() {
		usuarioLogueado = uDAO.buscarDocumento(loginUsuario.getDocumento());

	}

	public boolean isRegistroExitoso() {
		return registroExitoso;
	}

	public void verificarSesion() {
		FacesContext context = FacesContext.getCurrentInstance();
		Object usuarioLogueado = context.getExternalContext().getSessionMap().get("usuarioLogueado");

		if (usuarioLogueado == null) {
			try {
				context.getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String traerNombre(Integer id) {
		UsuarioDTO usuario = uDAO.buscarPorId(id);
		return usuario.getPrimerNombre() + " " + usuario.getPrimerApellido();
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		usuarios = usuarios;
	}

	public boolean verificarRol(String username) {

		if (username.contains("TP")) {
			return true;
		}

		return false;
	}

	public String getPaginaActual() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		// Esto te devuelve algo como: /app/paciente.xhtml
		String path = request.getRequestURI();

		// Si solo quieres el nombre de la página:
		String pagina = path.substring(path.lastIndexOf("/") + 1);
		return pagina;
	}

	public UsuarioDTO getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(UsuarioDTO loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public UsuarioDTO getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getConfirmarContrasena() {
		return confirmarContrasena;
	}

	public void setConfirmarContrasena(String confirmarContrasena) {
		this.confirmarContrasena = confirmarContrasena;
	}

	public UsuarioDTO getNuevoUsuario() {
		return nuevoUsuario;
	}

	public void setNuevoUsuario(UsuarioDTO nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}

	public UsuarioDAO getuDAO() {
		return uDAO;
	}

	public void setuDAO(UsuarioDAO uDAO) {
		this.uDAO = uDAO;
	}

	public void setRegistroExitoso(boolean registroExitoso) {
		this.registroExitoso = registroExitoso;
	}

}
