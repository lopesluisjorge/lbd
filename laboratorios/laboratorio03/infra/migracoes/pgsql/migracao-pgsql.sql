create table if not exists filmes (
  id serial,
  titulo         varchar(64) not null unique,
  ano_lancamento int         not null,
  duracao        int         not null,
  genero         varchar(32) not null,
  primary key(id));

create table if not exists clientes (
  id serial,
  nome     varchar(64)  not null,
  cpf      char(11)     not null unique,
  endereco varchar(128) not null,
  telefone varchar(16)  not null,
  primary key (id));

create table if not exists videos (
  id serial,
  filme_id     int           not null,
  status       smallint      not null default 1,
  tipo         varchar(32)   not null,
  valor_diaria decimal(5, 2) not null,
  primary key (id),
  foreign key (filme_id) references filmes(id));

create table if not exists emprestimos (
  id serial,
  cliente_id     int      not null,
  data_locacao   date     not null,
  data_devolucao date,
  valor_aluguel  decimal(7, 2),
  status         smallint not null default 1,
  primary key (id),
  foreign key (cliente_id) references clientes(id));

create table if not exists emprestimo_video (
  id serial,
  emprestimo_id int not null,
  video_id      int not null,
  primary key (id),
  foreign key (emprestimo_id) references emprestimos(id),
  foreign key (video_id)      references videos(id));
