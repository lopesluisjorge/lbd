# Lab 02 - Procedures e Triggers

- Execute o script (cria-bd-estoque.sql) para criar a base de dados no sistema e execute as
instruções de insert.

- Responda às seguintes questões e teste devidamente todos os procedimentos e triggers que
desenvolver, usando a linha de comando e comandos SQL.

## Procedures

1. Elabore uma procedure para efetuar a baixa do estoque de um produto (vide tabela
saida_produto). A procedure deverá receber dois parâmetros de entrada (id do
produto e quantidade vendida). Caso o produto não tenha no estoque a procedure deverá
armazenar em uma tabela denominada produtos_em_falta com as seguintes colunas:
id, id_produto, data, quant_estoque e valor_unitario.

2. Elabore uma procedure para efetuar a comparação entre estoque mínimo (da tabela
produto) e estoque atual de um produto (na tabela estoque). Caso o estoque esteja abaixodo estoque mínimo, será armazenado em uma tabela produtos_requisicao
(id_produto, descrição e datahora).

3. Elabore uma procedure para reajustar o preço de um produto com uma determinada taxa
(porcentagem – parâmetro IN).

## Triggers

4. Faça um trigger para armazenar em uma tabela chamada produtos_atualizados
(prd_codigo, prd_qtd_anterior, prd_qtd_atualizada, prd_valor)
quando ocorrer quaisquer alterações nos atributos da tabela produtos. No entanto, caso
a alteração atribua o valor zero para o atributo PRD_qtd_estoque, a tabela
produtos_em_falta deverá ser alimentada com as mesmas informações da tabela
produto, exceto o atributo prd_valor. Além disso, o atributo prd_status do
produto atualizado deve ser modificado para NULL e o atributo orp_status de todos os
orçamentos_produto desse produto deverá ser modificado também para NULL.

5. Faça um trigger para armazenar em uma tabela chamada
historico_produtos_excluidos(id, id_produto, descricao,
usuario, datahora ) todos os produtos que foram excluídos da tabela Produtos. A
coluna usuario representa o usuário do sistema que realizou a exclusão e em que data
e hora. Essa trigger somente excluirá os produtos, se eles não tiverem entrada na tabela
estoque.

6. Faça um trigger que antes de inserir uma saída no orçamento, seja verificado em estoque
se a quantidade é suficiente no estoque (qtde). Caso afirmativo, a operação deve ser
cancelada e, logo em seguida, insera na tabela produtos_requisitados os seguintes
atributos (id, id_produto, qtd_em_falta, datahora, usuario_sistema).
Dessa maneira, essa tabela manterá todos os produtos que já foram vendidos e que não
tinham no estoque no momento da venda. O atributo qtd_em_falta é a diferença da
quantidade orçada e a quantidade no estoque.
