
create database if not exists estoquedb;

use estoquedb;

drop table if exists produto;

create table produto (
    id int(11) not null auto_increment,
    status char(1) not null default 'A',
    descricao varchar(50) null default null,
    estoque_minimo int(11) null default null,
    estoque_maximo int(11) null default null,
    primary key (id)
);

insert into produto (descricao, estoque_minimo, estoque_maximo) 
values ('Caneta', 10, 200),
       ('Lapis', 10, 200),
       ('Borracha', 10, 200),
       ('Apontador', 5, 100),
       ('Grafite', 10, 100),
       ('Corretivo', 5, 50);

select * from produto;

/*
TABELA ENTRADA_PRODUTO: serão gravadas todas as compras de 
produtos efetuadas para papelaria.
*/

drop table if exists entrada_produto;

create table entrada_produto (
    id int(11) not null auto_increment,
    id_produto int(11) null default null,
    qtde int(11) null default null,
    valor_unitario decimal(9,2) null default '0.00',
    data_entrada date null default null,
    primary key (id),
    foreign key (id_produto) references produto(id)
);

/*TABELA “SAIDA_PRODUTO”: serão gravadas todas as Vendas de produtos */

drop table if exists saida_produto;

create table saida_produto (
    id int(11) not null auto_increment,
    id_produto int(11) null default null,
    qtde int(11) null default null,
    valor_unitario decimal(9,2) null default '0.00',
    data_saida date null default null,
    primary key (id),
    foreign key (id_produto) references produto(id)
);

/* Cenário desejado: Essa tabela somente recebe os dados conforme 
as ações executadas nas tabelas de “ENTRADA_PRODUTO” e “SAIDA_PRODUTO”. 
O usuário não terá interação direta como INSERÇÕES, UPdateS E EXCLUSÕES, 
a tabela “ESTOQUE” é somente o resultado das ações de compra e venda 
de produtos.
*/

drop table if exists estoque;

create table estoque (
    id int(11) not null auto_increment,
    id_produto int(11) null default null,
    qtde int(11) null default null,
    valor_unitario decimal(9,2) null default '0.00',
    primary key (id),
    foreign key (id_produto) references produto(id)
);


/* ####################################### */
/* ------------- PROCEDURES -------------- */

-- Exemplo básico
delimiter $$


select 10 + 10 as conta$$

create procedure nome() 
begin
    select 10 + 10 as conta;
end $$

select 10 + 10 as conta$$

drop procedure nome$$

create procedure soma(valor1 int, valor2 int) 
begin
    select valor1 + valor2 as resultado;
end $$

call soma(34, 65)$$


drop procedure if exists sp_atualiza_estoque;


delimiter $$


create procedure sp_atualiza_estoque(idProd int, qtdeComprada int, valorUnitario decimal(9,2)) 
begin
    declare total int;

    select id_produto into total 
    from estoque 
    where id_produto = idProd;

    if (total is not null) then
        update estoque 
        set qtde = qtde + qtdeComprada, valor_unitario = valorUnitario 
        where id_produto = idProd;
    else    
        insert into estoque (id_produto, qtde, valor_unitario) 
        values (idProd, qtdeComprada, valorUnitario);
    end if;
end $$


delimiter ;


call sp_atualiza_estoque(1, 10, 5.50);

select * from estoque;


/* ####################################### */
/* -------------- FUNÇÃO COM CURSOR ----- */


delimiter $$


create function fn_valor_total_em_estoque() returns decimal(9,2)
begin
    declare quantidade int(11);
    declare valor decimal(9,2);
    declare total_valor decimal(9,2);
    declare done int default FALSE;

    # cursor para buscar os registros a serem processados
    # com quantidade > 0
    declare busca_estoque cursor for 
    select qtde, valor_unitario 
    from estoque 
    where qtde > 0;

    DECLARE CONTINUE HANDLER FOR not FOUND SET done = TRUE;

    # abre o cursor
    open busca_estoque;

        set total_valor = 0;   
        # inicia o loop
        estoque: loop

            # next() do JDBC
            fetch busca_estoque into quantidade, valor;
            # Código defensivo para sair do laço 
            if done then
                leave estoque;
            end if;
            set total_valor = total_valor + (quantidade * valor);
        end loop;

    # fecha o cursor   
    close busca_estoque;

    return total_valor;
end $$


delimiter ;  


select qtde, valor_unitario from estoque;

select qtde, valor_unitario
from estoque
where qtde > 0;

select sum(qtde * valor_unitario) 
from estoque
where qtde > 0;      

select fn_valor_total_em_estoque(); 


/* ####################################### */
/* -------------- TRIGGERS --------------- */

# 1. Trigger para auditoria

drop table if exists auditoria;

create table auditoria (
    id int primary key auto_increment,
    usuario varchar(45),
    datahora datetime default current_timestamp,
    operacao varchar(45)
);

alter table auditoria add column estoque_min_old int(11);
alter table auditoria add column estoque_min_new int(11);


desc auditoria;


select * from auditoria;


drop trigger if exists trg_limiteEstoque_BU;


delimiter $$


create trigger trg_limiteEstoque_BU before update on produto
for each row
begin
    if ((old.estoque_minimo <> new.estoque_minimo) or 
        (old.estoque_maximo <> new.estoque_maximo)) then
        
        insert into auditoria(usuario, datahora, operacao, estoque_min_old, estoque_min_new) 
        values(current_user, current_timestamp, "update", old.estoque_minimo, new.estoque_minimo);
    end if;
end $$


/* execute no terminal */
delimiter ;


select * from auditoria;
select * from produto; 


update produto set estoque_minimo=27 where id=4;
update produto set descricao = 'Lapis 3b' where id=2;


select * from auditoria;


## 2. Trigger que atualiza o estoque quando inserimos na tabela entrada produto
delimiter $$


create trigger trg_entradaProduto_AI after insert on entrada_produto
for each row 
begin
    call sp_atualiza_estoque(new.id_produto, new.qtde, new.valor_unitario);
end $$


delimiter ;

/* ----- teste do trigger trg_entradaProduto_AI  ---- */
select * from entrada_produto;
select * from estoque;

insert into entrada_produto (id_produto, qtde, valor_unitario, data_entrada) 
values (3, 10, 7.60, '2016-10-19');


select * from entrada_produto;
select * from estoque;


/* Triggers para banco de backup separado */

create database backup_estoque;

use backup_estoque;

create table backup_produto as select * from estoquedb.produto;


desc backup_produto;
select * from backup_produto;


use estoquedb;

drop trigger if exists tgr_backup_produto;

delimiter $$


create trigger tgr_backup_produto after insert on produto
for each row
begin
    insert into backup_estoque.backup_produto 
    values(new.id, new.status, new.descricao, new.estoque_minimo, new.estoque_maximo);
end $$

delimiter ;