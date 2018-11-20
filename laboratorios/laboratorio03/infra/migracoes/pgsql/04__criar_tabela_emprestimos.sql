create table if not exists emprestimos (
  id serial,
  cliente_id        int           not null,
  data_de_locacao   date          not null,
  data_de_devolucao date,
  valor_aluguel     decimal(7, 2) not null,
  status            smallint      not null,
  primary key (id),
  foreign key (cliente_id) references clientes(id));
