CREATE TABLE IF NOT EXISTS customer (
    first_name text,
    last_name text
);

CREATE TABLE IF NOT EXISTS employee (
    first_name text,
    last_name text
);

INSERT INTO customer
VALUES ('Иван', 'Иванов'),
       ('Петр', 'Сергеев'),
       ('Ирина', 'Бросова'),
       ('Анна', 'Опушкина'),
       ('Потап', 'Потапов');

INSERT INTO employee
VALUES ('Кристина', 'Позова'),
       ('Михаил', 'Кругов'),
       ('Анна', 'Опушкина'),
       ('Иван', 'Иванов'),
       ('Сергей', 'Петров');

select * from customer
union
select * from employee;

select * from customer
union all
select * from employee;

select * from customer
except
select * from employee;

select * from customer
intersect
select * from employee;