

use estoquedb;

-- Novos dados

insert into produto (descricao, estoque_minimo, estoque_maximo)
values ('Caderno', 10, 200),
       ('Papel A4', 10, 200),
       ('Papel Ofício', 10, 200),
       ('Lapiseira', 5, 100),
       ('Pasta Catálogo', 10, 100);

-- Quando Inserir um produto na entrada, este vai ser automaticamente posto no estoque
-- Baseado no exemplo do professor

-- Procedure

delimiter $$

drop procedure if exists sp_atualiza_estoque$$

create procedure sp_atualiza_estoque (
    p_id_produto     int,
    p_qtde_comprada  int,
    p_valor_unitario decimal(9,2)) 
begin
    declare var_count int;

    select count(*) into var_count
    from estoque
    where id_produto = p_id_produto
    limit 1;

    if var_count = 1 then
        update estoque set qtde           = qtde + p_qtde_comprada, 
                           valor_unitario = p_valor_unitario 
        where id_produto = p_id_produto;
    else
        insert into estoque (id_produto,   qtde,            valor_unitario) 
                     values (p_id_produto, p_qtde_comprada, p_valor_unitario);
    end if;
end $$

-- Trigger

delimiter $$

drop trigger if exists trg_adiciona_estoque_ai$$

create trigger trg_adiciona_estoque_ai after insert on entrada_produto
for each row
begin
    call sp_atualiza_estoque(new.id_produto, new.qtde, new.valor_unitario);
end $$

delimiter ;

-- Testes

select * from entrada_produto;
select * from estoque;

insert into entrada_produto (id_produto, qtde, valor_unitario, data_entrada)
values (1, 20, 1.99, current_date),
       (2, 10, 0.99, current_date),
       (3, 30, 2.99, current_date),
       (4, 30, 3.99, current_date),
       (5, 30, 7.49, current_date),
       (6, 30, 5.29, current_date),
       (7, 10, 14.99, current_date),
       (8, 30, 10.99, current_date),
       (9, 1, 17.29, current_date),
       (1, 10, 1.99, current_date);

select * from entrada_produto;
select * from estoque;



-- Procedures



-- 1. Elabore uma procedure para efetuar a baixa do estoque de um produto (vide tabela
-- saida_produto). A procedure deverá receber dois parâmetros de entrada (id do
-- produto e quantidade vendida). Caso o produto não tenha no estoque a procedure deverá
-- armazenar em uma tabela denominada produtos_em_falta com as seguintes colunas:
-- id, id_produto, data, quant_estoque e valor_unitario.

delimiter ;

create table if not exists produtos_em_falta (
    id int not null auto_increment,
    id_produto int not null,
    data date not null,
    quant_estoque int not null,
    valor_unitario decimal(9, 2),
    primary key (id),
    foreign key (id_produto) references produto(id));

delimiter $$

drop procedure if exists sp_baixa_estoque$$

create procedure sp_baixa_estoque(p_id_produto int, p_quantidade_vendida int)
begin
    declare var_quantidade_produtos int;
    declare var_valor_atual         decimal(9, 2);
    
    select qtde,                    valor_unitario 
    into   var_quantidade_produtos, var_valor_atual
    from estoque 
    where id_produto = p_id_produto;
    
    if var_quantidade_produtos is not null then
        if var_quantidade_produtos > p_quantidade_vendida then
            update estoque set qtde = qtde - p_quantidade_vendida
            where id_produto = p_id_produto;
        
            insert into saida_produto (
                id_produto,
                qtde,
                data_saida,
                valor_unitario)
            values (
                p_id_produto,
                p_quantidade_vendida,
                current_date,
                var_valor_atual);
        else        
            insert into produtos_em_falta (
                id_produto,
                data,
                quant_estoque,
                valor_unitario)
            values (
                p_id_produto,
                current_date,
                var_quantidade_produtos,
                var_valor_atual);
        end if;
    else
        select 'Produto inexistente' as p_id_produto;
    end if;
end $$

delimiter ;

-- Testes da Questão 1

select * from estoque;
select * from produtos_em_falta;

-- Funciona Corretamente
call sp_baixa_estoque(1, 5); # 30 -> 25
call sp_baixa_estoque(3, 10); # 30 -> 20

select * from estoque;
select * from produtos_em_falta;

-- Produto sem estoque sufiente

call sp_baixa_estoque(2, 20); # 10 -> 10, Insere em produtos_em_falta

select * from estoque;
select * from produtos_em_falta;

988839474



-- 2. Elabore uma procedure para efetuar a comparação entre estoque mínimo (da tabela
-- produto) e estoque atual de um produto (na tabela estoque). Caso o estoque esteja abaixo
-- do estoque mínimo, será armazenado em uma tabela produtos_requisicao
-- (id_produto, descrição e datahora).

delimiter ;

create table if not exists produtos_requisicao (
    id int not null auto_increment,
    id_produto int not null,
    descricao varchar(50) not null,
    datahora datetime not null,
    primary key (id),
    foreign key (id_produto) references produto(id));

delimiter $$

drop procedure if exists sp_comparacao_estoque_minimo$$

create procedure sp_comparacao_estoque_minimo(p_id_produto int)
begin
    declare var_estoque_atual  int;
    declare var_estoque_minimo int;
    declare var_descricao      varchar(50);
    
    select p.estoque_minimo, p.descricao, qtde 
    into var_estoque_minimo, var_descricao, var_estoque_atual
    from produto p 
        join estoque e on p.id = e.id_produto
    where p.id = p_id_produto;
    
    if (var_estoque_minimo is not null
            and var_estoque_atual is not null) then
        if var_estoque_atual < var_estoque_minimo then
            insert into produtos_requisicao (
                id_produto,
                descricao,
                datahora)
            values (
                p_id_produto,
                var_descricao,
                now());
        else
            select 'Estoque normal' as p_id_produto;
        end if;
    else
        select 'Produto inexistente' as p_id_produto;
    end if;
end $$

-- Testes da Questão 2

delimiter ;

select * from produtos_requisicao;

-- Produto com estoque normal

call sp_comparacao_estoque_minimo(1); # 'Estoque normal'
call sp_comparacao_estoque_minimo(2); # 'Estoque normal'

select * from produtos_requisicao;

-- Produto com estoque baixo

call sp_comparacao_estoque_minimo(9); # Insere em produtos_requisicao

select * from produtos_requisicao;



-- 3. Elabore uma procedure para reajustar o preço de um produto com uma determinada taxa
-- (porcentagem – parâmetro IN).

delimiter $$

drop procedure if exists sp_reajustar_preco$$

create procedure sp_reajustar_preco(
    p_id_produto int,
    porcentagem decimal(5, 2))
begin
    declare var_valor_atual decimal(9, 2);

    select valor_unitario into var_valor_atual
    from estoque
    where id_produto = p_id_produto;
    
    if (var_valor_atual is not null) then
        update estoque
        set valor_unitario =
            valor_unitario + (valor_unitario * (porcentagem / 100))
        where id_produto = p_id_produto;
    else
        select 'Produto inexistente' as p_id_produto;
    end if;
end $$

-- Testes da Questão 3

delimiter ;

select * from estoque;

-- Aumento de preço

call sp_reajustar_preco(1, 10); # 1.99 -> 2.19
call sp_reajustar_preco(2, 25.3); # 0.99 -> 1.24

-- Redução no preço

call sp_reajustar_preco(3, -20); # 2.99 -> 2.39
call sp_reajustar_preco(4, -17.5); # 3.99 -> 3.29

select * from estoque;


-- Triggers



-- 4. Faça uma trigger para armazenar em uma tabela chamada produtos_atualizados
-- (prd_codigo, prd_qtd_anterior, prd_qtd_atualizada, prd_valor)
-- quando ocorrer quaisquer alterações nos atributos da tabela estoque. No entanto, caso
-- a alteração atribua o valor zero para o atributo qtde, a tabela produtos_em_falta
-- deverá ser alimentada com as mesmas informações da tabela estoque, exceto o atributo
-- valor_unitario

delimiter ;

create table if not exists produtos_atualizados (
    id int not null auto_increment,
    prd_codigo int not null,
    prd_qtd_anterior int not null,
    prd_qtd_atualizada int not null,
    prd_valor decimal(9, 2) not null,
    primary key (id),
    foreign key (prd_codigo) references produto(id));
    
delimiter $$

drop trigger if exists trg_atualizacao_estoque_au$$

create trigger trg_atualizacao_estoque_au after update on estoque
for each row
begin
    insert into produtos_atualizados (
        prd_codigo,
        prd_qtd_anterior,
        prd_qtd_atualizada,
        prd_valor) 
    values (
        new.id_produto,
        old.qtde,
        new.qtde,
        new.valor_unitario);

    if new.qtde = 0 then
        insert into produtos_em_falta (
            id_produto,
            data,
            quant_estoque)
        values (
            new.id_produto,
            current_date,
            new.qtde);
    end if;
end $$

delimiter ;


-- Testes Questão 4

update estoque set valor_unitario = 1.99 where id = 1;



-- 5. Faça um trigger para armazenar em uma tabela chamada
-- historico_produtos_excluidos(id, id_produto, descricao,
-- usuario, datahora ) todos os produtos que foram excluídos da tabela Produtos. A
-- coluna usuario representa o usuário do sistema que realizou a exclusão e em que data
-- e hora. Essa trigger somente excluirá os produtos, se eles não tiverem entrada na tabela
-- estoque.

delimiter ;

create table if not exists historico_produtos_excluidos (
    id int not null auto_increment,
    id_produto int not null ,
    descricao varchar(50),
    usuario varchar(50),
    datahora datetime default now(),
    primary key (id));

delimiter $$

drop trigger if exists trg_armazenamento_produtos_removidos_ad$$

create trigger trg_armazenamento_produtos_removidos_ad after delete on produto
for each row
begin
    insert into historico_produtos_excluidos (
        id_produto,
        descricao,
        usuario,
        datahora)
    values (
        old.id,
        old.descricao,
        current_user,
        now());
end $$

-- Testes da Questão 5

-- Remoção com sucesso

delete from produto where id = 10;

-- Remoção com falha

delete from produto where id = 1;



-- 6. Faça uma trigger que antes de inserir uma saída no orçamento (tabela saída_produto), seja
-- verificado na tabela estoque se a quantidade (não(?)) é suficiente (atributo qtde). Caso afirmativo,
-- a operação deve ser cancelada e, logo em seguida, insira na tabela
-- produtos_requisitados os seguintes atributos (id, id_produto, qtd_em_falta, datahora, usuario_sistema). 
-- Dessa maneira, essa tabela manterá todos os produtos que já foram vendidos e que não tinham no estoque no
-- momento da venda. O atributo qtd_em_falta é a diferença da quantidade orçada e a
-- quantidade no estoque.

delimiter ;

create table if not exists produtos_requisitados (
    id int not null, 
    id_produto int not null, 
    qtd_em_falta int unsigned not null,
    datahora datetime default now(),
    usuario_sistema varchar(50),
    primary key (id),
    foreign key (id_produto) references produto(id));

delimiter $$

drop trigger if exists trg_verificacao_saida_estoque_bi$$

create trigger trg_verificacao_saida_estoque_bi before insert on saida_produto
for each row
begin
    declare var_quantidade int;
    declare var_nova_qtde  int;
    declare var_id_produto int;

    select qtde into var_quantidade
    from estoque
    where id_produto = new.id_produto;

    if var_quantidade is not null and var_quantidade <= new.qtde then
        delete from saida_produto where id_produto = new.id_produto;
    
        select new.qtde - var_quantidade, new.id_produto 
        into   var_nova_qtde,             var_id_produto;
    
        insert into produtos_requisitados (
            id_produto,
            qtd_em_falta,
            datahora,
            usuario_sistema) 
        values (
            var_id_produto,
            var_nova_qtde,
            now(),
            current_user);
    else
        update estoque
        set qtde = qtde - new.qtde 
        where id_produto = new.id_produto;
    end if;
end $$

delimiter ;
