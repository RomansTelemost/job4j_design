create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id in (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trig
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 10, 50);

create or replace function tax_row()
    returns trigger as
$$
    BEGIN
		new.price = new.price + new.price * 0.2;
    	return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trig_row
    before insert on products
    for each row
    execute procedure tax_row();

create table if not exists history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function log_products()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values(new.name, new.price, now());
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger products_trigger
    after insert on products
    for each row
    execute procedure log_products();
