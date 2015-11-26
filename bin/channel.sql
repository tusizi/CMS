drop table if EXISTS t_channel;
create table t_channel(
id integer not null auto_increment,
name varchar(200),
description longtext,
primary key (id)
);

insert into t_channel (name) values('JavaEE');
insert into t_channel (name) values('JavaSE');
insert into t_channel (name) values('JBPM');
insert into t_channel (name) values('Anjroid');
insert into t_channel (name) values('OpenSource');
insert into t_channel (name) values('分析与设计');

