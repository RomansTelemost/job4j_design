insert into role(role_name) values('Superuser');
insert into role(role_name) values('Acountant');

insert into users(user_name, role_id) values('Tatiana', 2);
insert into users(user_name, role_id) values('Ivan', 1);

insert into rules(rules_name) values('AccessToLogin');
insert into rules(rules_name) values('AccessToOrders');
insert into rules(rules_name) values('AccessToPlaning');

insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);
insert into role_rules(role_id, rules_id) values(1, 3);

insert into role_rules(role_id, rules_id) values(2, 1);
insert into role_rules(role_id, rules_id) values(2, 2);

insert into category(category_name) values('Groceries');
insert into category(category_name) values('Repair parts');

insert into state(state_name) values('Request');
insert into state(state_name) values('Agreement');
insert into state(state_name) values('Delivery');
insert into state(state_name) values('Payment');



