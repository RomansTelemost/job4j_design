create table if not exists type(
	id serial primary key,
	name text
);

create table if not exists product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('ДЕСЕРТЫ');

insert into product(name, type_id, expired_date, price) values('Сливочный', 1, '2023-04-02', 200);
insert into product(name, type_id, expired_date, price) values('Голландский', 1, '2023-04-15', 250);
insert into product(name, type_id, expired_date, price) values('Молоко домик в деревне', 2, '2023-04-9', 100);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 1', 3, '2023-04-01', 150);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 2', 3, '2023-04-10', 200);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 3', 3, '2023-04-10', 150);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 4', 3, '2023-04-10', 150);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 5', 3, '2023-04-10', 150);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 6', 3, '2023-04-10', 150);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 7', 3, '2023-04-10', 150);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 8', 3, '2023-04-10', 2000);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 9', 3, '2023-04-10', 300);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 10', 3, '2023-04-10', 2000);
insert into product(name, type_id, expired_date, price) values('Шоколадное мороженное 11', 3, '2023-04-10', 1000);

select p.name, t.name from product as p
join type as t on p.type_id = t.id
where t.name = 'СЫР';

select p.name from product as p
where lower(p.name) like '%мороженное%';

select p.name, p.expired_date from product as p
where p.expired_date <= now();

select p.name from product as p
where price = (select max(p.price) from product as p);

select t.name Тип, count(p.id) Количество from product as p
join type as t on p.type_id = t.id
group by t.name;

select p.name Продукт from product as p
join type as t on p.type_id = t.id
where upper(t.name) = 'СЫР' or upper(t.name) = 'МОЛОКО';

select t.name from product as p
join type as t on p.type_id = t.id
group by t.name
having count(p.id) < 10;

select t.name, p.name from product as p
join type as t on p.type_id = t.id;


