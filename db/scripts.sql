create database mumu;
create table orders(
	id serial primary key,
	number INT,
	amount numeric(19,2)
);
alter table orders add description varchar(255);
insert into orders(number, amount, description) values(1, 100.25, 'Hello! Where is my order!');
insert into orders(number, amount, description) values(2, 1000, 'My order!');
update orders set amount=150.25 where id = 1;
delete from orders;