create table if not exists customer(
	id serial primary key,
	name text,
	sex boolean
);

create table if not exists address(
	id serial primary key,
	address text,
	customer_id int references customer(id)
);

create table if not exists movie(
	id serial primary key,
	name varchar(255)
);

create table if not exists videoRental(
	id serial primary key,
	customer_id int references customer(id),
	movie_id int references movie(id)
);

insert into customer(name, sex) values ('Ivan', true);
insert into customer(name, sex) values ('Elena', false);

insert into address(address, customer_id) values ('1ый переулок д. 40, кв. 3', 1);
insert into address(address, customer_id) values ('2ой переулок д. 5, кв. 3', 2);

insert into movie(name) values ('Матрица');
insert into movie(name) values ('Интерстеллар');
insert into movie(name) values ('Человек - паук');

insert into videoRental(customer_id, movie_id) values (1, 1);
insert into videoRental(customer_id, movie_id) values (1, 2);
insert into videoRental(customer_id, movie_id) values (2, 2);
insert into videoRental(customer_id, movie_id) values (2, 3);