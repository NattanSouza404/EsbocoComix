-- Script a ser usado no SGBD PostgreSQL

-- Criacao do database
CREATE DATABASE projeto_les;

-- Criacao da tabela
CREATE TABLE clientes (
	cli_id SERIAL PRIMARY KEY,
	cli_nome varchar(100) NOT NULL
);

-- Exemplo de linha
INSERT INTO cliente
	(nome)
VALUES
	('Jorge');