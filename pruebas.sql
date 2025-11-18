
/* ============================================================
   🔹 PRUEBA 1 — Cambio de EPS con historial preservado
   ============================================================ */

-- 1.1 Cambiar al paciente de EPS (por ejemplo, ahora queda en EPS 1)
UPDATE paciente
SET id_eps = 3
WHERE id_paciente = 44;


/* ============================================================
   🔹 1.2 Insertar una nueva consulta en la NUEVA EPS
   ============================================================ */

-- Obtener un médico que pertenezca a la EPS 1
SET @nuevo_medico := (
    SELECT id_medico FROM medico WHERE id_eps = 3 LIMIT 1
);

-- Insertar la nueva consulta
INSERT INTO consulta (
    id_paciente, id_medico, id_eps, fecha_consulta, fecha_validez
)
VALUES (
    44,
    @nuevo_medico,
    3,                                   -- NUEVA EPS del paciente
    CURRENT_DATE,
    DATE_ADD(CURRENT_DATE, INTERVAL 30 DAY)
);

-- Registrar diagnóstico obligatorio para esta consulta
SET @id_consulta_nueva := LAST_INSERT_ID();

INSERT INTO diagnostico (codigo_cie10, descripcion, id_consulta)
VALUES ('J00', 'Infección respiratoria leve con evolución favorable.', @id_consulta_nueva);


/* ============================================================
   🔹 1.3 MOSTRAR TODAS LAS CONSULTAS DEL PACIENTE,
      INCLUYENDO LA EPS REAL DONDE SE ATENDIÓ CADA UNA
   ============================================================ */

SELECT 
    c.id_consulta,
    c.fecha_consulta,
    c.fecha_validez,
    eps.nombre_eps AS eps_donde_fue_atendido,
    c.id_medico,
    CONCAT(u.primer_nombre, ' ', u.primer_apellido) AS medico_nombre,
    d.codigo_cie10,
    d.descripcion AS diagnostico
FROM consulta c
JOIN diagnostico d ON d.id_consulta = c.id_consulta
JOIN eps ON eps.id_eps = c.id_eps               -- EPS REAL DE LA CONSULTA
JOIN medico m ON m.id_medico = c.id_medico
JOIN usuario u ON u.id_usuario = m.id_medico
WHERE c.id_paciente = 27
ORDER BY c.fecha_consulta DESC;



/* ============================================================
   🔹 PRUEBA 2 — Validación completa:
        ✔ El médico atendió al paciente
        ✔ La consulta pertenece a ese médico
        ✔ La consulta aún está en fecha válida (fecha_validez >= hoy)
        ✔ El diagnóstico corresponde a esa consulta
   ============================================================ */


-- 2.1 Simular que una consulta es antigua y ya NO es válida
UPDATE consulta
SET fecha_consulta = DATE_SUB(CURRENT_DATE, INTERVAL 200 DAY),
    fecha_validez  = DATE_SUB(CURRENT_DATE, INTERVAL 100 DAY)
WHERE id_consulta = 13;


-- 2.2 Consulta final (si no devuelve nada, NO tiene permiso)
SELECT 
    c.id_consulta,
    c.id_medico,
    c.id_paciente,
    c.fecha_consulta,
    c.fecha_validez,
    d.codigo_cie10,
    d.descripcion AS descripcion_diagnostico
FROM consulta c
JOIN diagnostico d ON d.id_consulta = c.id_consulta
WHERE c.id_medico = 25      -- médico que intenta ver la info
  AND c.id_paciente = 38   -- paciente objetivo
  AND c.id_consulta = 13    -- consulta que queremos validar
  AND c.fecha_validez >= CURRENT_DATE;  -- fecha válida REAL



/* ============================================================
   🔹 PRUEBA 3 — Médico NO puede ver pacientes que NO atendió,
     aunque tengan su misma EPS
   ============================================================ */

-- 3.1 Verificar si el médico ha atendido al paciente
SELECT COUNT(*) AS vecesAtendido
FROM consulta
WHERE id_medico = 7        -- id del médico a probar
  AND id_paciente = 10;    -- id del paciente

-- 3.2 Intento de obtener información (debe devolver 0 registros)
SELECT c.*
FROM consulta c
WHERE c.id_paciente = 10
  AND c.id_medico = 7;


/* ============================================================
   🔹 PRUEBA 4 — Buscar un médico por tarjeta profesional
     y verificar que solo puede ver sus propios pacientes
   ============================================================ */

-- 4.1 Buscar médico por tarjeta profesional
SELECT *
FROM medico
WHERE tarjeta_profesional = 'TP100001';

-- 4.2 Consultas que SOLO ese médico atendió
SELECT 
    c.id_consulta,
    p.id_paciente,
    u.documento AS documento_paciente
FROM consulta c
JOIN paciente p 
    ON p.id_paciente = c.id_paciente
JOIN usuario u 
    ON u.id_usuario = p.id_paciente     -- Documento está en usuario
JOIN medico m 
    ON m.id_medico = c.id_medico
WHERE u.primer_nombre = 'María' AND u.primer_apellido = 'Sierra';

/* ============================================================
   🔹 PRUEBA 5 — Buscar el historial ciclinico del paciente 
       sin importar eps
   ============================================================ */

SELECT 
    c.id_consulta,
    c.fecha_consulta,
    eps.nombre_eps,
    m.tarjeta_profesional,
    d.codigo_cie10,
    med.nombre_comercial,
    ex.tipo_examen
FROM consulta c
JOIN medico m ON m.id_medico = c.id_medico
LEFT JOIN diagnostico d ON d.id_consulta = c.id_consulta
LEFT JOIN medicamento med ON med.id_consulta = c.id_consulta
LEFT JOIN examen ex ON ex.id_consulta = c.id_consulta
JOIN eps ON eps.id_eps = c.id_eps
WHERE c.id_paciente = 27
ORDER BY c.id_consulta;


