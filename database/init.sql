CREATE TABLE inventario.categorias (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	nombre varchar(100) NULL,
	codigo_prefijo VARCHAR(100) NULL,
	CONSTRAINT Categorias_PK PRIMARY KEY (iId)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='Catalogo de categorias';

-- inventario.activos_tecnologicos definition

CREATE TABLE `activos_tecnologicos` (
  `id` uuid NOT NULL,
  `identificador_tecnico` varchar(100) DEFAULT NULL,
  `folio_inventario` varchar(100) DEFAULT NULL,
  `numero_de_serie` varchar(100) NOT NULL,
  `marca_modelo` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `costo_adquisicion` double DEFAULT NULL,
  `fecha_hora` timestamp NULL DEFAULT NULL,
  `categoria` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `activos_tecnologicos_unique` (`numero_de_serie`),
  KEY `activos_tecnologicos_Categorias_FK` (`categoria`),
  CONSTRAINT `activos_tecnologicos_Categorias_FK` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci COMMENT='Activos tecnologicos';