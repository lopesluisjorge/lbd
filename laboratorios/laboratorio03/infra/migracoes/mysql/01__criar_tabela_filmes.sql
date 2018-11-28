use locadora_db;

create table if not exists filmes (
  id                int unsigned not null auto_increment,
  titulo            varchar(64)  not null unique,
  ano_de_lancamento year         not null,
  duracao           int          not null,
  genero            varchar(32)  not null,
  primary key(id));

-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Star Wars: Episódio IV - Uma Nova Esperança', 1977, 3, 'Ação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Os Caçadores da Arca Perdida', 1981, 2, 'Aventura');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Star Wars: Episódio V - O Império Contra-Ataca', 1980, 3, 'Ação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('O Senhor dos Anéis: A Sociedade do Anel', 2001, 3, 'Aventura');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Star Wars: Episódio VI - O Retorno do Jedi', 1983, 3, 'Ação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Blade Runner, o Caçador de Andróides', 1982, 2, 'Ficção Científica');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('O Exterminador do Futuro', 1984, 3,'Ficção Científica');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('O Senhor dos Anéis: As Duas Tores', 2002, 4, 'Aventura');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Matrix', 1999, 2, 'Ficção Científica');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('O Rei Leão', 1994, 2, 'Animação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Os Simpsons - O Filme', 2007, 2, 'Animação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('A Era do Gelo', 2002, 3, 'Animação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('O Poderoso Chefão', 1972, 3, 'Drama');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('South Park: Imaginationland', 2008, 3, 'Animação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('Um Sonho de Liberdade', 1994, 2, 'Drama');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('As Aventuras de Tintim', 2011, 3, 'Animação');
-- insert into filmes (titulo, ano_de_lancamento, duracao, genero) values ('O Senhor dos Anéis: O Retorno do Rei', 2003, 4, 'Aventura');
