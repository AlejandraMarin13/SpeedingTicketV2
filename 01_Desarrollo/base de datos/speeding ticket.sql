DROP SCHEMA IF EXISTS proyecto ;
CREATE SCHEMA proyecto;

CREATE TABLE proyecto.Cuenta (  
    numero_documento VARCHAR (45) comment 'Por medio de este campo se va a realizar el registro de documento.',
    tipo_documento VARCHAR(45) comment 'Con este campo se referencia el tipo de documento.' ,
    primer_nombre VARCHAR(45) NOT NULL comment 'Este campo llevara el nombre de la persona al registrarse.',
    segundo_nombre VARCHAR(45) comment 'Este campo llevara el segundo nombre de la persona al registrarse.',
    primer_apellido VARCHAR(45) NOT NULL comment 'Este campo llevara el primer apellido de la persona al registrarse.',
    segundo_apellido VARCHAR(45) NOT NULL comment 'Este campo llevara el segundo apellido de la persona al registrarse.',
	perfil VARCHAR(45) NOT NULL comment 'Con este campo se referenciara el rol que lleva el usuario..', 
    foto LONGBLOB NOT NULL comment 'Aqui se insertara la foto del usuario.',
    PRIMARY KEY (numero_documento,tipo_documento)
);

CREATE TABLE proyecto.Usuario(
    correo VARCHAR(45) NOT NULL comment ' este campo es para registrar el correo del usuario',
    contraseña VARCHAR(45) NOT NULL comment 'Este campo ayuda logear la cuenta del usuario.',
    cuenta_numero_documento VARCHAR(45) NOT NULL,
    cuenta_tipo_documento VARCHAR(45) NOT NULL,
	rol VARCHAR(45) NOT NULL comment 'Con este campo se referenciara el rol que lleva el usuario..', 
    FOREIGN KEY (cuenta_numero_documento,cuenta_tipo_documento)
        REFERENCES proyecto.cuenta(numero_documento,tipo_documento) on update cascade
);


CREATE TABLE proyecto.Registro (
    id_registro INT NOT NULL auto_increment comment'este campo sirve para identificar cada usuario en la base de datos', 
    Cuenta_numero_documento varchar(45) NOT NULL,
    Cuenta_tipo_documento VARCHAR(45) NOT NULL,
    fecha_hora_entrada DATETIME NOT NULL comment 'resgistra hora de entrada',
    fecha_hora_salida DATETIME comment 'registra la hora de salida',
    motivo_visita  VARCHAR(250) comment 'registrara el motivo de la visita',
    rol VARCHAR(45) NOT NULL comment 'el usuario con rol de administrador tendra acceso al sistema',
    
    PRIMARY KEY (id_registro,Cuenta_numero_documento,Cuenta_tipo_documento),
    FOREIGN KEY (cuenta_numero_documento , cuenta_tipo_documento)
        REFERENCES proyecto.cuenta(numero_documento ,tipo_documento) on update cascade
);

CREATE TABLE proyecto.Equipo (
    id_equipo VARCHAR(45) NOT NULL PRIMARY KEY comment 'este campo sirve para identificar el equipo de cada usuario en la base de datos',
    marca VARCHAR(45) NOT NULL comment 'este campo se pondra lamarca de el equipo de cada usuario',
    descripcion VARCHAR(45) comment 'este campo sirve para identificar, la marca y el serial de cada uno de los usuarios'
);

CREATE TABLE proyecto.Propiedad (
    equipo_id_equipo VARCHAR(45) NOT NULL,
    Cuenta_numero_documento varchar(45) NOT NULL,
    Cuenta_tipo_documento VARCHAR(45) NOT NULL,
    FOREIGN KEY (equipo_id_equipo)
        REFERENCES proyecto.Equipo (id_equipo) on update cascade,
    FOREIGN KEY (Cuenta_numero_documento , Cuenta_tipo_documento)
        REFERENCES proyecto.Cuenta (numero_documento , tipo_documento)on update cascade,
    PRIMARY KEY (Cuenta_numero_documento , Cuenta_tipo_documento, equipo_id_equipo)
);

CREATE TABLE proyecto.Registro_equipo (
    Registro_id_registro INT auto_increment NOT NULL,
    Propietario_equipo_id_equipo VARCHAR(45) NOT NULL,
    Propietario_Cuenta_numero_documento varchar(45) NOT NULL,
    Propietario_Cuenta_tipo_documento VARCHAR(45) NOT NULL,
    FOREIGN KEY (Propietario_equipo_id_equipo , Propietario_cuenta_numero_documento , Propietario_cuenta_tipo_documento)
        REFERENCES proyecto.Propiedad (equipo_id_equipo , Cuenta_numero_documento , Cuenta_tipo_documento)on update cascade,
    FOREIGN KEY (Registro_id_registro)
        REFERENCES proyecto.Registro (id_registro),
    PRIMARY KEY (Propietario_equipo_id_equipo , Propietario_Cuenta_numero_documento , Propietario_Cuenta_tipo_documento)
);

