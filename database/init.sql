CREATE TABLE inventario.categorias (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	nombre varchar(100) NOT NULL,
	codigo_prefijo VARCHAR(100) NOT NULL,
	CONSTRAINT Categorias_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='Catalogo de categorias';

INSERT INTO inventario.categorias
(id, nombre, codigo_prefijo)
VALUES(0, "Laptop", "LAP");

CREATE TABLE `activos_tecnologicos` (
  `identificador_tecnico` uuid NOT NULL,
  `folio_inventario` varchar(100) NOT NULL,
  `numero_de_serie` varchar(100) NOT NULL,
  `marca_modelo` varchar(100) DEFAULT NULL,
  `estado` varchar(100) NOT NULL,
  `costo_adquisicion` double DEFAULT NULL,
  `fecha_hora` timestamp NULL DEFAULT NULL,
  `categoria` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`identificador_tecnico`),
  UNIQUE KEY `activos_tecnologicos_unique` (`numero_de_serie`),
  KEY `activos_tecnologicos_Categorias_FK` (`categoria`),
  CONSTRAINT `activos_tecnologicos_Categorias_FK` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci COMMENT='Activos tecnologicos';


CREATE USER IF NOT EXISTS 'keycloak'@'%' IDENTIFIED BY 'contraseña';
CREATE DATABASE IF NOT EXISTS keycloak;
GRANT ALL PRIVILEGES ON keycloak.* TO 'keycloak'@'%';
FLUSH PRIVILEGES;