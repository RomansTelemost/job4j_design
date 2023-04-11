CREATE TABLE IF NOT EXISTS customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE IF NOT EXISTS orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO customers(first_name, last_name, age, country) VALUES('Rom', 'Sa', 10, 'England');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Sam', 'Gem', 25, 'New Zealand');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Fro', 'Bag', 26, 'Shire');
INSERT INTO customers(first_name, last_name, age, country) VALUES('Gen', 'Grey', 150, Null);

INSERT INTO orders(amount, customer_id) VALUES(100, 1);
INSERT INTO orders(amount, customer_id) VALUES(1500, 2);
INSERT INTO orders(amount, customer_id) VALUES(40, 3);

select * from customers where age = (select min(age) from customers);
select * from customers where id not in (select customer_id from orders);