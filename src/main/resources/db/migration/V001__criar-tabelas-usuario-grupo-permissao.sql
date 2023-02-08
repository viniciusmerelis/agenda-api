CREATE SEQUENCE seq_usuario INCREMENT 1 START 1;

CREATE TABLE usuario
(
    id               BIGINT DEFAULT nextval('seq_usuario') NOT NULL,
    nome             VARCHAR(100)                          NOT NULL,
    telefone         VARCHAR(11)                           NOT NULL UNIQUE,
    email            VARCHAR(255)                          NOT NULL UNIQUE,
    senha            VARCHAR(100)                          NOT NULL,
    data_cadastro    TIMESTAMP                             NOT NULL,
    data_atualizacao TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE SEQUENCE seq_grupo INCREMENT 1 START 1;

CREATE TABLE grupo
(
    id   BIGINT DEFAULT nextval('seq_grupo') NOT NULL,
    nome VARCHAR(100)                        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE permissao
(
    id        BIGINT       NOT NULL,
    nome      VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE grupo_permissao
(
    grupo_id     BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    PRIMARY KEY (grupo_id, permissao_id)
);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_permissao_grupo
        FOREIGN KEY (grupo_id) REFERENCES grupo (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_permissao_permissao
        FOREIGN KEY (permissao_id) REFERENCES permissao (id);

CREATE TABLE usuario_grupo
(
    usuario_id BIGINT NOT NULL,
    grupo_id   BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, grupo_id)
);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usuario_grupo_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usuario_grupo_grupo
        FOREIGN KEY (grupo_id) REFERENCES grupo (id);
