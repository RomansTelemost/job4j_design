create table people(
    id serial primary key,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(50)
);

--\d people - структура таблицы. В низу видно что создан кластерный индекс по умолчанию по id

create index people_last_name on people(last_name desc);