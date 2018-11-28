truncate emprestimo_video restart identity cascade;
truncate videos restart identity cascade;
truncate emprestimos restart identity cascade;
truncate filmes restart identity cascade;
truncate clientes restart identity cascade;

insert into clientes (nome, endereco, cpf, telefone)
 values ('José', 'Rua X, N 10, B. da Luz, São Luis', 12345678910, 912345678);
insert into clientes (nome, endereco, cpf, telefone)
 values ('Maria', 'Rua Y, N 34, B. da Luz, São Luis', 12345678911, 912345679);
insert into clientes (nome, endereco, cpf, telefone)
 values ('Juvenal', 'Rua A, N 12, B. da Luz, São Luis', 12345678912, 912345670);
insert into clientes (nome, endereco, cpf, telefone)
 values ('Tancredo', 'Rua B, N 17, B. da Luz, São Luis', 12345678913, 912345671);
insert into clientes (nome, endereco, cpf, telefone)
 values ('Flávia', 'Rua X, N 11, B. da Luz, São Luis', 12345678914, 912345672);

insert into filmes (titulo, ano_lancamento, duracao, genero)
 values ('Star Wars: Episódio IV - Uma Nova Esperança', 1977, 3, 'Aventura');
insert into filmes (titulo, ano_lancamento, duracao, genero)
 values ('Os Caçadores da Arca Perdida', 1981, 2, 'Aventura');
insert into filmes (titulo, ano_lancamento, duracao, genero)
 values ('Star Wars: Episódio V - O Império Contra-Ataca', 1980, 3, 'Aventura');
insert into filmes (titulo, ano_lancamento, duracao, genero)
 values ('O Senhor dos Anéis: A Sociedade do Anel', 2001, 3, 'Aventura');
insert into filmes (titulo, ano_lancamento, duracao, genero)
 values ('Star Wars: Episódio VI - O Retorno do Jedi', 1983, 3, 'Aventura');

insert into videos (filme_id, tipo, valor_diaria)
values (1, 'DVD', 2.50);
insert into videos (filme_id, tipo, valor_diaria)
values (1, 'DVD', 2.50);
insert into videos (filme_id, tipo, valor_diaria)
values (1, 'HVS', 3);
insert into videos (filme_id, tipo, valor_diaria)
values (2, 'DVD', 4);
insert into videos (filme_id, tipo, valor_diaria)
values (3, 'DVD', 2.40);
insert into videos (filme_id, tipo, valor_diaria)
values (4, 'DVD', 3.60);
insert into videos (filme_id, tipo, valor_diaria)
values (4, 'DVD', 3.60);
insert into videos (filme_id, tipo, valor_diaria)
values (4, 'DVD', 3.60);
insert into videos (filme_id, tipo, valor_diaria)
values (5, 'HVS', 2.20);
insert into videos (filme_id, tipo, valor_diaria)
values (5, 'HVS', 2.20);

insert  into emprestimos (cliente_id, data_locacao, data_devolucao, valor_aluguel, status)
values (1, 'yesterday', null, null, true);
insert  into emprestimos (cliente_id, data_locacao, data_devolucao, valor_aluguel, status)
values (2, 'yesterday', null, null, true);
insert  into emprestimos (cliente_id, data_locacao, data_devolucao, valor_aluguel, status)
values (3, 'yesterday', null, null, true);
insert  into emprestimos (cliente_id, data_locacao, data_devolucao, valor_aluguel, status)
values (4, 'yesterday', 'today', 2.40 + 3.60 + 2.20, false);

insert into emprestimo_video values (default , 1, 1);
update videos set status = 2 where id = 1;
insert into emprestimo_video values (default , 1, 4);
update videos set status = 2 where id = 4;
insert into emprestimo_video values (default , 2, 2);
update videos set status = 2 where id = 2;
insert into emprestimo_video values (default , 3, 3);
update videos set status = 2 where id = 3;
insert into emprestimo_video values (default , 4, 5);
insert into emprestimo_video values (default , 4, 6);
insert into emprestimo_video values (default , 4, 10);
