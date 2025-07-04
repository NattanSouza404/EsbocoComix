-- Script a ser usado no SGBD PostgreSQL

-- Criacao do database
CREATE DATABASE esboco_comix;

-- Criacao das tabelas
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
    end_is_ativo         BOOLEAN NOT NULL DEFAULT true,
    end_cli_id           NUMERIC(6) NOT NULL
);

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
    cre_is_ativo         BOOLEAN NOT NULL DEFAULT true,
    cre_bcc_id           NUMERIC(2) NOT NULL,
    cre_cli_id           NUMERIC(6) NOT NULL
);

CREATE TABLE grupos_precificacao (
    gpr_id NUMERIC(3) PRIMARY KEY,
    gpr_porcentagem NUMERIC(2) NOT NULL,
    gpr_nome VARCHAR(20) NOT NULL
);

CREATE TABLE quadrinhos (
    qua_id             NUMERIC(6) PRIMARY KEY,
    qua_autor          VARCHAR(100) NOT NULL,
    qua_ano            DATE NOT NULL,
    qua_titulo         VARCHAR(100) NOT NULL,
    qua_editora        VARCHAR(50) NOT NULL,
    qua_edicao         NUMERIC(3) NOT NULL,
    qua_isbn           CHAR(13) NOT NULL,
    qua_numero_paginas NUMERIC(5) NOT NULL,
    qua_sinopse        TEXT NOT NULL,
    qua_altura_cm      NUMERIC(3) NOT NULL,
    qua_largura_cm     NUMERIC(3) NOT NULL,
    qua_profundidade   NUMERIC(3) NOT NULL,
    qua_peso_gramas    NUMERIC(5) NOT NULL,
    qua_codigo_barras  VARCHAR(13) NOT NULL,
    qua_is_ativo       BOOLEAN NOT NULL,
    qua_gpr_id         NUMERIC(3) NOT NULL,
    qua_url_imagem     VARCHAR(255)
);

CREATE TABLE categorias (
    cat_id   NUMERIC(6) PRIMARY KEY,
    cat_nome VARCHAR(30) NOT NULL
);

CREATE TABLE categorias_quadrinho (
    cqu_cat_id NUMERIC(6) NOT NULL,
    cqu_qua_id NUMERIC(6) NOT NULL,
    PRIMARY KEY(cqu_cat_id, cqu_qua_id)
);

CREATE TABLE estoque (
    est_qua_id           NUMERIC(6) PRIMARY KEY,
    est_quantidade_total NUMERIC(6) NOT NULL
);

CREATE TABLE entrada_estoque (
    ees_id          NUMERIC(6) PRIMARY KEY,
    ees_qua_id      NUMERIC(6) NOT NULL,
    ees_quantidade  NUMERIC(5) NOT NULL,
    ees_valor_custo NUMERIC(6, 2) NOT NULL,
    ees_dt_entrada  TIMESTAMP NOT NULL,
    ees_fornecedor  VARCHAR(100) NOT NULL
);

CREATE TABLE itens_pedido (
    ite_ped_id  NUMERIC(9),
    ite_qua_id NUMERIC(6),
    ite_quantidade NUMERIC(2) NOT NULL,
    ite_valor_unitario NUMERIC(6, 2) NOT NULL,
	PRIMARY KEY(ite_ped_id, ite_qua_id)
);

CREATE TABLE pedidos (
    ped_id     NUMERIC(9) PRIMARY KEY,
    ped_cli_id NUMERIC(6) NOT NULL,
    ped_status VARCHAR(20) NOT NULL,
    ped_end_id NUMERIC(6) NOT NULL,
    ped_valor_total NUMERIC(10,2) NOT NULL,
    ped_valor_frete NUMERIC(6,2) NOT NULL,
    ped_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cartoes_credito_pedido (
    ccp_cre_id NUMERIC(9) NOT NULL,
    ccp_ped_id NUMERIC(9) NOT NULL,
    cpp_valor  NUMERIC(6, 2) NOT NULL,
    PRIMARY KEY(ccp_cre_id, ccp_ped_id)
);

CREATE TABLE cupons (
    cup_id             NUMERIC(9) PRIMARY KEY,
    cup_valor          NUMERIC(10, 2) NOT NULL,
    cup_is_promocional BOOLEAN NOT NULL,
    cup_is_troca       BOOLEAN NOT NULL,
    cup_is_ativo       BOOLEAN NOT NULL,
    cup_cli_id         NUMERIC(6) NOT NULL
);

CREATE TABLE cupons_pedido (
    cpe_cup_id NUMERIC(9),
    cpe_ped_id NUMERIC(9),
    PRIMARY KEY(cpe_cup_id, cpe_ped_id)
);

CREATE TABLE pedidos_pos_venda (
    ppv_id NUMERIC(9) PRIMARY KEY,
    ppv_ped_id NUMERIC(9) NOT NULL,
    ppv_qua_id NUMERIC(6) NOT NULL,
    ppv_quantidade NUMERIC(2) NOT NULL,
    ppv_status VARCHAR(20) NOT NULL,
    ppv_tipo VARCHAR(10) NOT NULL,
    ppv_data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE enderecos
    ADD CONSTRAINT fk_end_cli FOREIGN KEY ( end_cli_id ) REFERENCES clientes ( cli_id );

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_bcc FOREIGN KEY ( cre_bcc_id ) REFERENCES bandeiras_cartao_credito ( bcc_id );

ALTER TABLE cartoes_credito
    ADD CONSTRAINT fk_cre_cli FOREIGN KEY ( cre_cli_id ) REFERENCES clientes ( cli_id );

ALTER TABLE quadrinhos
    ADD CONSTRAINT fk_qua_gpr FOREIGN KEY ( qua_gpr_id ) REFERENCES grupos_precificacao ( gpr_id );

ALTER TABLE categorias_quadrinho
    ADD CONSTRAINT fk_cqu_cat FOREIGN KEY ( cqu_cat_id ) REFERENCES categorias ( cat_id );

ALTER TABLE categorias_quadrinho
    ADD CONSTRAINT fk_cqu_qua FOREIGN KEY ( cqu_qua_id ) REFERENCES quadrinhos ( qua_id );

ALTER TABLE itens_pedido
    ADD CONSTRAINT fk_ite_ped FOREIGN KEY ( ite_ped_id ) REFERENCES pedidos ( ped_id );

ALTER TABLE itens_pedido
    ADD CONSTRAINT fk_ite_qua FOREIGN KEY ( ite_qua_id ) REFERENCES quadrinhos ( qua_id );

ALTER TABLE pedidos
    ADD CONSTRAINT fk_ped_cli FOREIGN KEY ( ped_cli_id ) REFERENCES clientes ( cli_id );

ALTER TABLE pedidos
    ADD CONSTRAINT fk_ped_end FOREIGN KEY ( ped_end_id ) REFERENCES enderecos ( end_id );

ALTER TABLE cartoes_credito_pedido
    ADD CONSTRAINT fk_ccp_cre FOREIGN KEY ( ccp_cre_id ) REFERENCES cartoes_credito ( cre_id );

ALTER TABLE cartoes_credito_pedido
    ADD CONSTRAINT fk_ccp_ped FOREIGN KEY ( ccp_ped_id ) REFERENCES pedidos ( ped_id );

ALTER TABLE cupons
    ADD CONSTRAINT fk_cup_cli FOREIGN KEY ( cup_cli_id ) REFERENCES clientes ( cli_id );

ALTER TABLE cupons_pedido
    ADD CONSTRAINT fk_cpe_cup FOREIGN KEY ( cpe_cup_id ) REFERENCES cupons ( cup_id );

ALTER TABLE cupons_pedido
    ADD CONSTRAINT fk_cpe_ped FOREIGN KEY ( cpe_ped_id ) REFERENCES pedidos ( ped_id );

ALTER TABLE estoque
    ADD CONSTRAINT fk_est_qua FOREIGN KEY ( est_qua_id ) REFERENCES quadrinhos ( qua_id );

ALTER TABLE entrada_estoque
    ADD CONSTRAINT fk_ees_qua FOREIGN KEY ( ees_qua_id ) REFERENCES quadrinhos ( qua_id );

ALTER TABLE pedidos_pos_venda
    ADD CONSTRAINT fk_ppv_ped FOREIGN KEY ( ppv_ped_id ) REFERENCES pedidos ( ped_id );

ALTER TABLE pedidos_pos_venda
    ADD CONSTRAINT fk_ppv_qua FOREIGN KEY ( ppv_qua_id ) REFERENCES quadrinhos ( qua_id );