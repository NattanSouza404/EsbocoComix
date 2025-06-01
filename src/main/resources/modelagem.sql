-- Gerado por Oracle SQL Developer Data Modeler 22.2.0.165.1149
--   em:        2025-05-01 23:29:59 BRT
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

CREATE TABLE cartoes_credito_pedido (
    ccp_cre_id NUMBER(9) NOT NULL,
    ccp_ped_id NUMBER(9) NOT NULL,
    cpp_valor  NUMBER(6, 2) NOT NULL
)
LOGGING;

ALTER TABLE cartoes_credito_pedido ADD CONSTRAINT pk_ccp PRIMARY KEY ( ccp_cre_id,
                                                                       ccp_ped_id );

CREATE TABLE categorias (
    cat_id   NUMBER(6) NOT NULL,
    cat_nome VARCHAR2(30) NOT NULL
)
LOGGING;

ALTER TABLE categorias ADD CONSTRAINT pk_cat PRIMARY KEY ( cat_id );

CREATE TABLE categorias_quadrinho (
    cqu_cat_id NUMBER(6) NOT NULL,
    cqu_qua_id NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE categorias_quadrinho ADD CONSTRAINT pk_cqu PRIMARY KEY ( cqu_cat_id,
                                                                     cqu_qua_id );

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
    cli_tel_tipo      VARCHAR2(20) NOT NULL,
    cli_tel_ddd       CHAR(2) NOT NULL,
    cli_tel_numero    CHAR(9) NOT NULL,
    cli_is_ativo      CHAR(1) NOT NULL
)
LOGGING;

ALTER TABLE clientes ADD CONSTRAINT pk_cli PRIMARY KEY ( cli_id );

CREATE TABLE cupons (
    cup_id             NUMBER(9) NOT NULL,
    cup_valor          NUMBER(10, 2) NOT NULL,
    cup_is_promocional CHAR(1) NOT NULL,
    cup_is_troca       CHAR(1) NOT NULL,
    cup_is_ativo       CHAR(1) NOT NULL,
    cup_cli_id         NUMBER(6) NOT NULL
)
LOGGING;

ALTER TABLE cupons ADD CONSTRAINT pk_cup PRIMARY KEY ( cup_id );

CREATE TABLE cupons_pedido (
    cpe_cup_id NUMBER(9) NOT NULL,
    cpe_ped_id NUMBER(9) NOT NULL
)
LOGGING;

ALTER TABLE cupons_pedido ADD CONSTRAINT pk_cpe PRIMARY KEY ( cpe_cup_id,
                                                              cpe_ped_id );

CREATE TABLE enderecos (
    end_id               NUMBER(6) NOT NULL,
    end_frase_curta      VARCHAR2(30) NOT NULL,
    end_logradouro       VARCHAR2(100) NOT NULL,
    end_tipo_logradouro  VARCHAR2(20) NOT NULL,
    end_tipo_residencial VARCHAR2(20) NOT NULL,
    end_numero           VARCHAR2(10) NOT NULL,
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

CREATE TABLE entrada_estoque (
    ees_id      NUMBER(6) NOT NULL,
    ees_qua_id      NUMBER(6) NOT NULL,
    ees_quantidade  NUMBER(5) NOT NULL,
    ees_valor_custo NUMBER(6, 2) NOT NULL,
    ees_dt_entrada  TIMESTAMP NOT NULL,
    ees_fornecedor  VARCHAR2(100) NOT NULL
)
LOGGING;

ALTER TABLE entrada_estoque ADD CONSTRAINT pk_ees PRIMARY KEY ( ees_id );

CREATE TABLE estoque (
    est_qua_id           NUMBER(6) NOT NULL,
    est_quantidade_total NUMBER(6)
)
LOGGING;

ALTER TABLE estoque ADD CONSTRAINT pk_est PRIMARY KEY ( est_qua_id );

CREATE TABLE grupos_precificacao (
    gpr_id NUMBER(3) NOT NULL,
    gpr_porcentagem NUMBER(2) NOT NULL,
    gpr_nome VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE grupos_precificacao ADD CONSTRAINT pk_gpr PRIMARY KEY ( gpr_id );

CREATE TABLE itens_pedido (
    ite_ped_id     NUMBER(9) NOT NULL,
    ite_qua_id     NUMBER(6) NOT NULL,
    ite_quantidade NUMBER(2) NOT NULL,
    ite_status     VARCHAR2(20)
)
LOGGING;

ALTER TABLE itens_pedido ADD CONSTRAINT pk_ite PRIMARY KEY ( ite_ped_id,
                                                             ite_qua_id );

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

CREATE TABLE pedidos (
    ped_id          NUMBER(9) NOT NULL,
    ped_cli_id      NUMBER(6) NOT NULL,
    ped_status      VARCHAR2(20) NOT NULL,
    ped_end_id      NUMBER(6) NOT NULL,
    ped_valor_total NUMBER(10, 2) NOT NULL,
    ped_valor_frete NUMBER(6, 2) NOT NULL,
    ped_data        TIMESTAMP NOT NULL
)
LOGGING;

ALTER TABLE pedidos ADD CONSTRAINT pk_ped PRIMARY KEY ( ped_id );

CREATE TABLE quadrinhos (
    qua_id             NUMBER(6) NOT NULL,
    qua_preco          NUMBER(6, 2) NOT NULL,
    qua_autor          VARCHAR2(100) NOT NULL,
    qua_ano            DATE NOT NULL,
    qua_titulo         VARCHAR2(100) NOT NULL,
    qua_editora        VARCHAR2(50) NOT NULL,
    qua_edicao         NUMBER(3) NOT NULL,
    qua_isbn           CHAR(13) NOT NULL,
    qua_numero_paginas NUMBER(5) NOT NULL,
    qua_sinopse        CLOB NOT NULL,
    qua_altura_cm      NUMBER(3) NOT NULL,
    qua_largura_cm     NUMBER(3) NOT NULL,
    qua_profundidade   NUMBER(3) NOT NULL,
    qua_peso_gramas    NUMBER(5) NOT NULL,
    qua_codigo_barras  VARCHAR2(13) NOT NULL,
    qua_is_ativo       CHAR(1) NOT NULL,
    qua_gpr_id         NUMBER(3) NOT NULL,
    qua_url_imagem     VARCHAR2(255)
)
LOGGING;

ALTER TABLE quadrinhos ADD CONSTRAINT pk_qua PRIMARY KEY ( qua_id );

ALTER TABLE cartoes_credito_pedido
    ADD CONSTRAINT fk_ccp_cre FOREIGN KEY ( ccp_cre_id )
        REFERENCES cartoes_credito ( cre_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_credito_pedido
    ADD CONSTRAINT fk_ccp_ped FOREIGN KEY ( ccp_ped_id )
        REFERENCES pedidos ( ped_id )
    NOT DEFERRABLE;

ALTER TABLE cupons_pedido
    ADD CONSTRAINT fk_cpe_cup FOREIGN KEY ( cpe_cup_id )
        REFERENCES cupons ( cup_id )
    NOT DEFERRABLE;

ALTER TABLE cupons_pedido
    ADD CONSTRAINT fk_cpe_ped FOREIGN KEY ( cpe_ped_id )
        REFERENCES pedidos ( ped_id )
    NOT DEFERRABLE;

ALTER TABLE categorias_quadrinho
    ADD CONSTRAINT fk_cqu_cat FOREIGN KEY ( cqu_cat_id )
        REFERENCES categorias ( cat_id )
    NOT DEFERRABLE;

ALTER TABLE categorias_quadrinho
    ADD CONSTRAINT fk_cqu_qua FOREIGN KEY ( cqu_qua_id )
        REFERENCES quadrinhos ( qua_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_bcc FOREIGN KEY ( cre_bcc_id )
        REFERENCES bandeiras_cartao_credito ( bcc_id )
    NOT DEFERRABLE;

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_cli FOREIGN KEY ( cre_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE cupons
    ADD CONSTRAINT fk_cup_cli FOREIGN KEY ( cup_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE entrada_estoque
    ADD CONSTRAINT fk_ees_qua FOREIGN KEY ( ees_qua_id )
        REFERENCES quadrinhos ( qua_id )
    NOT DEFERRABLE;

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli FOREIGN KEY ( end_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE estoque
    ADD CONSTRAINT fk_est_qua FOREIGN KEY ( est_qua_id )
        REFERENCES quadrinhos ( qua_id )
    NOT DEFERRABLE;

ALTER TABLE itens_pedido
    ADD CONSTRAINT fk_ite_ped FOREIGN KEY ( ite_ped_id )
        REFERENCES pedidos ( ped_id )
    NOT DEFERRABLE;

ALTER TABLE itens_pedido
    ADD CONSTRAINT fk_ite_qua FOREIGN KEY ( ite_qua_id )
        REFERENCES quadrinhos ( qua_id )
    NOT DEFERRABLE;

ALTER TABLE pedidos
    ADD CONSTRAINT fk_ped_cli FOREIGN KEY ( ped_cli_id )
        REFERENCES clientes ( cli_id )
    NOT DEFERRABLE;

ALTER TABLE pedidos
    ADD CONSTRAINT fk_ped_end FOREIGN KEY ( ped_end_id )
        REFERENCES enderecos ( end_id )
    NOT DEFERRABLE;

ALTER TABLE quadrinhos
    ADD CONSTRAINT fk_qua_gpr FOREIGN KEY ( qua_gpr_id )
        REFERENCES grupos_precificacao ( gpr_id )
    NOT DEFERRABLE;



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            16
-- CREATE INDEX                             0
-- ALTER TABLE                             31
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
-- ERRORS                                   3
-- WARNINGS                                 0
