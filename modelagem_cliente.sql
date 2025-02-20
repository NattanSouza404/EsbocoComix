-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2025-02-19 12:28:33 BRT
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

CREATE TABLE cartoes_cliente (
    ctc_cli_id          NUMBER(6) NOT NULL,
    ctc_cre_id          NUMBER(9) NOT NULL,
    ctc_is_preferencial CHAR(1) NOT NULL
)
LOGGING;

ALTER TABLE cartoes_cliente ADD CONSTRAINT pk_ctc PRIMARY KEY ( ctc_cli_id,
                                                                ctc_cre_id );

CREATE TABLE cartoes_credito (
    cre_id               NUMBER(9) NOT NULL,
    cre_numero           CHAR(16) NOT NULL,
    cre_nome_impresso    VARCHAR2(100) NOT NULL,
    cre_codigo_seguranca CHAR(3) NOT NULL,
    cre_bandeira_cartao  NUMBER(2) NOT NULL
)
LOGGING;

ALTER TABLE cartoes_credito ADD CONSTRAINT pk_cre PRIMARY KEY ( cre_id );

CREATE TABLE clientes (
    cli_id            NUMBER(6) NOT NULL,
    cli_nome          VARCHAR2(100) NOT NULL,
    cli_dt_nascimento DATE NOT NULL,
    cli_cpf           CHAR(11) NOT NULL,
    cli_email         VARCHAR2(100) NOT NULL,
    cli_senha         VARCHAR2(16) NOT NULL,
    cli_gen_id        NUMBER(2) NOT NULL,
    cli_ranking       NUMBER(6)
)
LOGGING;

ALTER TABLE clientes ADD CONSTRAINT pk_cli PRIMARY KEY ( cli_id );

CREATE TABLE endereco_cobranca (
    eco_cli_id NUMBER(6) NOT NULL,
    eco_end_id NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE endereco_cobranca ADD CONSTRAINT pk_eco PRIMARY KEY ( eco_end_id,
                                                                  eco_cli_id );

CREATE TABLE endereco_entrega (
    een_cli_id NUMBER(6) NOT NULL,
    een_end_id NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE endereco_entrega ADD CONSTRAINT pk_een PRIMARY KEY ( een_end_id,
                                                                 een_cli_id );

CREATE TABLE enderecos (
    end_id          NUMBER(6) NOT NULL,
    end_logradouro  VARCHAR2(100) NOT NULL,
    end_tlo_id      NUMBER(2) NOT NULL,
    end_tre_id      NUMBER(2) NOT NULL,
    end_numero      NUMBER(3) NOT NULL,
    end_bairro      VARCHAR2(50) NOT NULL,
    end_cep         CHAR(8) NOT NULL,
    end_cidade      VARCHAR2(50) NOT NULL,
    end_estado      VARCHAR2(20) NOT NULL,
    end_pais        VARCHAR2(30) NOT NULL,
    end_observacoes VARCHAR2(50)
)
LOGGING;

ALTER TABLE enderecos ADD CONSTRAINT pk_end PRIMARY KEY ( end_id );

CREATE TABLE generos (
    gen_id   NUMBER(2) NOT NULL,
    gen_nome VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE generos ADD CONSTRAINT pk_gen PRIMARY KEY ( gen_id );

CREATE TABLE produtos (
    prd_id     unknown 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    prd_aut_id unknown 
--  ERROR: Datatype UNKNOWN is not allowed 

)
LOGGING;

CREATE TABLE telefones (
    tel_id     NUMBER(6) NOT NULL,
    tel_ddd    CHAR(2) NOT NULL,
    tel_numero CHAR(9) NOT NULL,
    tel_tpt_id NUMBER(2) NOT NULL,
    tel_cli_id NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE telefones ADD CONSTRAINT pk_tel PRIMARY KEY ( tel_id );

CREATE TABLE tipos_logradouro (
    tlo_id   NUMBER(2) NOT NULL,
    tlo_nome VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE tipos_logradouro ADD CONSTRAINT pk_tpl PRIMARY KEY ( tlo_id );

CREATE TABLE tipos_residencia (
    tre_id   NUMBER(2) NOT NULL,
    tre_nome VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE tipos_residencia ADD CONSTRAINT pk_tre PRIMARY KEY ( tre_id );

CREATE TABLE tipos_telefone (
    tpt_id   NUMBER(2) NOT NULL,
    tpt_nome VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE tipos_telefone ADD CONSTRAINT pk_tpt PRIMARY KEY ( tpt_id );

CREATE TABLE vendas 
    
    -- No Columns 

LOGGING;

ALTER TABLE clientes
    ADD CONSTRAINT fk_cli_gen FOREIGN KEY ( cli_gen_id )
        REFERENCES generos ( gen_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_bcc FOREIGN KEY ( cre_bandeira_cartao )
        REFERENCES bandeiras_cartao_credito ( bcc_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_cliente
    ADD CONSTRAINT fk_ctc_cli FOREIGN KEY ( ctc_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_cliente
    ADD CONSTRAINT fk_ctc_cre FOREIGN KEY ( ctc_cre_id )
        REFERENCES cartoes_credito ( cre_id )
    NOT DEFERRABLE;

ALTER TABLE endereco_cobranca
    ADD CONSTRAINT fk_eco_cli FOREIGN KEY ( eco_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE endereco_cobranca
    ADD CONSTRAINT fk_eco_end FOREIGN KEY ( eco_end_id )
        REFERENCES enderecos ( end_id )
    NOT DEFERRABLE;

ALTER TABLE endereco_entrega
    ADD CONSTRAINT fk_een_cli FOREIGN KEY ( een_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE endereco_entrega
    ADD CONSTRAINT fk_een_end FOREIGN KEY ( een_end_id )
        REFERENCES enderecos ( end_id )
    NOT DEFERRABLE;

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_tlo FOREIGN KEY ( end_tlo_id )
        REFERENCES tipos_logradouro ( tlo_id )
    NOT DEFERRABLE;

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_tre FOREIGN KEY ( end_tre_id )
        REFERENCES tipos_residencia ( tre_id )
    NOT DEFERRABLE;

ALTER TABLE telefones
    ADD CONSTRAINT fk_tel_cli FOREIGN KEY ( tel_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE telefones
    ADD CONSTRAINT fk_tel_tpt FOREIGN KEY ( tel_tpt_id )
        REFERENCES tipos_telefone ( tpt_id )
    NOT DEFERRABLE;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             0
-- ALTER TABLE                             24
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
-- ERRORS                                   2
-- WARNINGS                                 0
