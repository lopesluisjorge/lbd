create table if not exists filmes (
  id serial,
  titulo            varchar(64) not null unique,
  ano_de_lancamento int         not null,
  duracao           int         not null,
  genero            varchar(32) not null,
  primary key(id));
