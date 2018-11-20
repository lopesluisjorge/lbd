use store;

/* 1. Obtenha todos as transportadoras (Shippers) cadastradas.
 */

select * from shippers;

/* 2. Selecione o nome (CategororyName) e a descrição (Description) de todas as categorias
 * (Categories)
 */

select categoryname, description
from categories;
 
/* 3. Selecione apenas o Nome (FirstName), o Sobrenome (LastName) e a Data de admissão
 * (HireDate) de todos os funcionários com o cargo (ou função) (Title) de Representante de
 * Vendas (Sales Representative). Escreva um SQL que recupere apenas essses funcionários.
 */

select firstname, lastname, hiredate
from employees
where title like 'Sales Representative';

/* 4. Representantes de vendas dos Estados Unidos. Agora gostaríamos de ver as mesmas colunas
 * da questão anterior, mas apenas para aqueles funcionários que têm o cargo (Title) de
 * Representante de Vendas, e também sejam do Estados Unidos.
 */
 
select firstname, lastname, hiredate
from employees
where Title like 'Sales Representative' and country like 'USA';

/* 5. Na tabela Fornecedores (Suppliers), mostre o ID do fornecedor (SuplierId), o nome do contato
 * (ContactName), a sua Função (ContactTitle) para aqueles Fornecedores cuja função não seja
 * gerente de marketing (Marketing Manager).
 */

select supplierid, contactname, contacttitle 
from suppliers
where contacttitle not like 'Marketing Manager';

/* 6. Pedidos enviados para qualquer país da América Latina. Mas não temos uma lista de países
 * latino-americanos em uma tabela no banco de dados. Então, vamos usar esta lista de países
 * cadastrados: Brasil, México, Argentina, Venezuela.
 */

select orders.orderid, orders.customerid, orders.shipcountry
from orders
where shipcountry in ('Brazil', 'Mexico', 'Argentina', 'Venezuela');

/* 7. Para todos os funcionários na tabela Empregados, mostre o Nome, Sobrenome, Cargo (Title)
 * e Data de nascimento. Ordene os resultados pela Data de Nascimento (BirthDate). Mostre
 * primeiro os funcionários mais antigos.
 */

select firstname, lastname, title, birthdate
from employees
order by birthdate, hiredate;

/* 8. Repita a consulta anterior (questão 7). Porém, apresente apenas a porção da data (sem a
 * hora) da coluna BirthDate.
 */
 
select firstname, lastname, title, date(birthdate)
from employees
order by birthdate, hiredate;

/* 9. Na tabela OrderDetails, temos os campos UnitPrice e Quantity. Crie um novo campo,
 * TotalPrice, que multiplica estes dois. Ignoraremos o campo Desconto. Além disso, mostre o
 * OrderID, ProductID, UnitPrice e Quantity. Ordene os resultados por OrderID e ProductID.
 * Resultado Esperado
 */
 
select orderid, productid, round(unitprice, 2), quantity, round(unitprice * quantity, 2) as Totalprice 
from orderdetails
order by orderid, productid;
 
/* 10. Quantos clientes temos na tabela Customers?
 */

select count(*) as TotalCustomers 
from customers;

/* 11. Mostre a data da primeira encomenda (OrderDate) feita na tabela Pedidos (Order).
 */

select min(orderdate) 
from orders;

/* 12. Mostrar uma lista de países, em ordem alfabética, onde a empresa possui clientes.
 */
 
select distinct country
from customers
order by country;

/* 13. Mostre uma lista de todos as funções (ou cargos) (ContactTitles) na tabela Customers. Inclua
 * também uma contagem para cada ContactTitles. Isto é semelhante em conceito à questãoanterior "Países onde há clientes", exceto que agora queremos contar o total de ContactTitle
 * e ordenar de maneira descendente.
 */

select contacttitle, count(contacttitle)
from customers
group by contacttitle
order by count(contacttitle) desc;

/* 14. Gostaríamos de mostrar, para cada produto, o fornecedor associado. Mostre o ProductID,
 * ProductName e CompanyName do Fornecedor. Classificar por ProductID.
 * Esta questão precisa da cláusula Join em SQL.
 */
 
select products.productid, products.productname, suppliers.companyname
from products
join suppliers on products.supplierid = suppliers.supplierid
order by productid;
