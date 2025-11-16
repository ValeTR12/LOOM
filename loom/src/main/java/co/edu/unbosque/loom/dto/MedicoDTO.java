package co.edu.unbosque.loom.dto;

public class MedicoDTO extends UsuarioDTO {

	private Integer idEps;
	private String tarjetaProfesional;
	private String especialidad;

	public MedicoDTO() {
		// TODO Auto-generated constructor stub
	}

	public MedicoDTO(Integer idEps, String tarjetaProfesional, String especialidad) {
		super();
		this.idEps = idEps;
		this.tarjetaProfesional = tarjetaProfesional;
		this.especialidad = especialidad;
	}

	public Integer getIdEps() {
		return idEps;
	}

	public void setIdEps(Integer idEps) {
		this.idEps = idEps;
	}

	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}

	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Medico [eps=" + idEps + ", tarjetaProfesional=" + tarjetaProfesional + ", especialidad=" + especialidad
				+ "]";
	}

}
