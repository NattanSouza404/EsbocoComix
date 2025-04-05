-- Script a ser usado no SGBD PostgreSQL

-- Criacao do database
CREATE DATABASE esboco_comix;

-- Criacao da tabela
CREATE TABLE clientes (
    cli_id            NUMERIC(6) PRIMARY KEY,
    cli_nome          VARCHAR(100) NOT NULL,
    cli_genero        VARCHAR(20) NOT NULL,
    cli_dt_nascimento DATE NOT NULL,
    cli_cpf           CHAR(11) NOT NULL,
    cli_email         VARCHAR(100) NOT NULL,
    cli_hash_senha    VARCHAR(70) NOT NULL,
    cli_salt_senha    VARCHAR(24) NOT NULL,
    cli_ranking       NUMERIC(6) NOT NULL,
    cli_tel_tipo      VARCHAR(20) NOT NULL,
    cli_tel_ddd       CHAR(2) NOT NULL,
    cli_tel_numero    CHAR(9) NOT NULL,
    cli_is_ativo      BOOLEAN NOT NULL
);

CREATE TABLE enderecos (
    end_id               NUMERIC(6) PRIMARY KEY,
    end_frase_curta      VARCHAR(30) NOT NULL,
    end_logradouro       VARCHAR(100) NOT NULL,
    end_tipo_logradouro  VARCHAR(20) NOT NULL,
    end_tipo_residencial VARCHAR(20) NOT NULL,
    end_numero           VARCHAR(10) NOT NULL,
    end_bairro           VARCHAR(50) NOT NULL,
    end_cep              CHAR(8) NOT NULL,
    end_cidade           VARCHAR(50) NOT NULL,
    end_estado           VARCHAR(20) NOT NULL,
    end_pais             VARCHAR(30) NOT NULL,
    end_is_residencial   BOOLEAN NOT NULL,
    end_is_entrega       BOOLEAN NOT NULL,
    end_is_cobranca      BOOLEAN NOT NULL,
    end_observacoes      VARCHAR(50),
    end_cli_id           NUMERIC(6) NOT NULL
);

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli FOREIGN KEY ( end_cli_id ) REFERENCES clientes ( cli_id );

CREATE TABLE bandeiras_cartao_credito (
    bcc_id   NUMERIC(2) PRIMARY KEY,
    bcc_nome VARCHAR(20) NOT NULL
);

CREATE TABLE cartoes_credito (
    cre_id               NUMERIC(9) PRIMARY KEY,
    cre_numero           CHAR(16) NOT NULL,
    cre_nome_impresso    VARCHAR(100) NOT NULL,
    cre_codigo_seguranca CHAR(3) NOT NULL,
    cre_is_preferencial  BOOLEAN NOT NULL,
    cre_bcc_id           NUMERIC(2) NOT NULL,
    cre_cli_id           NUMERIC(6) NOT NULL
);

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_bcc FOREIGN KEY ( cre_bcc_id ) REFERENCES bandeiras_cartao_credito ( bcc_id );

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_cli FOREIGN KEY ( cre_cli_id ) REFERENCES clientes ( cli_id );

CREATE TABLE grupos_precificacao (
    gpr_id NUMERIC(3) PRIMARY KEY
);

CREATE SEQUENCE gpr_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_gpr_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.gpr_id := nextval('gpr_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_gpr_id
BEFORE INSERT ON grupos_precificacao
FOR EACH ROW
EXECUTE FUNCTION set_gpr_id();

CREATE TABLE quadrinhos (
    qua_id             NUMERIC(6) PRIMARY KEY,
    qua_ano            DATE NOT NULL,
    qua_titulo         VARCHAR(100) NOT NULL,
    qua_editora        VARCHAR(50) NOT NULL,
    qua_edicao         NUMERIC(3) NOT NULL,
    qua_isbn           CHAR(13) NOT NULL,
    qua_numero_paginas NUMERIC(5) NOT NULL,
    qua_sinopse        VARCHAR(255) NOT NULL,
    qua_altura_cm      NUMERIC(3) NOT NULL,
    qua_largura_cm     NUMERIC(3) NOT NULL,
    qua_profundidade   NUMERIC(3) NOT NULL,
    qua_peso_gramas    NUMERIC(5) NOT NULL,
    qua_codigo_barras  VARCHAR(13) NOT NULL,
    qua_is_ativo       BOOLEAN NOT NULL,
    qua_gpr_id         NUMERIC(3) NOT NULL
);

ALTER TABLE quadrinhos
    ADD CONSTRAINT fk_qua_gpr FOREIGN KEY ( qua_gpr_id ) REFERENCES grupos_precificacao ( gpr_id );







CREATE SEQUENCE cli_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_cli_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.cli_id := nextval('cli_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_cli_id
BEFORE INSERT ON clientes
FOR EACH ROW
EXECUTE FUNCTION set_cli_id();

CREATE SEQUENCE end_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_end_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.end_id := nextval('end_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_end_id
BEFORE INSERT ON enderecos
FOR EACH ROW
EXECUTE FUNCTION set_end_id();

CREATE SEQUENCE cre_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_cre_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.cre_id := nextval('cre_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_cre_id
BEFORE INSERT ON cartoes_credito
FOR EACH ROW
EXECUTE FUNCTION set_cre_id();

CREATE SEQUENCE bcc_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_bcc_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.bcc_id := nextval('bcc_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_bcc_id
BEFORE INSERT ON bandeiras_cartao_credito
FOR EACH ROW
EXECUTE FUNCTION set_bcc_id();

CREATE SEQUENCE qua_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_qua_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.qua_id := nextval('qua_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_qua_id
BEFORE INSERT ON quadrinhos
FOR EACH ROW
EXECUTE FUNCTION set_qua_id();