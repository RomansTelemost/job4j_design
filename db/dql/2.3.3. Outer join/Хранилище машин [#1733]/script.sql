create table if not exists car_bodies(
	id serial primary key,
	name varchar(255)
);
create table if not exists car_engines(
	id serial primary key,
	name varchar(255)
);
create table if not exists car_transmissions(
	id serial primary key,
	name varchar(255)
);
create table if not exists cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Sedan');
insert into car_bodies(name) values('Hatchback');
insert into car_bodies(name) values('SUV');
insert into car_bodies(name) values('Limo');

insert into car_engines(name) values('1.6');
insert into car_engines(name) values('1.8');
insert into car_engines(name) values('2.0');

insert into car_transmissions(name) values('Mechanical');
insert into car_transmissions(name) values('Automatic');

insert into cars(name, body_id, engine_id, transmission_id) values('Audi', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('BMV', 2, null, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Volvo', 3, 3, 1);

select c.id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name from cars as c
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id;

select cb.name from car_bodies cb
left join cars c on c.body_id = cb.id
where c.id is null;

select ce.name from car_engines ce
left join cars c on c.engine_id = ce.id
where c.id is null;

select ce.name from car_engines ce
left join cars c on c.engine_id = ce.id
where c.id is null;

select ct.name from car_transmissions ct
left join cars c on c.transmission_id = ct.id
where c.id is null;
