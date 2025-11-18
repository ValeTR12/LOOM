# Autores
González Rendón Maryory
Tamayo Ruiz Valentina

# LOOM – Sistema Unificado de Historia Clínica

LOOM es un sistema diseñado para centralizar la información clínica de pacientes dentro del sistema de salud colombiano.  
Permite administrar pacientes, médicos, EPS y eventos clínicos como consultas, diagnósticos, exámenes y medicamentos.  
El sistema garantiza trazabilidad incluso cuando el paciente cambia de EPS, además de integrar reglas de acceso por rol y consultas estadísticas para validar el funcionamiento general.

---

# Archivos del Proyecto

- **tablas.sql** — Crea la base de datos LOOM con todas las tablas, relaciones y llaves.  
- **insercion.sql** — Inserta datos iniciales de prueba (EPS, médicos, pacientes, consultas, diagnósticos, etc.).  
- **graficas.sql** — Contiene las consultas estadísticas para validar el comportamiento del sistema.  
- **pruebas.sql** — Incluye pruebas funcionales para verificar reglas como permisos por rol y tiempos de acceso del médico.  
- **README.md** — Documento guía del proyecto con instrucciones de ejecución.

---

# Instrucciones de Ejecución

1. Abrir un cliente SQL compatible con MySQL (**MySQL Workbench**).  
2. Ejecutar `tablas.sql` para crear la estructura completa de la base de datos.  
3. Ejecutar `insercion.sql` para cargar los datos de prueba.  
4. Ejecutar `pruebas.sql` para validar reglas de negocio (permisos por rol, tiempos de acceso del médico, consistencia del historial).  
5. Ejecutar `graficas.sql` para visualizar estadísticas del sistema.

---

# Regla Específica de Acceso Importante (Rol Médico)

- **Médico autorizado:** puede consultar el historial del paciente solo si lo ha atendido previamente y si el tiempo de autorización sigue vigente según el diagnóstico.  
- **Paciente:** puede consultar siempre toda su información clínica.  

Esta regla se asegura con la siguiente validación incluida en `pruebas.sql`:

```sql
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Acceso no permitido: el médico no tiene autorización vigente para consultar el historial clínico.';
