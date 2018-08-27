CREATE TABLE clientes(
   cdCliente serial      NOT NULL,
   nome varchar(50) NOT NULL,
   cpf varchar(50) NOT NULL,
   telefone varchar(50) NOT NULL,
   CONSTRAINT pk_clientes
      PRIMARY KEY(cdCliente)
);

INSERT INTO clientes(nome, cpf, telefone) VALUES('Rafael','111.111.111-11','(11) 1111-1111');
INSERT INTO clientes(nome, cpf, telefone) VALUES('João'  ,'222.222.222-22','(22) 2222-2222');
INSERT INTO clientes(nome, cpf, telefone) VALUES('Maria' ,'333.333.333-33','(33) 3333-3333');

