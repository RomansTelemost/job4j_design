create table if not exists departments(
	id serial primary key,
	name text
);

create table if not exists employees(
	id serial primary key,
	name text
);

alter table employees add if not exists departments_id int references departments(id);

insert into departments(name) values('IT');
insert into departments(name) values('HR');
insert into departments(name) values('Finance');
insert into departments(name) values('Marketing');

insert into employees(name, departments_id) values('Ivan it', 1);
insert into employees(name, departments_id) values('Ivan hr', 2);
insert into employees(name, departments_id) values('Ivan finance', 3);
insert into employees(name, departments_id) values('Ivan', null);

select e.name employees, d.name department from employees e
left join departments d on e.departments_id = d.id;

select e.name employees, d.name department from employees e
right join departments d on e.departments_id = d.id;

select e.name employees, d.name department from employees e
full join departments d on e.departments_id = d.id;

select e.name employees, d.name department from employees e
cross join departments d;

select d.name from departments d
left join employees e on e.departments_id = d.id where e.departments_id is null;

select e.name, d.name from employees e
left join departments d on e.departments_id = d.id;

select e.name, d.name from departments d
right join employees e on e.departments_id = d.id;

create table if not exists teens(
	id serial primary key,
	name text,
	gender bool
);

insert into teens(name, gender) values('Ivan', true);
insert into teens(name, gender) values('Inna', false);
insert into teens(name, gender) values('Stepan', true);
insert into teens(name, gender) values('Elena', false);

select t1.name, t2.name from teens t1
cross join teens t2
where t1.gender is true and t2.gender is false;