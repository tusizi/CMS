drop table if exists t_admin;
create table t_admin(
id integer not null auto_increment,
username varchar(255) not null unique,
password varchar(255),
primary key (id)
);

insert into t_admin(username,password) values('admin','admin');
