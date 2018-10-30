use store;

/* 1. Selecione o total de produtos em cada categoria. Classifique os resultados pelo número total
 * de produtos, em ordem decrescente.
 */
 
select categories.categoryname, count(*) as TotalProducts 
from products
    join categories on categories.categoryid = products.categoryid
group by categories.categoryname
order by count(*) desc;

/* 2. Na tabela Clientes, mostre o número total de clientes por país e cidade.
 */

select country, city, count(*) as TotalCustomer
from customers
group by country, city
order by count(*) desc;

/* 3. Quais os produtos que possuem estoque baixo, e precisam ser solicitados? Para esta questão,
 * basta usar os campos UnitsInStock e ReorderLevel (nível mínimo no estoque), onde se
 * UnitsInStock for menor do que o ReorderLevel, deve ser solicitado. Por enquanto, vamos
 * desconsiderar os campos UnitsOnOrder e Discontinued.
 * Ordene os resultados por ProductID.
 */
 
select productid, productname, unitsinstock, reorderlevel
from products
where unitsinstock < reorderlevel
order by productid;

/* 4. Agora precisamos incorporar os atributos - UnitsInStock, UnitsOnOrder, ReorderLevel,
 * Discontinued - no nosso cálculo. Definiremos "produtos que precisam ser reordenados" com
 * o seguinte:
 * • UnitsInStock + UnitsOnOrder deve ser menor ou igua a ReorderLevel
 * • Um produto está descontinuado, quando o seu valor é 0 (falso).
 */

select productid, productname, unitsinstock, unitsonorder, reorderlevel, discontinued
from products
where unitsinstock + unitsonorder <= reorderlevel and discontinued = 0;

/* 5. Um vendedor está indo em uma viagem de negócios para visitar os clientes e gostaria de ver
 * uma lista com todos os clientes, classificados por região, em ordem alfabética.
 * No entanto, ele quer que os clientes sem região (nulo no campo Região) estejam no final da
 * listagem, em vez de no topo, onde você normalmente encontraria os valores nulos. Dentro da
 * mesma região, as empresas devem ser classificadas por CustomerID.:
 * Dica: Pesquise sobre a clausula case when no link
 * https://www.w3schools.com/sql/func_mysql_case.asp
 * ( case when Region is null then ... ) as RegionOrder
 */

select customers.customerid, customers.companyname, customers.region, 
    (case when region is null then 1
                              else 0 end) as RegionOrder
from customers
order by RegionOrder, customers.region, customers.customerid;

/* 6. Alguns dos países onde enviamos os produtos têm taxas de frete muito altas. Precisamos
 * investigar algumas opções de envio para nossos clientes, para oferecer-lhes taxas de frete
 * mais baixas. Retorne os três países com maior frete médio geral, em ordem decrescente pelo
 * valor do frete médio.
 */

select shipcountry, round(avg(freight), 4) as AverageFreight
from orders
group by shipcountry
order by AverageFreight desc
limit 3;

/* 7. Continuamos com a pergunta acima sobre altas taxas de frete. Agora, Em vez de usar todos os
 * pedidos que temos, só queremos ver pedidos a partir do ano de 2015.
 * Dica utilize a função year()
 * [https://www.w3schools.com/sql/func_mysql_year.asp]
 */ 

select shipcountry, round(avg(freight), 4) as AverageFreight
from orders
where year(shippeddate) >= '2015'
group by shipcountry
order by AverageFreight desc
limit 3;

/* 8. Agora queremos obter os três países com as taxas de frete médias mais elevadas. Mas em vez
 * de filtrar para um determinado ano, queremos usar os últimos 12 meses de pedidosrealizados, 
 * usando como data final a data do último pedido (OrderDate) (coluna da tabela Orders)
 * Dica: utiliza a função DateAdd(...).
 * Veja no link: https://www.w3schools.com/sql/func_mysql_date_add.asp
 */



/* 9. Estamos fazendo inventário e precisamos mostrar informações para todos os pedidos
 * (orders). Classificar por OrderID e ID do produto. Veja na figura que segue
 */

select employees.employeeid, employees.lastname, orders.orderid, products.productname, orderdetails.quantity
from employees
    join orders on employees.employeeid = orders.employeeid
    join orderdetails on orders.orderid = orderdetails.orderid
    join products on orderdetails.productid = products.productid
order by orders.orderid, products.productid;

/* 10. Existem alguns clientes que nunca fizeram um pedido. Mostre esses clientes.
 */

select customers.customerid as CustomersCustomerID
from customers
where not exists (select orders.orderid
                  from orders
                  where customers.customerid = orders.customerid);

select customers.customerid as CustomersCustomerID, orders.customerid as OrdersCustomeID
from customers
    left join orders on customers.customerid = orders.customerid
where orders.customerid is null;

/* 11. Uma funcionária (Margaret Peacock, EmployeeID 4) realizou a maioria dos pedidos de venda.
 * No entanto, existem alguns clientes que nunca fizeram uma compra (encomenda) com ela.
 * Recupere apenas esses clientes.
 */
