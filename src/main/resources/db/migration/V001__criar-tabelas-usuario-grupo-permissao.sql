CREATE SEQUENCE seq_usuario INCREMENT 1 START 1;

CREATE TABLE usuario
(
    id       INTEGER DEFAULT nextval('seq_usuario') NOT NULL,
    nome     VARCHAR(100)                           NOT NULL,
    telefone VARCHAR(11)                            NOT NULL UNIQUE,
    email    VARCHAR(255)                           NOT NULL UNIQUE,
    senha    VARCHAR(100)                           NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE seq_grupo INCREMENT 1 START 1;

CREATE TABLE grupo
(
    id   INTEGER DEFAULT nextval('seq_grupo') NOT NULL,
    nome VARCHAR(100)                         NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE seq_permissao INCREMENT 1 START 1;

CREATE TABLE permissao
(
    id        INTEGER DEFAULT nextval('seq_permissao') NOT NULL,
    nome      VARCHAR(100)                             NOT NULL,
    descricao VARCHAR(10)                              NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE grupo_permissao
(
    grupo_id     INTEGER NOT NULL,
    permissao_id INTEGER NOT NULL,
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
    usuario_id INTEGER NOT NULL,
    grupo_id   INTEGER NOT NULL,
    PRIMARY KEY (usuario_id, grupo_id)
);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usuario_grupo_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usuario_grupo_grupo
        FOREIGN KEY (grupo_id) REFERENCES grupo (id);
