USE Loom;

--   TABLA: ROL
CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

--   TABLA: USUARIO
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(20) NOT NULL UNIQUE,
    primer_nombre VARCHAR(50) NOT NULL,
    segundo_nombre VARCHAR(50),
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    direccion VARCHAR(100),
    ciudad VARCHAR(50) DEFAULT 'Bogotá',
    contrasena VARCHAR(255),
    id_rol INT,
    username VARCHAR(50) NOT NULL UNIQUE,

    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

--   TABLA: CONTACTO
CREATE TABLE contacto (
    id_contacto INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('TEL','EMAIL') NOT NULL,
    valor VARCHAR(100) NOT NULL,
    id_usuario INT NOT NULL,

    UNIQUE (tipo, valor, id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

--   TABLA: EPS
CREATE TABLE eps (
    id_eps INT AUTO_INCREMENT PRIMARY KEY,
    nombre_eps VARCHAR(100) NOT NULL UNIQUE,
    tipo_regimen VARCHAR(50),
    correo VARCHAR(100) UNIQUE
);

-- =======================
--   TABLA: CONTACTO EPS
-- =======================
CREATE TABLE contacto_eps (
    id_contacto_eps INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('TEL','DIRE') NOT NULL,
    valor VARCHAR(100) NOT NULL,
    id_eps_fk INT NOT NULL,

    UNIQUE (tipo, valor, id_eps_fk),
    FOREIGN KEY (id_eps_fk) REFERENCES eps(id_eps)
);

--   TABLA: PACIENTE
CREATE TABLE paciente (
    id_paciente INT PRIMARY KEY,
    fecha_afiliacion DATE NOT NULL,
    id_eps INT NOT NULL,
    tipo_afiliacion VARCHAR(50),

    FOREIGN KEY (id_paciente) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_eps) REFERENCES eps(id_eps)
);

--   TABLA: MEDICO
CREATE TABLE medico (
    id_medico INT PRIMARY KEY,
    id_eps INT NOT NULL,
    tarjeta_profesional VARCHAR(50) NOT NULL UNIQUE,
    especialidad VARCHAR(100),

    FOREIGN KEY (id_medico) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_eps) REFERENCES eps(id_eps)
);

--   TABLA: CONSULTA
CREATE TABLE consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    id_eps INT NOT NULL,
    fecha_consulta DATE NOT NULL,
    fecha_validez DATE NOT NULL,

    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico),
    FOREIGN KEY (id_eps) REFERENCES eps(id_eps)
);

--   TABLA: DIAGNOSTICO
CREATE TABLE diagnostico (
    id_diagnostico INT AUTO_INCREMENT PRIMARY KEY,
    codigo_cie10 VARCHAR(10) NOT NULL,
    descripcion VARCHAR(255),
    id_consulta INT NOT NULL,

    FOREIGN KEY (id_consulta) REFERENCES consulta(id_consulta)
);

--   TABLA: MEDICAMENTO
CREATE TABLE medicamento (
    id_medicamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_comercial VARCHAR(100),
    principio_activo VARCHAR(100),
    dosis VARCHAR(50),
    frecuencia VARCHAR(50),
    duracion INT,
    id_consulta INT NOT NULL,

    FOREIGN KEY (id_consulta) REFERENCES consulta(id_consulta)
);

--   TABLA: EXAMEN
CREATE TABLE examen (
    id_examen INT AUTO_INCREMENT PRIMARY KEY,
    tipo_examen VARCHAR(100),
    resultado VARCHAR(255),
    fecha_examen DATE,
    id_consulta INT NOT NULL,

    FOREIGN KEY (id_consulta) REFERENCES consulta(id_consulta)
);
