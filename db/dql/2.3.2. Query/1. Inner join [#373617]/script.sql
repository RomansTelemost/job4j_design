create table if not exists customer(
	id serial primary key,
	name varchar(255)
);

create table if not exists contact(
	id serial primary key,
	phone text,
	customer_id int references customer(id)
);

insert into customer(name) values('Ilon');
insert into customer(name) values('Simen');
insert into customer(name) values('Dmitryi');

insert into contact(phone, customer_id) values('+1-913-222-99-43', 1);
insert into contact(phone, customer_id) values('+7-913-222-99-44', 2);

select cus.name, con.phone from customer as cus
join contact as con on cus.id = con.customer_id;

select cus.name, con.phone from customer as cus
join contact as con on cus.id = con.customer_id
where cus.name like 'Il%';

select cus.name, con.phone from customer as cus
join contact as con on cus.id = con.customer_id
where con.phone not like '%99-44';