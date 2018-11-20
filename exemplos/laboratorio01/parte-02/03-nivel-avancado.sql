use store;

/* 1. Queremos enviar aos nossos melhores clientes (clientes VIP) um presente. Clientes VIP
 * são aqueles que fizeram pelo menos 1 pedido com um valor total (não incluindo o
 * desconto) maior ou igual a $ 10.000. Para esta consulta vamos considerar apenas pedidos
 * realizados no ano de 2016.
 */



/* 2. O gerente mudou de ideia. Em vez de exigir que os clientes VIP tenham pelo menos um
 * pedido no total de $ 10.000 ou mais. Agora, são considerados Clientes VIP, aqueles que
 * tenham o somatório dos pedidos em 2016 maior ou igual a $ 15.000.
 */



/* 3. No final do mês, é provável que os vendedores tentem vender um pouco mais, para
 * alcançar às suas metas mensais. Mostre todos os pedidos feitos no último dia do mês.
 * Ordene por EmployeeID e OrderID.
 */

select employees.employeeid, orders.orderid, orders.orderdate
from employees
    join orders on employees.employeeid = orders.employeeid
where orders.orderdate = last_day(orders.OrderDate)
order by employees.employeeid, orders.orderid;

/* 4. Os desenvolvedores de aplicativos móveis estão testando um aplicativo que os clientes
 * usarão para exibir os pedidos realizados. Para se certificar de que até os pedidos maiores
 * serão exibidos corretamente no aplicativo e ajudar a equipe de desenvolvimento. Mostre
 * os 10 pedidos com mais itens em ordem decrescente.
 */

select orders.orderid, count(*) as TotalOrderDetails
from orders
    join orderdetails on orders.orderid = orderdetails.orderid
group by orders.orderid
order by count(*) desc
limit 10;

/* 5. Alguns clientes estão reclamando de atraso na entrega. Quais pedidos estão com atraso
 * na entrega?
 */
 
select orderid, date(orderdate), date(requireddate), date(shippeddate)
from orders
where shippeddate >= requireddate;

/* 6. Alguns vendedores têm mais pedidos chegando com atraso. Talvez precisem de mais
 * treinamento. Quais vendedores têm mais pedidos atrasados?
 */

select employees.employeeid, employees.lastname, count(*) as TotalLateOrders
from employees
    join orders on employees.employeeid = orders.employeeid
where orders.shippeddate >= orders.requireddate
group by employees.employeeid
order by count(*) desc;

/* 7. O gerente de vendas, pensando mais sobre os pedidos atrasados, percebeu que apenas
 * olhar para o número de pedidos (de cada vendedor) que chegam depois do prazo não é
 * suficiente. Ele decidiu comparar o total de pedidos de cada vendedor com o total de seus
 * pedidos que estão em atraso. Retorne os resultados conforme a figura que segue:
 */

select employees.employeeid, employees.lastname, count(*) as AllOrders, TotalLateOrders
from employees join orders on employees.employeeid = orders.employeeid
               join (select employees.employeeid, count(*) as TotalLateOrders
                    from employees join orders on employees.employeeid = orders.employeeid
                    where orders.shippeddate >= orders.requireddate
                    group by employees.employeeid) tlo on employees.employeeid = tlo.employeeid
group by employees.employeeid
order by employees.employeeid;

-- Resposta do Professor
select orders.EmployeeID, employees.LastName, count(*) as AllOrders,
    (select count(*)
        from orders o
        where ShippedDate >= RequiredDate and o.EmployeeID = orders.EmployeeID) as LateOrders
from orders 
join employees on orders.EmployeeID = employees.EmployeeID
GROUP BY employees.EmployeeID;
