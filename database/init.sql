CREATE TABLE inventario.Categorias (
	iId BIGINT UNSIGNED auto_increment NOT NULL,
	nombre varchar(100) NULL,
	codigo_prefijo VARCHAR(100) NULL,
	CONSTRAINT Categorias_PK PRIMARY KEY (iId)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='Catalogo de categorias';

CREATE TABLE inventario.activos_tecnologicos (
	id UUID NOT NULL,
	identificador_tecnico varchar(100) NULL,
	folio_inventario varchar(100) NULL,
	numero_de_serie varchar(100) NOT NULL,
	marca_modelo varchar(100) NULL,
	estado varchar(100) NULL,
	costo_adquisicion DOUBLE NULL,
	fecha_hora TIMESTAMP NULL,
	categoria BIGINT UNSIGNED NULL,
	CONSTRAINT activos_tecnologicos_pk PRIMARY KEY (id),
	CONSTRAINT activos_tecnologicos_Categorias_FK FOREIGN KEY (categoria) REFERENCES inventario.Categorias(iId)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='Actrivos tecnologicos';