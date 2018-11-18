create table if not exists videos (
    id serial,
    filme_id int not null,
    status smallint not null,
    tipo varchar(32) not null,
    valor_diaria decimal(5, 2) not null,
    primary key (id),
    foreign key (filme_id) references filmes(id));
