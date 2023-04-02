create table furniture(
	id serial primary key,
	name varchar(255)
);

create table part(
	id serial primary key,
	name varchar(255)
);

create table furniture_parts(
	id serial primary key,
	furniture_id int references furniture(id),
	part_id int references part(id)
);

insert into part(id, name) values(1, 'Door');
insert into part(id, name) values(2, 'Door hinch');
insert into part(id, name) values(3, 'Screw');
insert into part(id, name) values(4, 'Shelf');
insert into part(id, name) values(5, 'Door handle');
insert into part(id, name) values(6, 'Table leg');
insert into part(id, name) values(7, 'Wooden canvas');