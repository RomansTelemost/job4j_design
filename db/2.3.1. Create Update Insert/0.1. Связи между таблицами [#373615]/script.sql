create table hotelRoom(
	id serial primary key,
	number int unique
);

create table if not exists passport(
	id serial primary key,
	seria int unique,
	number int unique
);

create table customer(
	id serial primary key,
	name varchar(255) unique,
	passport_id int references passport(id) unique
);

create table bookRooms(
	id serial primary key,
	fromDate date,
	hotelRoom_id int references hotelRoom(id) unique,
	customer_id int references customer(id)
);

create table bonus(
    id serial primary key,
    name varchar(255) unique
);

create table appliedBonuses(
    id serial primary key,
    customer_id int references customer(id),
    bonus_id int references bonus(id)
);