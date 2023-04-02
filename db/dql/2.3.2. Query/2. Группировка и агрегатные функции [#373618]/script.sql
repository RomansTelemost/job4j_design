create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table if not exists people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('Laptop', 3500);
insert into devices(name, price) values('Smartphone', 2500);
insert into devices(name, price) values('PC', 10000);

insert into people(name) values('Semen');
insert into people(name) values('Vitaly');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(3, 2);
insert into devices_people(device_id, people_id) values(2, 2);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people as dp
join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;


