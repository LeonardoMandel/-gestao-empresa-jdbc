-- 1. CRIAÇÃO DO BANCO E USUÁRIO
CREATE DATABASE IF NOT EXISTS empresa;
USE empresa;

-- Cuidado: Em ambiente de dev, remove o usuário se existir para recriá-lo.
DROP USER IF EXISTS 'devuser'@'localhost';
CREATE USER 'devuser'@'localhost' IDENTIFIED BY 'devpass123';
GRANT ALL PRIVILEGES ON empresa.* TO 'devuser'@'localhost';
FLUSH PRIVILEGES;

-- 2. CRIAÇÃO DAS TABELAS (SEM DADOS)
DROP TABLE IF EXISTS projeto;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS pessoa;

CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE funcionario (
    id INT PRIMARY KEY,
    matricula VARCHAR(10) NOT NULL UNIQUE,
    departamento VARCHAR(100),
    CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (id) REFERENCES pessoa(id)
);

CREATE TABLE projeto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    id_funcionario INT NOT NULL,
    CONSTRAINT fk_projeto_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);

