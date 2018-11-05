-- Procedures

use estoquedb;



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
    
    if (var_quantidade_produtos is not null) then        
        if (var_quantidade_produtos > p_quantidade_vendida) then
			update estoque set qtde = qtde - p_quantidade_vendida
			where id_produto = p_id_produto;
            
            insert into saida_produto (id_produto, qtde, data_saida, valor_unitario)
            values (p_id_produto, p_quantidade_vendida, date(now()), var_valor_atual);
        else
			insert into produtos_em_falta (id_produto, data, quant_estoque, valor_unitario) 
			values (var_id_verificador, date(now()), var_quantidade_produtos, var_valor_atual);
        end if;
	end if;
end $$



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
    declare var_descricao varchar(50);
    
    select p.estoque_minimo, p.descricao, qtde 
    into var_estoque_minimo, var_descricao, var_estoque_atual
    from produto p 
        join estoque e on p.id = e.id_produto
    where p.id = p_id_produto;
    
    if (var_estoque_minimo is not null and var_estoque_atual is not null) then
        if (var_estoque_atual < var_estoque_minimo) then
            insert into produtos_requisicao (id_produto, descricao, datahora)
            values (p_id_produto, var_descricao, now());
        end if;
    end if;
end $$



-- 3. Elabore uma procedure para reajustar o preço de um produto com uma determinada taxa
-- (porcentagem – parâmetro IN).

delimiter $$

drop procedure if exists sp_reajustar_preco$$

create procedure sp_reajustar_preco(p_id_produto int, porcentagem decimal(5, 2))
begin
	declare var_valor_atual decimal(9, 2);

	select valor_unitario into var_valor_atual
    from estoque
    where id_produto = p_id_produto;
    
    if (var_valor_atual is not null) then
		update estoque set valor_unitario = valor_unitario + (valor_unitario * (porcentagem / 100))
        where id_produto = p_id_produto;
    end if;
end $$



-- Triggers



-- 4. Faça um trigger para armazenar em uma tabela chamada produtos_atualizados
-- (prd_codigo, prd_qtd_anterior, prd_qtd_atualizada, prd_valor)
-- quando ocorrer quaisquer alterações nos atributos da tabela produtos. No entanto, caso
-- a alteração atribua o valor zero para o atributo PRD_qtd_estoque, a tabela
-- produtos_em_falta deverá ser alimentada com as mesmas informações da tabela
-- produto, exceto o atributo prd_valor. Além disso, o atributo prd_status do
-- produto atualizado deve ser modificado para NULL e o atributo orp_status de todos os
-- orçamentos_produto desse produto deverá ser modificado também para NULL.

delimiter ;

create table if not exists produtos_atualizado (
	id int not null auto_increment,
    prd_codigo int not null,
    prd_qtd_anterior int not null,
    prd_qtd_atualizada int not null,
    prd_valor decimal(9, 2) not null,
    primary key (id),
    foreign key (prd_codigo) references produto(id));
    
delimiter $$

drop trigger if exists trg_atualizacao_produto_au$$



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
    insert into historico_produtos_excluidos (id_produto, descricao, usuario, datahora)
    values (old.id, old.descricao, current_user(), now());
end $$



-- 6. Faça um trigger que antes de inserir uma saída no orçamento, seja verificado em estoque
-- se a quantidade é suficiente no estoque (qtde). Caso afirmativo, a operação deve ser
-- cancelada e, logo em seguida, insera na tabela produtos_requisitados os seguintes
-- atributos (id, id_produto, qtd_em_falta, datahora, usuario_sistema).
-- Dessa maneira, essa tabela manterá todos os produtos que já foram vendidos e que não
-- tinham no estoque no momento da venda. O atributo qtd_em_falta é a diferença da
-- quantidade orçada e a quantidade no estoque.

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

    select qtde into var_quantidade
    from estoque
    where id_produto = new.id_produto;

    if (var_quantidade is not null and var_quantidade < new.qtde) then
        insert into produtos_requisitados (id_produto, qtd_em_falta, datahora, usuario_sistema)
        values (new.id_produto, new.qtde - var_quantidade, now(), current_user());
        
        delete from saida_produto
        where id_produto = new.id_produto;
	else
		update estoque
        set qtde = qtde - new.qtde 
        where id_produto = new.id_produto;
    end if;
end $$

delimiter ;
