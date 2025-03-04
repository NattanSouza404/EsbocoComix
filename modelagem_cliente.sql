-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2025-03-01 17:25:45 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE bandeiras_cartao_credito (
    bcc_id   NUMBER(2) NOT NULL,
    bcc_nome VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE bandeiras_cartao_credito ADD CONSTRAINT pk_bcc PRIMARY KEY ( bcc_id );

CREATE TABLE cartoes_credito (
    cre_id               NUMBER(9) NOT NULL,
    cre_numero           CHAR(16) NOT NULL,
    cre_nome_impresso    VARCHAR2(100) NOT NULL,
    cre_codigo_seguranca CHAR(3) NOT NULL,
    cre_is_preferencial  CHAR(1) NOT NULL,
    cre_bcc_id           NUMBER(2) NOT NULL,
    cre_cli_id           NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE cartoes_credito ADD CONSTRAINT pk_cre PRIMARY KEY ( cre_id );

CREATE TABLE categorias (
    cat_id   unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    cat_nome unknown 
--  ERROR: Datatype UNKNOWN is not allowed 

)
LOGGING;

CREATE TABLE categorias_quadrinho (
    cqu_cat_id unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    cqu_qua_id unknown 
--  ERROR: Datatype UNKNOWN is not allowed 

)
LOGGING;

CREATE TABLE clientes (
    cli_id            NUMBER(6) NOT NULL,
    cli_nome          VARCHAR2(100) NOT NULL,
    cli_genero        VARCHAR2(20) NOT NULL,
    cli_dt_nascimento DATE NOT NULL,
    cli_cpf           CHAR(11) NOT NULL,
    cli_email         VARCHAR2(100) NOT NULL,
    cli_hash_senha    VARCHAR2(70) NOT NULL,
    cli_salt_senha    VARCHAR2(24) NOT NULL,
    cli_ranking       NUMBER(6) NOT NULL,
    cli_is_ativo      CHAR(1) NOT NULL,
    cli_tel_id        NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE clientes ADD CONSTRAINT pk_cli PRIMARY KEY ( cli_id );

CREATE TABLE enderecos (
    end_id               NUMBER(6) NOT NULL,
    end_frase_curta      VARCHAR2(30) NOT NULL,
    end_logradouro       VARCHAR2(100) NOT NULL,
    end_tipo_logradouro  VARCHAR2(20) NOT NULL,
    end_tipo_residencial VARCHAR2(20) NOT NULL,
    end_numero           NUMBER(3) NOT NULL,
    end_bairro           VARCHAR2(50) NOT NULL,
    end_cep              CHAR(8) NOT NULL,
    end_cidade           VARCHAR2(50) NOT NULL,
    end_estado           VARCHAR2(20) NOT NULL,
    end_pais             VARCHAR2(30) NOT NULL,
    end_is_residencial   CHAR(1) NOT NULL,
    end_is_entrega       CHAR(1) NOT NULL,
    end_is_cobranca      CHAR(1) NOT NULL,
    end_observacoes      VARCHAR2(50),
    end_cli_id           NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE enderecos ADD CONSTRAINT pk_end PRIMARY KEY ( end_id );

CREATE TABLE log_transacao (
    data_hora           unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    usuario_responsavel unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    dados_alterados     unknown 
--  ERROR: Datatype UNKNOWN is not allowed 

)
LOGGING;

CREATE TABLE quadrinhos (
    qua_id                 NUMBER(6) NOT NULL,
    qua_ano                unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_titulo             unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_editora            unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_edicao             unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_isbn               unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_numero_paginas     unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_sinopse            unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_altura             unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_largura            unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_peso               unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_profundidade       unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_grupo_precificacao unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_codigo_barras      unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    qua_is_ativo           unknown 
--  ERROR: Datatype UNKNOWN is not allowed 

)
LOGGING;

ALTER TABLE quadrinhos ADD CONSTRAINT quadrinhos_pk PRIMARY KEY ( qua_id );

CREATE TABLE telefones (
    tel_id     NUMBER(6) NOT NULL,
    tel_ddd    CHAR(2) NOT NULL,
    tel_numero CHAR(9) NOT NULL,
    tel_tipo   VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE telefones ADD CONSTRAINT pk_tel PRIMARY KEY ( tel_id );

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_tel FOREIGN KEY ( cli_tel_id )
        REFERENCES telefones ( tel_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_bcc FOREIGN KEY ( cre_bcc_id )
        REFERENCES bandeiras_cartao_credito ( bcc_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_cli FOREIGN KEY ( cre_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli FOREIGN KEY ( end_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             9
-- CREATE INDEX                             0
-- ALTER TABLE                             10
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                  21
-- WARNINGS                                 0
