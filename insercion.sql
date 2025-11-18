USE Loom;

-- Inserts de roles
INSERT INTO rol (nombre_rol, descripcion) VALUES
('MEDICO', 'Rol para usuarios que son médicos'),
('PACIENTE', 'Rol para usuarios que son pacientes'),
('ADMIN', 'Rol para usuarios administradores');


-- Inserts de EPS con correos falsos
INSERT INTO eps (nombre_eps, tipo_regimen, correo) VALUES
('Salud Total', 'Contributivo', 'servicioalcliente@saludtotal.com.co'),
('Compensar', 'Contributivo', 'servicioalcliente@compensar.com.co'),
('Coosalud', 'Contributivo', 'servicioalcliente@coosalud.com.co'),
('Famisanar', 'Subsidiado', 'servicioalcliente@famisanar.com.co'),
('Mutual Ser', 'Subsidiado', 'servicioalcliente@mutualser.com.co'),
('Sanitas', 'Contributivo', 'servicioalcliente@sanitas.com.co'),
('Nueva EPS', 'Contributivo', 'servicioalcliente@nuevaeps.com.co'),
('SURA', 'Contributivo', 'servicioalcliente@sura.com.co');

INSERT INTO contacto_eps (tipo, valor, id_eps_fk) VALUES
-- SALUD TOTAL (1)
('TEL',  '6014854555',                        1),
('DIRE', 'Carrera 49 # 94-39, Bogotá',        1),

-- COMPENSAR (2)
('TEL',  '6014441234',                        2),
('DIRE', 'Avenida 68 # 49A-47, Bogotá',       2),

-- COSALUD (3)
('TEL',  '6013436000',                        3),
('DIRE', 'Calle 80 # 69Q-50, Bogotá',         3),

-- FAMISANAR (4)
('TEL',  '6017423700',                        4),
('DIRE', 'Calle 78 # 13-29, Bogotá',          4),

-- MUTUAL SER (5)
('TEL',  '6053205060',                        5),
('DIRE', 'Calle 30 # 21-45, Cartagena',       5),

-- SANITAS (6)
('TEL',  '6013759000',                        6),
('DIRE', 'Carrera 7 # 32-33, Bogotá',         6),

-- NUEVA EPS (7)
('TEL',  '6013077020',                        7),
('DIRE', 'Avenida Carrera 68 # 13-61, Bogotá',7),

-- SURA (8)
('TEL',  '6014897900',                        8),
('DIRE', 'Calle 94 # 13-30, Bogotá',          8);



DROP TEMPORARY TABLE IF EXISTS nombres;
CREATE TEMPORARY TABLE nombres (nombre VARCHAR(50));

INSERT INTO nombres VALUES
('Carlos'),('Ana'),('Luis'),('Valentina'),('Juan'),
('María'),('Andrés'),('Camila'),('Sebastián'),('Diana'),
('Felipe'),('Laura'),('Julián'),('Paula'),('Mateo'),
('Natalia'),('Jorge'),('Sara'),('Esteban'),('Isabela'),
('Daniel'),('Gabriela'),('Santiago'),('Miranda'),('Tomás');

DROP TEMPORARY TABLE IF EXISTS apellidos;
CREATE TEMPORARY TABLE apellidos (apellido VARCHAR(50));

INSERT INTO apellidos VALUES
('Gómez'),('Pérez'),('Rivas'),('López'),('Hernández'),
('Castillo'),('Ramírez'),('Pardo'),('Sierra'),('Cárdenas'),
('Rodríguez'),('Mejía'),('Torres'),('Vargas'),('Roldán'),
('Quintero'),('Morales'),('García'),('Ruiz'),('Mendoza'),
('Ortega'),('Guerrero'),('Cifuentes'),('Valencia'),('Ortiz');


SET @doc := 10000000;   -- DOCUMENTO de 8 dígitos
SET @tp := 100000;      -- TARJETA PROFESIONAL (6 dígitos)
SET @usuario_id := 0;


-- =========================================
-- PROCEDIMIENTO PARA GENERAR USUARIOS
-- =========================================
DELIMITER $$

DROP PROCEDURE IF EXISTS generar_usuarios $$

CREATE PROCEDURE generar_usuarios()
BEGIN
    DECLARE e INT DEFAULT 1;

    -- Contadores de documentos y tarjeta profesional
    SET @doc = 10000000;  -- documento inicial de 8 dígitos
    SET @tp = 100000;     -- tarjeta profesional inicial de 6 dígitos

    WHILE e <= 8 DO
        
        -- ===========================
        -- INSERTAR 25 MÉDICOS POR EPS
        -- ===========================
        SET @i = 1;

        WHILE @i <= 25 DO

            -- nombres aleatorios
            SET @nombre1 = (SELECT nombre FROM nombres ORDER BY RAND() LIMIT 1);
            SET @nombre2 = (SELECT nombre FROM nombres ORDER BY RAND() LIMIT 1);
            SET @ape1 = (SELECT apellido FROM apellidos ORDER BY RAND() LIMIT 1);
            SET @ape2 = (SELECT apellido FROM apellidos ORDER BY RAND() LIMIT 1);

            -- documento y tarjeta profesional
            SET @doc = @doc + 1;
            SET @tp = @tp + 1;

            INSERT INTO usuario (
                documento, primer_nombre, segundo_nombre, primer_apellido,
                segundo_apellido, direccion, ciudad, contrasena, id_rol, username
            ) VALUES (
                @doc,
                @nombre1, @nombre2, @ape1, @ape2,
                CONCAT('Calle ', FLOOR(RAND()*100+1)),
                'Bogotá', NULL,
                (SELECT id_rol FROM rol WHERE nombre_rol='MEDICO'),
                CONCAT('TP', @tp)
            );

            SET @usuario_id = LAST_INSERT_ID();

            INSERT INTO medico(id_medico, id_eps, tarjeta_profesional, especialidad)
            VALUES(@usuario_id, e, CONCAT('TP', @tp), 'Medicina General');

            INSERT INTO contacto(tipo, valor, id_usuario)
            VALUES('TEL', CONCAT('3', FLOOR(RAND()*900000000+100000000)), @usuario_id);

            INSERT INTO contacto(tipo, valor, id_usuario)
            VALUES('EMAIL', CONCAT(LOWER(@nombre1),'.',LOWER(@ape1),'@example.com'), @usuario_id);

            SET @i = @i + 1;
        END WHILE;

        -- =============================
        -- INSERTAR 50 PACIENTES POR EPS
        -- =============================
        SET @j = 1;

        WHILE @j <= 50 DO

            -- nombres aleatorios
            SET @nombre1 = (SELECT nombre FROM nombres ORDER BY RAND() LIMIT 1);
            SET @nombre2 = (SELECT nombre FROM nombres ORDER BY RAND() LIMIT 1);
            SET @ape1 = (SELECT apellido FROM apellidos ORDER BY RAND() LIMIT 1);
            SET @ape2 = (SELECT apellido FROM apellidos ORDER BY RAND() LIMIT 1);

            -- documento
            SET @doc = @doc + 1;

            INSERT INTO usuario (
                documento, primer_nombre, segundo_nombre, primer_apellido,
                segundo_apellido, direccion, ciudad, contrasena, id_rol, username
            ) VALUES (
                @doc,
                @nombre1, @nombre2, @ape1, @ape2,
                CONCAT('Carrera ', FLOOR(RAND()*100+1)),
                'Bogotá', NULL,
                (SELECT id_rol FROM rol WHERE nombre_rol='PACIENTE'),
                @doc
            );

            SET @usuario_id = LAST_INSERT_ID();

            INSERT INTO paciente(id_paciente, fecha_afiliacion, id_eps, tipo_afiliacion)
            VALUES(
                @usuario_id, 
                -- Fecha aleatoria entre 2022-01-01 y 2023-12-31
                DATE_ADD(MAKEDATE(2022,1), INTERVAL FLOOR(RAND()*730) DAY),
                e, 
                'Contributivo'
            );

            INSERT INTO contacto(tipo, valor, id_usuario)
            VALUES('TEL', CONCAT('3', FLOOR(RAND()*900000000+100000000)), @usuario_id);

            INSERT INTO contacto(tipo, valor, id_usuario)
            VALUES('EMAIL', CONCAT(LOWER(@nombre1),'.',LOWER(@ape1),'@example.com'), @usuario_id);

            SET @j = @j + 1;
        END WHILE;

        SET e = e + 1;
    END WHILE;

END $$

DELIMITER ;


-- =========================================
-- EJECUTAR PROCEDIMIENTO
-- =========================================
CALL generar_usuarios();

-- =========================================
-- OPCIONAL: BORRAR PROCEDIMIENTO
-- =========================================
DROP PROCEDURE generar_usuarios;



-- CONSULTAS --
-- =========================================
-- OPCIONAL: BORRAR TABLAS TEMPORALES Y PROCEDIMIENTO
-- =========================================
DROP TEMPORARY TABLE IF EXISTS cie10;
DROP TEMPORARY TABLE IF EXISTS medicamentos_real;
DROP PROCEDURE IF EXISTS generar_consultas;

-- =========================================
-- TABLA TEMPORAL CIE10
-- =========================================
CREATE TEMPORARY TABLE cie10(
    codigo VARCHAR(10),
    descripcion VARCHAR(255),
    gravedad VARCHAR(10), -- LEVE, MEDIA, GRAVE
    dias_validez INT
);

INSERT INTO cie10 VALUES
('J00','Rinitis aguda','LEVE',3),
('A09','Gastroenteritis','MEDIA',7),
('J18','Neumonía','GRAVE',20),
('E11','Diabetes tipo 2 sin complicaciones','MEDIA',10),
('I10','Hipertensión esencial','MEDIA',10),
('M54','Lumbago','LEVE',5),
('N39','Infección urinaria','MEDIA',7),
('B34','Infección viral no especificada','LEVE',3),
('K21','Reflujo gastroesofágico','MEDIA',7),
('J45','Asma','GRAVE',15);

-- =========================================
-- TABLA TEMPORAL MEDICAMENTOS REALES
-- =========================================
CREATE TEMPORARY TABLE medicamentos_real(
    nombre_comercial VARCHAR(50),
    principio_activo VARCHAR(50),
    dosis_default VARCHAR(20),
    frecuencia_default VARCHAR(50),
    duracion_default INT
);

INSERT INTO medicamentos_real VALUES
('Paracetamol','Paracetamol','500 mg','Cada 8 horas',5),
('Ibuprofeno','Ibuprofeno','400 mg','Cada 8 horas',7),
('Amoxicilina','Amoxicilina','500 mg','Cada 12 horas',10),
('Omeprazol','Omeprazol','20 mg','Cada 24 horas',14),
('Metformina','Metformina','850 mg','Cada 12 horas',30),
('Losartán','Losartán','50 mg','Cada 24 horas',30),
('Cetirizina','Cetirizina','10 mg','Cada 24 horas',7),
('Salbutamol','Salbutamol','100 mcg','Cada 8 horas',14),
('Azitromicina','Azitromicina','500 mg','Cada 24 horas',5),
('Furosemida','Furosemida','40 mg','Cada 24 horas',7);

-- =========================================
-- PROCEDIMIENTO PARA GENERAR CONSULTAS
-- =========================================
DELIMITER $$

DROP PROCEDURE IF EXISTS generar_consultas $$
CREATE PROCEDURE generar_consultas()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_id_pac INT;
    DECLARE v_id_eps INT;

    DECLARE cur CURSOR FOR 
        SELECT id_paciente, id_eps FROM paciente;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_id_pac, v_id_eps;
        IF done THEN LEAVE read_loop; END IF;

        -- Seleccionar médico de la misma EPS
        SET @id_medico := (
            SELECT id_medico FROM medico 
            WHERE id_eps = v_id_eps 
            ORDER BY RAND() LIMIT 1
        );

        -- Elegir CIE10
        SET @cie_codigo := (SELECT codigo FROM cie10 ORDER BY RAND() LIMIT 1);
        SET @cie_desc := (SELECT descripcion FROM cie10 WHERE codigo=@cie_codigo);
        SET @cie_dias := (SELECT dias_validez FROM cie10 WHERE codigo=@cie_codigo);

        -- FECHAS
        SET @fecha_consulta := DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND()*60) DAY);
        SET @fecha_validez := DATE_ADD(@fecha_consulta, INTERVAL @cie_dias DAY);

        -- ======================
        -- INSERTAR CONSULTA
        -- ======================
        INSERT INTO consulta(id_paciente, id_medico, id_eps, fecha_consulta, fecha_validez)
        VALUES(v_id_pac, @id_medico, v_id_eps, @fecha_consulta, @fecha_validez);

        SET @id_consulta := LAST_INSERT_ID();

        -- ======================
        -- INSERTAR DIAGNOSTICO (SIEMPRE)
        -- ======================
        INSERT INTO diagnostico(codigo_cie10, descripcion, id_consulta)
        VALUES(@cie_codigo, @cie_desc, @id_consulta);

        -- ======================
        -- MEDICAMENTOS (0-2) REALES
        -- ======================
        SET @cant_meds := FLOOR(RAND()*3); -- 0,1,2

        WHILE @cant_meds > 0 DO
            INSERT INTO medicamento(nombre_comercial, principio_activo, dosis, frecuencia, duracion, id_consulta)
			SELECT nombre_comercial, principio_activo, dosis_default, frecuencia_default, duracion_default, @id_consulta
			FROM medicamentos_real
			ORDER BY RAND()
			LIMIT 1;

            
            SET @cant_meds := @cant_meds - 1;
        END WHILE;

        -- ======================
        -- EXAMEN (0-1)
        -- ======================
        IF RAND() < 0.5 THEN
            SET @tipo_ex := (SELECT CASE 
                WHEN @cie_codigo='J18' THEN 'Radiografía de tórax'
                WHEN @cie_codigo='N39' THEN 'Uroanálisis'
                WHEN @cie_codigo='J45' THEN 'Espirometría'
                WHEN @cie_codigo='A09' THEN 'Coprológico'
                ELSE 'Examen general de laboratorio'
            END);

            SET @resultado_ex := CONCAT(
                @tipo_ex, 
                ' realizado sin complicaciones. Los valores obtenidos muestran tendencias compatibles con el diagnóstico principal. Se recomienda seguimiento clínico en los próximos días para evaluar progreso.'
            );

            INSERT INTO examen(tipo_examen, resultado, fecha_examen, id_consulta)
            VALUES(@tipo_ex, @resultado_ex, @fecha_consulta, @id_consulta);
        END IF;

    END LOOP;

    CLOSE cur;

END$$

DELIMITER ;


CALL generar_consultas();


