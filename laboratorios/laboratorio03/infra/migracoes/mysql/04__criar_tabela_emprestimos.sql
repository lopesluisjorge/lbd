use locadora_db;

create table if not exists emprestimos (
  id                int unsigned  not null auto_increment,
  cliente_id        int unsigned  not null,
  data_de_locacao   date          not null,
  data_de_devolucao date,
  valor_aluguel     decimal(7, 2) not null,
  status            tinyint,
  primary key (id),
  foreign key (cliente_id) references clientes(id));
