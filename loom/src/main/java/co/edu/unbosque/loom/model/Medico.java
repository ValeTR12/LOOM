package co.edu.unbosque.loom.model;


import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
@PrimaryKeyJoinColumn(name = "id_medico")
public class Medico extends Usuario {

	@ManyToOne
	@JoinColumn(name = "id_eps")
	private Eps eps;
	private String tarjetaProfesional;
	private String especialidad;

	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public Medico(Eps eps, String tarjetaProfesional, String especialidad) {
		super();
		this.eps = eps;
		this.tarjetaProfesional = tarjetaProfesional;
		this.especialidad = especialidad;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
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
		return "Medico [eps=" + eps + ", tarjetaProfesional=" + tarjetaProfesional + ", especialidad=" + especialidad
				+ "]";
	}

}
