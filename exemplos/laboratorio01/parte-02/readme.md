# Lab 01 - Parte 02: Junções, sub-consultas, group by, funções de agregação e visões

[Dump da Base de Dados](loja.sql)

[Consultas Nível Básico](01-nivel-basico.sql)

[Consultas Nível Intermediário](02-nivel-intermediario.sql)

[Consultas Nível Avançado](03-nivel-avancado.sql)

[Gabarito Consultas Intermediárias](gabarito-sql-consultas-intermediarias.sql)

[Gabarito Consultas Avançadas](gabarito-sql-consultas-avancadas.sql)


## Questões Básicas

1. Obtenha todos as transportadoras (Shippers) cadastradas.

2. Selecione o nome (CategororyName) e a descrição (Description) de todas as categorias
(Categories)

3. Selecione apenas o Nome (FirstName), o Sobrenome (LastName) e a Data de admissão
(HireDate) de todos os funcionários com o cargo (ou função) (Title) de Representante de
Vendas (Sales Representative). Escreva um SQL que recupere apenas essses funcionários.

4. Representantes de vendas dos Estados Unidos. Agora gostaríamos de ver as mesmas colunas
da questão anterior, mas apenas para aqueles funcionários que têm o cargo (Title) de
Representante de Vendas, e também sejam do Estados Unidos.
Resultado Esperado5. Na tabela Fornecedores (Suppliers), mostre o ID do fornecedor (SuplierId), o nome do contato (ContactName), a sua Função (ContactTitle) para aqueles Fornecedores cuja função não seja
gerente de marketing (Marketing Manager).

6. Pedidos enviados para qualquer país da América Latina. Mas não temos uma lista de países
latino-americanos em uma tabela no banco de dados. Então, vamos usar esta lista de países
cadastrados: Brasil, México, Argentina, Venezuela.

7. Para todos os funcionários na tabela Empregados, mostre o Nome, Sobrenome, Cargo (Title)
e Data de nascimento. Ordene os resultados pela Data de Nascimento (BirthDate). Mostre
primeiro os funcionários mais antigos.

8. Repita a consulta anterior (questão 7). Porém, apresente apenas a porção da data (sem a
hora) da coluna BirthDate.
Dica: Pesquise Mysql Date Function no Google.

9. Na tabela OrderDetails, temos os campos UnitPrice e Quantity. Crie um novo campo,
TotalPrice, que multiplica estes dois. Ignoraremos o campo Desconto. Além disso, mostre o
OrderID, ProductID, UnitPrice e Quantity. Ordene os resultados por OrderID e ProductID.
Resultado Esperado. . .

10. Quantos clientes temos na tabela Customers?

11. Mostre a data da primeira encomenda (OrderDate) feita na tabela Pedidos (Order).

12. Mostrar uma lista de países, em ordem alfabética, onde a empresa possui clientes.

13. Mostre uma lista de todos as funções (ou cargos) (ContactTitles) na tabela Customers. Inclua
também uma contagem para cada ContactTitles. Isto é semelhante em conceito à questãoanterior "Países onde há clientes", exceto que agora queremos contar o total de ContactTitle e ordenar de maneira descendente.

14. Gostaríamos de mostrar, para cada produto, o fornecedor associado. Mostre o ProductID,
ProductName e CompanyName do Fornecedor. Classificar por ProductID.
Esta questão precisa da cláusula Join em SQL. A cláusula Join é usada para juntar duas ou mais
tabelas de banco de dados relacionais de uma maneira lógica.

## Questões Avançadas

1. Queremos enviar aos nossos melhores clientes (clientes VIP) um presente. Clientes VIP
são aqueles que fizeram pelo menos 1 pedido com um valor total (não incluindo o
desconto) maior ou igual a $ 10.000. Para esta consulta vamos considerar apenas pedidos
realizados no ano de 2016.
a. Dica: o group by deverá ficar assim ( group by customers.customerID,
customers.companyName, orders.orderID )

2. O gerente mudou de ideia. Em vez de exigir que os clientes VIP tenham pelo menos um
pedido no total de $ 10.000 ou mais. Agora, são considerados Clientes VIP, aqueles que
tenham o somatório dos pedidos em 2016 maior ou igual a $ 15.000.

3. No final do mês, é provável que os vendedores tentem vender um pouco mais, para
alcançar às suas metas mensais. Mostre todos os pedidos feitos no último dia do mês.
Ordene por EmployeeID e OrderID.
a. Dica: pesquise sobre a função Last_day

4. Os desenvolvedores de aplicativos móveis estão testando um aplicativo que os clientes
usarão para exibir os pedidos realizados. Para se certificar de que até os pedidos maiores
serão exibidos corretamente no aplicativo e ajudar a equipe de desenvolvimento. Mostre
os 10 pedidos com mais itens em ordem decrescente.

5. Alguns clientes estão reclamando de atraso na entrega. Quais pedidos estão com atraso
na entrega?

6. Alguns vendedores têm mais pedidos chegando com atraso. Talvez precisem de mais
treinamento. Quais vendedores têm mais pedidos atrasados?

7. O gerente de vendas, pensando mais sobre os pedidos atrasados, percebeu que apenas
olhar para o número de pedidos (de cada vendedor) que chegam depois do prazo não é
suficiente. Ele decidiu comparar o total de pedidos de cada vendedor com o total de seus
pedidos que estão em atraso. Retorne os resultados conforme a figura que segue:

8. Crie uma View “pedidos-atrasados” que lista todos os pedidos que ainda não foram
entregues e já ultrapassaram o prazo previsto para entrega.
a. OBS: Você pode aproveitar a consulta SQL da questão 05.