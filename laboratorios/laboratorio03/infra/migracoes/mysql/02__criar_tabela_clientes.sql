use locadora_db;

create table if not exists clientes (
  id       int unsigned not null auto_increment,
  nome     varchar(64)  not null,
  cpf      char(11)     not null unique,
  endereco varchar(128) not null,
  telefone varchar(16)  not null,
  primary key (id));
