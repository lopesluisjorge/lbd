create table if not exists emprestimos (
  id serial,
  cliente_id     int      not null,
  data_locacao   date     not null,
  data_devolucao date,
  valor_aluguel  decimal(7, 2),
  status         boolean not null default true,
  primary key (id),
  foreign key (cliente_id) references clientes(id));
