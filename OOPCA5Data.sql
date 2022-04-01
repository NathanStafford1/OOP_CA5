DROP DATABASE IF EXISTS OOPCA5;
CREATE DATABASE IF NOT EXISTS OOPCA5;
USE OOPCA5;
drop table if exists games;

create table games ( game_ID int NOT NULL,
name varchar(30),
price double,
quantity int,
PRIMARY KEY (game_ID));

insert into games values(1,"Halo", 49.99, 5);
insert into games values(2,"Forza", 59.99, 17);
insert into games values(3,"Minecraft", 19.99, 37);
insert into games values(4,"Fifa", 49.99, 11);
insert into games values(5,"NBA", 49.99, 24);
insert into games values(6,"Rocket league", 29.99, 19);
insert into games values(7,"Fortnite", 0.0, 100);
insert into games values(8,"F1", 49.99, 14);
insert into games values(9,"Call of duty", 49.99, 5);
insert into games values(10,"Borderlands", 39.99, 17);

//Auto increment for game_id