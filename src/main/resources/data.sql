INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@sistema.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');

INSERT INTO permissao (codigo, descricao) values (1, 'ADMNISTRADOR');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
