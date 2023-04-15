DELETE FROM grupo_permissao;
DELETE FROM usuario_grupo;
DELETE FROM atendimento_servico_prestado;
DELETE FROM grupo;
DELETE FROM permissao;
DELETE FROM agendamento;
DELETE FROM atendimento;
DELETE FROM servico_prestado;
DELETE FROM usuario;
DELETE FROM cliente;
DELETE FROM servico_prestado;

ALTER SEQUENCE seq_grupo RESTART;
ALTER SEQUENCE seq_usuario RESTART;
ALTER SEQUENCE seq_agendamento RESTART;
ALTER SEQUENCE seq_atendimento RESTART;
ALTER SEQUENCE seq_servico_prestado RESTART;
ALTER SEQUENCE seq_cliente RESTART;
ALTER SEQUENCE seq_servico_prestado RESTART;

INSERT INTO grupo(id, nome) VALUES (nextval('seq_grupo'), 'Administrador');
INSERT INTO grupo(id, nome) VALUES (nextval('seq_grupo'), 'Secretária');
INSERT INTO grupo(id, nome) VALUES (nextval('seq_grupo'), 'Colaborador');

INSERT INTO permissao(id, nome, descricao) VALUES (1, 'EDITAR_AGENDA', 'Permite editar agenda');
INSERT INTO permissao(id, nome, descricao) VALUES (2, 'CONSULTAR_AGENDA', 'Permite consultar agenda');
INSERT INTO permissao(id, nome, descricao) VALUES (3, 'EDITAR_USUARIO', 'Permite editar usuários');
INSERT INTO permissao(id, nome, descricao) VALUES (4, 'CONSULTAR_USUARIO', 'Permite consultar usuários');

INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (1, 2);
INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (1, 3);
INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (1, 4);
INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (2, 1);
INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (2, 2);
INSERT INTO grupo_permissao(grupo_id, permissao_id) VALUES (3, 2);

INSERT INTO usuario(id, nome, telefone, email, senha, data_cadastro, data_atualizacao)
VALUES (nextval('seq_usuario'), 'Alice', '27994709408', 'alicerita@colab.com.br', '123', '2023-02-10T00:00:00', null);
INSERT INTO usuario(id, nome, telefone, email, senha, data_cadastro, data_atualizacao)
VALUES (nextval('seq_usuario'), 'Mariana', '27996818863', 'mari.carol@colab.com.br', '123', '2023-02-10T00:00:00', null);
INSERT INTO usuario(id, nome, telefone, email, senha, data_cadastro, data_atualizacao)
VALUES (nextval('seq_usuario'), 'Nicole', '27937908062', 'nicoleb@colab.com.br', '123', '2023-02-10T00:00:00', null);
INSERT INTO usuario(id, nome, telefone, email, senha, data_cadastro, data_atualizacao)
VALUES (nextval('seq_usuario'), 'Giovana', '27987787740', 'gio@sec.com', '123', '2023-02-10T00:00:00', null);
INSERT INTO usuario(id, nome, telefone, email, senha, data_cadastro, data_atualizacao)
VALUES (nextval('seq_usuario'), 'Isabella', '27997383463', 'isabella.adm@gmail.com', '123', '2023-02-10T00:00:00', null);

INSERT INTO usuario_grupo(usuario_id, grupo_id) VALUES (1, 3);
INSERT INTO usuario_grupo(usuario_id, grupo_id) VALUES (2, 3);
INSERT INTO usuario_grupo(usuario_id, grupo_id) VALUES (3, 3);
INSERT INTO usuario_grupo(usuario_id, grupo_id) VALUES (4, 2);
INSERT INTO usuario_grupo(usuario_id, grupo_id) VALUES (5, 1);

INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Larissa', '27971266910');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Jaqueline', '27947663161');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Fátima', '27929977391');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Heloise', '27980665683');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Marina', '27987232312');

INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Aplicação');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Manutenção');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Conserto');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Corte de cabelo');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Corte de barba');

INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, servico_prestado_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-03-20T08:00:00', 1, 1, 1, '2023-03-15T11:00:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, servico_prestado_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-03-20T10:30:00', 2, 1, 2, '2023-03-15T12:00:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, servico_prestado_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-03-21T09:00:00', 3, 1, 3, '2023-03-16T08:45:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, servico_prestado_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-03-22T09:15:00', 4, 1, 4, '2023-03-17T11:00:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, servico_prestado_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-03-23T08:00:00', 5, 1, 5, '2023-03-18T10:04:00');
