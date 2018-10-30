/* 
1. Selecione o total de produtos em cada categoria. 
Classifique os resultados pelo número total 
de produtos, em ordem decrescente. */

select c.CategoryName, count(*) as quantidade
from Products as p join Categories as c on c.CategoryID = p.CategoryId
group by c.CategoryName
order by quantidade desc;

/* 
2.  Na tabela Clientes, mostre o número total de clientes por país e cidade. */
select Country, City, count(*) as TotalCustomer 
from Customers
group by Country, City
Order by TotalCustomer desc;


/*
3. Quais os produtos que possuem estoque baixo, e precisam ser solicitados? Para esta questão, 
basta  usar  os  campos  UnitsInStock  e  ReorderLevel  (nível  mínimo  no  estoque),  onde  se 
UnitsInStock  for  menor  do  que  o  ReorderLevel,  deve  ser  solicitado.  Por  enquanto,  vamos 
desconsiderar os campos UnitsOnOrder e Discontinued. 
*/

select ProductId, ProductName, UnitsInStock, ReorderLevel
from products where UnitsInStock < ReorderLevel
order by ProductID;


/* 
4.  Agora  precisamos  incorporar  os  atributos  -  UnitsInStock,  UnitsOnOrder,  ReorderLevel, 
Discontinued - no nosso cálculo. Definiremos "produtos que precisam ser reordenados" com 
o seguinte: 
  • UnitsInStock + UnitsOnOrder deve ser menor ou igua a ReorderLevel 
  • Um produto está descontinuado, quando o seu valor é 0 (falso). 
*/

select ProductId, ProductName, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued
from products where (UnitsInStock + UnitsOnOrder) < ReorderLevel and Discontinued = false
order by ProductID;


/* 
5.  Um vendedor está indo em uma viagem de negócios para visitar os clientes e gostaria de ver 
uma lista com todos os clientes, classificados por região, em ordem alfabética. 
No entanto, ele quer que os clientes sem região (nulo no campo Região) estejam no final da 
listagem, em vez de no topo, onde você normalmente encontraria os valores nulos. Dentro da 
mesma região, as empresas devem ser classificadas por CustomerID.: 
*/

select CustomerID, CompanyName, Region, 
       (case when 
             Region is null then 1 
                            else 0 end) as RegionOrder 
from Customers
Order by RegionOrder, Region, CustomerID;

-- solução 2
select CustomerID, CompanyName, Region
from Customers
Order by (case when 
             Region is null then 1 
                            else 0 end), Region, CustomerID;

/*
6. Alguns dos países onde enviamos os produtos têm taxas de frete muito altas. Precisamos 
investigar algumas opções de envio para nossos clientes, para oferecer-lhes taxas de frete 
mais baixas. Retorne os três países com maior frete médio geral, em ordem decrescente pelo 
valor do frete médio. 
*/

select ShipCountry, avg(Freight) as mediaFrete 
from orders 
group by ShipCountry 
order by mediaFrete desc
limit 3;


/* 
7.  Continuamos com a pergunta acima sobre altas taxas de frete. Agora, Em vez de usar todos os 
pedidos que temos, só queremos ver pedidos a partir do ano de 2015. 
Dica utilize a função year()  
*/

select ShipCountry, avg(Freight) as mediaFrete 
from orders 
where year(OrderDate) = 2015 
group by ShipCountry 
order by mediaFrete desc
limit 3;


/*
8.  Agora queremos obter os três países com as taxas de frete médias mais elevadas. Mas em vez 
de  filtrar  para  um  determinado  ano,  queremos  usar  os  últimos  12  meses  de  pedidos realizados, usando como data final a data do último pedido (OrderDate) (coluna da tabela 
Orders) 
 
Dica: utiliza a função DateAdd(...).  
Veja no link: https://www.w3schools.com/sql/func_mysql_date_add.asp 
*/

select ShipCountry, avg(Freight) as mediaFrete 
from orders 
-- where DATE_ADD(now(), INTERVAL -1 Year)
where OrderDate >=  DATE_ADD( (select max(orderdate) from orders), INTERVAL -1 Year)
group by ShipCountry 
order by mediaFrete desc
limit 3;


/*
9.  Estamos  fazendo  inventário  e  precisamos  mostrar  informações  
para  todos  os  pedidos (orders). Classificar por OrderID e ID do produto. 
*/

select employees.employeeId,employees.LastName, Orders.OrderId, 
       products.productName, orderdetails.quantity
from employees join orders on employees.employeeID = orders.employeeId
               join orderdetails on orders.OrderID = orderdetails.OrderId 
               join products on orderdetails.productId = products.productId
order by orders.orderId, products.productId;
               
/*
10. Existem alguns clientes que nunca fizeram um pedido. Mostre esses clientes              
*/              
select  customers.customerID, orders.CustomerID
from customers 
        left join orders on orders.customerId = customers.customerId 
where orders.orderId is null;

-- solução 2

select  customers.customerID
from customers 
where not exists 
          ( select orders.CustomerID 
            from orders 
            where customers.customerID = orders.CustomerID );


/*
11. Uma funcionária (Margaret Peacock, EmployeeID 4) realizou a maioria dos pedidos de venda. 
No entanto, existem alguns clientes que nunca fizeram uma compra (encomenda) com ela. 
Recupere apenas esses clientes. 
*/

select  customers.customerID, orders.CustomerID
from customers left join orders on orders.customerId = customers.customerId 
                                   and orders.EmployeeID = 4
where orders.orderId is null;

-- solução 2
Select CustomerID from customers 
where CustomerID not in (select customerId from orders where EmployeeID = 4);
