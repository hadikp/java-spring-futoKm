create table trainings(id bigint auto_increment PRIMARY KEY, name varchar(50), description varchar(50), training_date date);
insert into trainings(name, description, training_date) values ('futás', 'Péter hosszú futása', '2022-02-23');
insert into trainings(name, description, training_date) values ('futás', 'Péter rövid kocogása', '2022-02-24');