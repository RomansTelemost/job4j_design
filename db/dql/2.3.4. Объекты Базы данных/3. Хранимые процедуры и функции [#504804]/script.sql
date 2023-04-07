create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

create or replace procedure remove_data(u_count integer, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = u_id and count <= u_count;
    END;
$$;

call insert_data('item1', 'p1', 10, 100);
call insert_data('item2', 'p2', 14, 100);

call remove_data(10, 1);

create or replace function remove_less_data(u_count integer)
returns integer
language 'plpgsql'
as $$
	declare result integer;
    BEGIN
		select into result count(id) from products where count <= u_count;
		delete from products where count <= u_count;
		return result;
    END;
$$;


