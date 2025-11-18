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
import jakarta.faces.application.NavigationHandler;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<UsuarioDTO> usuarios;
	private UsuarioDTO loginUsuario;
	private UsuarioDTO loginAdmin;
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
		loginAdmin = new UsuarioDTO();
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
			mostrarMensaje("Éxito", cuerpo);
			registroExitoso = true;
			// adminBean.cargarUsuarios();
			this.nuevoUsuario = new UsuarioDTO();
			if (getPaginaActual().equals("iniciosesionM.xhtml")) {
				return "iniciosesionM.xhtml?faces-redirect=true";
			}
			return "iniciosesionP.xhtml?faces-redirect=true";
			
		}else {
			mostrarMensaje("Error", cuerpo);
			registroExitoso = false;
			if (getPaginaActual().equals("iniciosesionM.xhtml")) {
				return "iniciosesionM.xhtml?faces-redirect=true";
			}
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
		}
		if (response != null && response.equals("PACIENTE")) {
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
	
	public String loginAdmin() {
		String username = uDAO.iniciarsesionAdmin(loginAdmin);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado",
				username);
		if(username.equals("ADMIN")) {
			return "admin.xhtml?faces-redirect=true";
		}
		return "index.xhtml?faces-redirect=true";
	}
	
	public String cerrarSesion() {
	    // Invalidar la sesión actual
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    facesContext.getExternalContext().invalidateSession();
	    
	    // Limpiar la variable de usuario logueado por si acaso
	    usuarioLogueado = null;

	    // Redirigir al login con faces-redirect para que la URL se actualice
	    return "inicio.xhtml?faces-redirect=true"; // o "iniciosesionM.xhtml" según corresponda
	}
	
	public void verificarSesion() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    Object usuarioLogueado = context.getExternalContext().getSessionMap().get("usuarioLogueado");

	    // Obtén la URL solicitada
	    String paginaSolicitada = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();

	    try {
	        if (usuarioLogueado == null) {
	            // Si no hay sesión, redirige siempre a index.xhtml
	            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/index.xhtml");
	            context.responseComplete();
	        } else {
	            // Usuario logueado
	            // Obtén la página actual permitida desde la sesión
	            String paginaActual = (String) context.getExternalContext().getSessionMap().get("paginaActual");
	            if (paginaActual == null) {
	                // Si no hay página en sesión, inicializa con la actual
	                paginaActual = paginaSolicitada;
	                context.getExternalContext().getSessionMap().put("paginaActual", paginaActual);
	            }

	            // Si intenta acceder a otra página diferente, redirige a la misma que estaba
	            if (!paginaSolicitada.equals(paginaActual)) {
	                context.getExternalContext().redirect(paginaActual);
	                context.responseComplete();
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	public void verificarCierre() {
		FacesContext context = FacesContext.getCurrentInstance();
		Object usuarioLogueado = context.getExternalContext().getSessionMap().get("usuarioLogueado");

		if (usuarioLogueado == null) {
			try {
				if(getPaginaActual().equals("paciente.xhtml")) {
				context.getExternalContext().redirect("paciente.xhtml");
				}
				if(getPaginaActual().equals("medico.xhtml")){
					context.getExternalContext().redirect("medico.xhtml");
				}else {
				context.getExternalContext().redirect("index.xhtml");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void logueado() {
		usuarioLogueado = uDAO.buscarDocumento(loginUsuario.getDocumento());

	}

	public boolean isRegistroExitoso() {
		return registroExitoso;
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

	public UsuarioDTO getLoginAdmin() {
		return loginAdmin;
	}

	public void setLoginAdmin(UsuarioDTO loginAdmin) {
		this.loginAdmin = loginAdmin;
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
