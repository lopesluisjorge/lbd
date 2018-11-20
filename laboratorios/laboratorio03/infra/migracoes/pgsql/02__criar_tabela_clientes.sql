create table if not exists clientes (
  id serial,
  nome     varchar(64)  not null,
  cpf      char(11)     not null unique,
  endereco varchar(128) not null,
  telefone varchar(16)  not null,
  primary key (id));
