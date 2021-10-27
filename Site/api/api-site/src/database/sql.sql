CREATE DATABASE SITETAROT;

USE SITETAROT;

CREATE TABLE USUARIO (
ID_USUARIO INT PRIMARY KEY AUTO_INCREMENT,
NOME_USUARIO VARCHAR(100),
EMAIL_USUARIO VARCHAR(100),
SENHA_USUARIO VARCHAR(100),
GENERO_USUARIO CHAR(1),
CHECK(GENERO_USUARIO = 'm' OR GENERO_USUARIO = 'f' OR GENERO_USUARIO = 'x'),
NASCIMENTO_USUARIO DATE);

CREATE TABLE ARCANOS (
ID_ARCANOS INT PRIMARY KEY AUTO_INCREMENT,
NOME_ARCANO VARCHAR(45),
NAIPE_ARCANO VARCHAR(45),
CATEGORIA_ARCANO VARCHAR(45));

CREATE TABLE DADOS (
ID_DADOS INT PRIMARY KEY AUTO_INCREMENT,
FK_USUARIO INT,
FK_ARCANOS INT,
HORARIO TIME,
DIA DATE,
TIRAGENS INT,
FOREIGN KEY(FK_USUARIO) REFERENCES USUARIO(ID_USUARIO),
FOREIGN KEY(FK_ARCANOS) REFERENCES ARCANOS(ID_ARCANOS));

select * from USUARIO;