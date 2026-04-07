create table Customers(
    customer_id int primary key,
    name varchar(50),
    city varchar(50)
);

create table Products(
    product_id int primary key,
    name varchar(50),
    category varchar(50),
    price decimal(10,3)
);

create table Orders(
    orders_id int primary key,
    customer_id int references Customers(customer_id),
    order_date date
)

create table Order_items(
    orders_id int references Orders(orders_id),
    product_id int references Products(product_id),
    quantity int
    primary key (orders_id,product_id)
);


--SQL Queries

--Top selling Products

select p.product_id as id,p.name as product_name,p.category as product_category,p.price as product_price,sum(o.quantity) as quantity_sold
from Products p right join Order_items o
on p.product_id=o.product_id
group by p.product_id
order by quantity_sold desc

--identifing most valuable customers

select c.customer_id as customer_id,c.name as name,c.city as city,sum(oi.quantity*p.price) amount_paid
from Customers c right join Orders o on
c.customer_id=o.customer_id
right join Order_items oi
on o.orders_id=oi.orders_id
left join Products p
on oi.product_id=p.product_id
group by c.customer_id
order by amount_paid desc


--Monthly revenue calculation

select date_format(o.order_date,'%Y-%m') as month, sum(p.price*oi.quantity) as monthly_earnings
from Orders o join Order_items oi
on o.orders_id=oi.orders_id
left join Products p
on oi.product_id=p.product_id
group by date_format(o.order_date,'%Y-%m')


--category-wise sales analysis

select sum(p.price*oi.quantity)as category_earnings,p.category
from Products p join Order_items oi
on p.product_id=oi.product_id
group by p.category
order by category_earnings desc


--detect inactive customers

select *
from Customers 
where customer_id not in (select customer_id from Orders)








