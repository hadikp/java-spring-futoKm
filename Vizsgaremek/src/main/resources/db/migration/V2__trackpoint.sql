create table trackpoints(id bigint auto_increment PRIMARY KEY, name varchar(50), elevation double, coordinate_id bigint);
insert into trackpoints(name, elevation, coordinate_id) values ('Tóváros közepe', 102.2, 1);
insert into trackpoints(name, elevation, coordinate_id) values ('Lövölde eleje', 114.1, 2);
insert into trackpoints(name, elevation, coordinate_id) values ('Lövölde vége', 111.7, 3);
insert into trackpoints(name, elevation, coordinate_id) values ('Halesz', 114.1, 4);
insert into trackpoints(name, elevation, coordinate_id) values ('Budai-nagyszombati út', 151.4, 5);
insert into trackpoints(name, elevation, coordinate_id) values ('Zsolt utca-körgyűrű', 123.5, 6);
insert into trackpoints(name, elevation, coordinate_id) values ('Berényi utca-körgyűrű', 121.1, 7);
