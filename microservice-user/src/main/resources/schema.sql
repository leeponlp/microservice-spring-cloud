drop table user if exists;
create table user(
	id int generated by default as identity,
	username varchar(40),
	age int(3),
	primary key(id)
);