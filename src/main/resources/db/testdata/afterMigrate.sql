DELETE FROM grupo;
DELETE FROM permissao;

ALTER SEQUENCE seq_grupo RESTART;

INSERT INTO grupo(id, nome) VALUES (nextval('seq_grupo'), 'Administrador');
INSERT INTO grupo(id, nome) VALUES (nextval('seq_grupo'), 'Secretária');
INSERT INTO grupo(id, nome) VALUES (nextval('seq_grupo'), 'Colaborador');

INSERT INTO permissao(id, nome, descricao) VALUES (1, 'EDITAR_AGENDA', 'Permite editar agenda');
INSERT INTO permissao(id, nome, descricao) VALUES (2, 'EDITAR_USUARIO', 'Permite editar usuários');
INSERT INTO permissao(id, nome, descricao) VALUES (3, 'CONSULTAR_AGENDA', 'Permite consultar agenda');
