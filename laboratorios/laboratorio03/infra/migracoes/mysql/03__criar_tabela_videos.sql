use locadora_db;

create table if not exists videos (
  id           int unsigned not null auto_increment,
  filme_id     int unsigned not null,
  status       int          not null,
  tipo         varchar(32),
  valor_diaria decimal(5, 2),
  primary key (id),
  foreign key (filme_id) references filmes(id));
