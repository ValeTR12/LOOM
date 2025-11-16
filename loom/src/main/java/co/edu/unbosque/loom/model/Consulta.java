package co.edu.unbosque.loom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsulta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_eps")
	private Eps eps;

	private LocalDate fechaConsulta;

	private LocalDate fechaValidez;

	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
	private List<Diagnostico> diagnosticos;

	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
	private List<Medicamento> medicamentos;

	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
	private List<Examen> examenes;

	public Consulta() {
		// TODO Auto-generated constructor stub
	}

	public Consulta(Paciente paciente, Medico medico, Eps eps, LocalDate fechaConsulta, LocalDate fechaValidez,
			List<Diagnostico> diagnosticos, List<Medicamento> medicamentos, List<Examen> examenes) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.eps = eps;
		this.fechaConsulta = fechaConsulta;
		this.fechaValidez = fechaValidez;
		this.diagnosticos = diagnosticos;
		this.medicamentos = medicamentos;
		this.examenes = examenes;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Eps getEps() {
		return eps;
	}

	public void setEps(Eps eps) {
		this.eps = eps;
	}

	public LocalDate getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(LocalDate fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public LocalDate getFechaValidez() {
		return fechaValidez;
	}

	public void setFechaValidez(LocalDate fechaValidez) {
		this.fechaValidez = fechaValidez;
	}

	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	
	

	public void calcularFechaValidez() {

		if (diagnosticos == null || diagnosticos.isEmpty()) {
			this.fechaValidez = this.fechaConsulta;
			return;
		}

		// Selecciona el máximo tiempo entre diagnósticos
		int dias = diagnosticos.stream().mapToInt(Diagnostico::obtenerDiasValidez).max().orElse(0);

		this.fechaValidez = this.fechaConsulta.plusDays(dias);
	}

	public boolean estaVigente() {
		return LocalDate.now().isBefore(fechaValidez) || LocalDate.now().isEqual(fechaValidez);
	}
}
