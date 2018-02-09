DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS permissao;
DROP TABLE IF EXISTS usuario_permissao;

CREATE TABLE usuario (
	codigo identity PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
);

CREATE TABLE permissao (
	codigo identity PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
);


CREATE TABLE usuario_permissao (
	codigo_usuario identity NOT NULL,
	codigo_permissao LONG NOT NULL,
	CONSTRAINT fk_codigo_usuario FOREIGN KEY(codigo_usuario) REFERENCES usuario(codigo),
	CONSTRAINT fk_codigo_permissao FOREIGN KEY(codigo_permissao) REFERENCES permissao(codigo)
);
