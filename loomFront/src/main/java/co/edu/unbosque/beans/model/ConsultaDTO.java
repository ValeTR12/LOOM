package co.edu.unbosque.beans.model;

import java.time.LocalDate;
import java.util.List;

public class ConsultaDTO {

	private Integer idConsulta;
	private Integer idPaciente;
	private Integer idMedico;
	private EpsDTO eps;
	private LocalDate fechaConsulta;
	private LocalDate fechaValidez;
	private List<DiagnosticoDTO> diagnosticos;
	private List<MedicamentoDTO> medicamentos;
	private List<ExamenDTO> examenes;

	public ConsultaDTO() {
		// TODO Auto-generated constructor stub
	}

	public ConsultaDTO(Integer idConsulta, Integer idPaciente, Integer idMedico, EpsDTO eps, LocalDate fechaConsulta,
			LocalDate fechaValidez, List<DiagnosticoDTO> diagnosticos, List<MedicamentoDTO> medicamentos,
			List<ExamenDTO> examenes) {
		super();
		this.idConsulta = idConsulta;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
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

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public EpsDTO getEps() {
		return eps;
	}

	public void setEps(EpsDTO eps) {
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

	public List<DiagnosticoDTO> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<DiagnosticoDTO> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public List<MedicamentoDTO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<ExamenDTO> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<ExamenDTO> examenes) {
		this.examenes = examenes;
	}

	public void calcularFechaValidez() {

		if (diagnosticos == null || diagnosticos.isEmpty()) {
			this.fechaValidez = this.fechaConsulta;
			return;
		}

		// Selecciona el máximo tiempo entre diagnósticos
		int dias = diagnosticos.stream().mapToInt(DiagnosticoDTO::obtenerDiasValidez).max().orElse(0);

		this.fechaValidez = this.fechaConsulta.plusDays(dias);
	}

	public boolean estaVigente() {
		return LocalDate.now().isBefore(fechaValidez) || LocalDate.now().isEqual(fechaValidez);
	}
}