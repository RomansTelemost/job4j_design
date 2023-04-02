insert into fauna(name, avg_age, discovery_date) values('Tiger', 20000, null);
insert into fauna(name, avg_age, discovery_date) values('Glass fish', 5000, '2000-04-10');
insert into fauna(name, avg_age, discovery_date) values('Glass fish', 12000, '2000-04-10');
insert into fauna(name, avg_age, discovery_date) values('Bakteria porutus', 100000, '1890-10-01');
insert into fauna(name, avg_age, discovery_date) values('Shark', 20, '1650-04-10');

select * from fauna where name like '%fish%';
select * from fauna where avg_age between '10000' and '21000';
select * from fauna where discovery_date is null;
select * from fauna where discovery_date > '1950-01-01';
