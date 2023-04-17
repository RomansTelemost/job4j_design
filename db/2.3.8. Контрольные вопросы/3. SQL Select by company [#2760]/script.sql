CREATE TABLE IF NOT EXISTS company(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name) VALUES(1, 'DQL');
INSERT INTO company(id, name) VALUES(2, 'DDL');
INSERT INTO company(id, name) VALUES(5, 'DML');

INSERT INTO person(id, name, company_id) VALUES(1, 'A', 1);
INSERT INTO person(id, name, company_id) VALUES(2, 'B', 1);
INSERT INTO person(id, name, company_id) VALUES(3, 'C', 1);
INSERT INTO person(id, name, company_id) VALUES(4, 'D', 2);
INSERT INTO person(id, name, company_id) VALUES(5, 'E', 2);
INSERT INTO person(id, name, company_id) VALUES(6, 'F', 2);
INSERT INTO person(id, name) VALUES(7, 'G', 5);
INSERT INTO person(id, name) VALUES(8, 'H');

select per.name as "Person name", com.name as "Company name" from person as per
left join company as com on per.company_id = com.id
where com.id is null or NOT com.id = 5;

select com.name, count(per.id) from company as com
left join person as per on com.id = per.company_id
Group by com.name having count(per.id) >
(select count(per.id) from company as com
left join person as per on com.id = per.company_id
Group by com.name limit 1);