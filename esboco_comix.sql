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
    cli_is_ativo      BOOLEAN NOT NULL,
    cli_tel_id        NUMERIC(6) NOT NULL
);

CREATE TABLE telefones (
    tel_id     NUMERIC(6) PRIMARY KEY,
    tel_ddd    CHAR(2) NOT NULL,
    tel_numero CHAR(9) NOT NULL,
    tel_tipo   VARCHAR(20) NOT NULL
);

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_tel FOREIGN KEY (cli_tel_id) REFERENCES telefones(tel_id);

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

CREATE TRIGGER trg_set_cli_id
BEFORE INSERT ON clientes
FOR EACH ROW
EXECUTE FUNCTION set_cli_id();

CREATE SEQUENCE tel_sq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE FUNCTION set_tel_id() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.tel_id := nextval('tel_sq');
    RETURN NEW;
END;
$$;

CREATE TRIGGER tg_set_tel_id
BEFORE INSERT ON telefones
FOR EACH ROW
EXECUTE FUNCTION set_tel_id();

INSERT INTO clientes(
	cli_nome, cli_genero, cli_dt_nascimento, cli_cpf, cli_email, cli_hash_senha, cli_salt_senha, cli_ranking, cli_is_ativo, cli_tel_id)
	VALUES ('Jorge', 'A', '2002-02-02', '9999999999', 'a', 'a', 'a', 1, true, 1);

INSERT INTO telefones(
	tel_id, tel_ddd, tel_numero, tel_tipo)
	VALUES (3, 'aa', 'aaaaaaaaa', 'a');