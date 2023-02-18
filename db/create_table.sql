create table tb_clientes
(
    id                    bigint auto_increment primary key,
    nome                  varchar(255),
    identificacao_federal varchar(14) UNIQUE,
    registro              varchar(50),
    tipo                  ENUM ('CPF', 'CNPJ'),
    situacao              boolean
);

create table tb_telefones
(
    id bigint auto_increment primary key,
    pais varchar(3),
    ddd varchar(3),
    numero varchar(9) not null ,
    pricipal boolean not null ,
    id_cliente bigint not null ,
    FOREIGN KEY (id_cliente) REFERENCES tb_clientes(id)
);