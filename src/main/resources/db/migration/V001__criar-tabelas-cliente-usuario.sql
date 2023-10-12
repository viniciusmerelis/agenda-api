CREATE SEQUENCE seq_cliente INCREMENT 1 START 1;

CREATE TABLE cliente
(
    id       BIGINT DEFAULT nextval('seq_cliente') NOT NULL,
    nome     VARCHAR(150)                          NOT NULL,
    telefone VARCHAR(11)                           NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE SEQUENCE seq_usuario INCREMENT 1 START 1;

CREATE TABLE usuario
(
    id               BIGINT DEFAULT nextval('seq_usuario') NOT NULL,
    nome             VARCHAR(100)                          NOT NULL,
    telefone         VARCHAR(11)                           NOT NULL UNIQUE,
    email            VARCHAR(255)                          NOT NULL UNIQUE,
    senha            VARCHAR(100)                          NOT NULL,
    data_cadastro    TIMESTAMP,
    data_atualizacao TIMESTAMP,
    PRIMARY KEY (id)
);
