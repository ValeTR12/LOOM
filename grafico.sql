-- Eps Carga Mensual --
SELECT 
    m.id_eps AS idEps,
    e.nombre_eps AS nombre,
    MONTH(c.fecha_consulta) AS mes,
    COUNT(c.id_consulta) AS totalConsultas
FROM consulta c
JOIN medico m ON c.id_medico = m.id_medico
JOIN eps e ON m.id_eps = e.id_eps
GROUP BY m.id_eps, e.nombre_eps, MONTH(c.fecha_consulta)
ORDER BY totalConsultas DESC;


-- Top Medicos Ultimo Año --
SELECT 
    m.id_medico AS idMedico,
    CONCAT(u.primer_nombre, ' ', u.primer_apellido) AS nombreCompleto,
    COUNT(c.id_consulta) AS totalConsultas
FROM consulta c
JOIN medico m ON m.id_medico = c.id_medico
JOIN usuario u ON u.id_usuario = m.id_medico
WHERE c.fecha_consulta >= CURRENT_DATE - INTERVAL 365 DAY
GROUP BY m.id_medico, u.primer_nombre, u.primer_apellido
ORDER BY totalConsultas DESC
LIMIT 10;


-- Top Enfermedades --
SELECT 
    d.codigoCie10 AS codigoCie10,
    d.descripcion AS descripcion,
    COUNT(d.idDiagnostico) AS total
FROM Diagnostico d
GROUP BY d.codigoCie10, d.descripcion
ORDER BY total DESC
LIMIT 5;


-- Medicamentos más recetados por EPS --
SELECT 
    e.nombre_eps AS nombreEps,
    m.nombre_comercial AS medicamento,
    COUNT(m.id_medicamento) AS total
FROM medicamento m
JOIN consulta c ON c.id_consulta = m.id_consulta
JOIN medico med ON med.id_medico = c.id_medico
JOIN eps e ON e.id_eps = med.id_eps
GROUP BY e.nombre_eps, m.nombre_comercial
ORDER BY total DESC
LIMIT 7;