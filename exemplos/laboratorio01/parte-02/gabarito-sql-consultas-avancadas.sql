use store;
/*
1.	Queremos enviar aos nossos melhores clientes (clientes VIP) um presente. 
Clientes VIP são aqueles que fizeram pelo menos 1 pedido com um valor total 
(não incluindo o desconto) 
maior ou igual a $ 10.000 com pedidos realizados no ano de 2016.*/

select  customers.CustomerID, 
        CompanyName, 
        orderdetails.OrderId, 
        sum(Quantity * UnitPrice) as TotalOrderAmount
from orders join orderdetails on orders.orderId = orderdetails.OrderID
            join customers on customers.customerId = orders.customerId
where year(OrderDate) = 2016
group by customers.customerID, customers.companyName, orders.orderID
having TotalOrderAmount > 10000 
order by TotalOrderAmount desc;        

/*2. O gerente mudou de ideia. Em vez de exigir que os clientes VIP 
tenham pelo menos um pedido no total de $ 10.000 ou mais. 
Agora, são considerados Clientes VIP, 
aqueles que tenham o somatório dos pedidos em 2016 maior ou igual a $ 15.000.  */           
            

select customers.CustomerID, 
       CompanyName, 
       sum(Quantity * UnitPrice) as TotalOrderAmount
from orders join orderdetails on orders.orderId = orderdetails.OrderID
            join customers on customers.customerId = orders.customerId
            where year(OrderDate) = 2016
            -- where OrderDate between '2016-01-01' and '2016-12-31'
            -- where OrderDate between '20160101' and '20161231'

group by customers.customerID, customers.companyName
having TotalOrderAmount > 15000 

order by TotalOrderAmount desc; 
      
/*3. No final do mês, é provável que os vendedores tentem vender um pouco mais, 
para alcançar às suas metas mensais. 
Mostre todos os pedidos feitos no último dia do mês. Ordene por EmployeeID e OrderID. */  

 select EmployeeId, OrderId, Date(orderDate)
 from orders
 where Date(orderDate) = Last_day(orderDate);

/* 
4.  Os desenvolvedores de aplicativos móveis estão testando um aplicativo que os clientes 
usarão para exibir os pedidos realizados. Para se certificar de que até os pedidos maiores 
serão exibidos corretamente no aplicativo e ajudar a equipe de desenvolvimento. Mostre 
os 10 pedidos com mais itens em ordem decrescente. 
*/

select * from orders join orderdetails using(orderID);
select * from orders join orderdetails on orders.orderId = orderdetails.orderId;


select orders.orderId, count(orderdetails.orderId) as TotalOrderDetails
from orders join orderdetails on orders.orderid = orderdetails.orderId
group by orders.orderId
order by TotalOrderDetails desc;

/* 
5. 
Alguns clientes estão reclamando de atraso na entrega. 
Quais pedidos estão com atraso na entrega?  
*/

select OrderId, orderDate, requiredDate, shippedDate
from orders 
where shippedDate >= requiredDate;

/* 
6.  Alguns vendedores têm mais pedidos chegando com atraso. 
Talvez precisem de mais treinamento. 
Quais vendedores têm mais pedidos atrasados? 
*/ 
 
select orders.employeeId, 
       employees.lastName,
       count(orderId) as NumberOfOrders
from orders join employees
where orders.employeeId = employees.employeeId and 
                          shippedDate >= requiredDate
group by orders.employeeId
order by NumberOfOrders desc;
 
 
/* 7. 
O gerente de vendas, pensando mais sobre os pedidos atrasados, percebeu que apenas 
olhar para o número de pedidos (de cada vendedor) que chegam depois do prazo não é 
suficiente. Ele decidiu comparar o total de pedidos de cada vendedor com o total de seus 
pedidos que estão em atraso. 
*/


-- passo 1 total de pedidos em atraso por vendedor
SELECT COUNT(OrderID)
       FROM orders 
	   WHERE ShippedDate >= RequiredDate and EmployeeID = 4;

-- passo 2
SELECT 
    orders.EmployeeID,
    employees.LastName,
    COUNT(orders.OrderID) AS AllOrders,
    (SELECT COUNT(o.OrderID)
       FROM orders o 
	   WHERE ShippedDate >= RequiredDate and o.EmployeeID = orders.EmployeeID) AS LateOrders  
  
FROM orders JOIN employees ON orders.EmployeeID = employees.EmployeeID
GROUP BY employees.EmployeeID;
