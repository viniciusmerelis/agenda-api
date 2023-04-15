CREATE SEQUENCE seq_servico_prestado START 1 INCREMENT 1;

CREATE TABLE servico_prestado
(
    id   BIGINT DEFAULT nextval('seq_servico_prestado') NOT NULL,
    nome VARCHAR(255)                                   NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE seq_agendamento INCREMENT 1 START 1;

CREATE TABLE agendamento
(
    id                     BIGINT DEFAULT nextval('seq_agendamento') NOT NULL,
    horario                TIMESTAMP                                 NOT NULL,
    cliente_id             BIGINT                                    NOT NULL,
    usuario_colaborador_id BIGINT                                    NOT NULL,
    servico_prestado_id    BIGINT                                    NOT NULL,
    data_criacao           TIMESTAMP                                 NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    CONSTRAINT fk_usuario_colaborador_id FOREIGN KEY (usuario_colaborador_id) REFERENCES usuario (id),
    CONSTRAINT fk_servico_prestado_id FOREIGN KEY (servico_prestado_id) REFERENCES servico_prestado (id)
);

CREATE SEQUENCE seq_atendimento INCREMENT 1 START 1;

CREATE TABLE atendimento
(
    id                     BIGINT DEFAULT nextval('seq_atendimento') NOT NULL,
    agendamento_id         BIGINT,
    cliente_id             BIGINT,
    usuario_colaborador_id BIGINT                                    NOT NULL,
    valor_total            NUMERIC(5, 2)                             NOT NULL,
    data_criacao           TIMESTAMP                                 NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_atendimento_cliente_id FOREIGN KEY (cliente_id) REFERENCES cliente (id),
    CONSTRAINT fk_atendimento_usuario_colaborador_id FOREIGN KEY (usuario_colaborador_id) REFERENCES usuario (id),
    CONSTRAINT fk_agendamento_id FOREIGN KEY (agendamento_id) REFERENCES agendamento (id)
);

CREATE TABLE atendimento_servico_prestado
(
    atendimento_id      BIGINT        NOT NULL,
    servico_prestado_id BIGINT        NOT NULL,
    valor               NUMERIC(5, 2) NOT NULL,
    CONSTRAINT fk_asp_atendimento_id FOREIGN KEY (atendimento_id) REFERENCES atendimento (id),
    CONSTRAINT fk_asp_servico_prestado_id FOREIGN KEY (servico_prestado_id) REFERENCES servico_prestado (id)
);

