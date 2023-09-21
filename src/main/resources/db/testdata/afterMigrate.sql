DELETE FROM atendimento_servico_prestado;
DELETE FROM agendamento;
DELETE FROM atendimento;
DELETE FROM servico_prestado;
DELETE FROM usuario;
DELETE FROM cliente;

ALTER SEQUENCE seq_usuario RESTART;
ALTER SEQUENCE seq_agendamento RESTART;
ALTER SEQUENCE seq_atendimento RESTART;
ALTER SEQUENCE seq_servico_prestado RESTART;
ALTER SEQUENCE seq_cliente RESTART;

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

INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Larissa', '27971266910');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Jaqueline', '27947663161');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Fátima', '27929977391');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Heloise', '27980665683');
INSERT INTO cliente(id, nome, telefone) VALUES (nextval('seq_cliente'), 'Marina', '27987232312');

INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Aplicação');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Manutenção');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Conserto');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Corte Cabelo');
INSERT INTO servico_prestado(id, nome) VALUES (nextval('seq_servico_prestado'), 'Corte Barba');

INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-20T08:00:00', 1, 1, '2023-09-15T11:00:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-20T10:30:00', 2, 1, '2023-09-15T12:00:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-21T09:00:00', 3, 1, '2023-09-16T08:45:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-22T09:15:00', 4, 1, '2023-09-17T11:00:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-23T08:00:00', 5, 1, '2023-09-18T10:04:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-23T09:15:00', 2, 1, '2023-09-19T08:05:00');
INSERT INTO agendamento(id, horario, cliente_id, usuario_colaborador_id, data_criacao) VALUES (nextval('seq_agendamento'), '2023-09-23T10:00:00', 1, 1, '2023-09-19T11:25:00');
