package co.edu.unbosque.loom.dto;

public interface PerfilClinicoView {
    Integer getIdPaciente();
    String getNombreCompleto();
    Long getTotalConsultas();
    Long getTotalDiagnosticos();
    Long getTotalMedicamentos();
}
