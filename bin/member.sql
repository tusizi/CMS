-- 创建会员信息表
drop table if exists t_member;
create table t_member(
id integer not null auto_increment,
name varchar(200),
email varchar(50),
nickName VARCHAR (50),
password VARCHAR (20),
qq VARCHAR (20),
phone VARCHAR (20),
address VARCHAR (255),
description longtext,
PRIMARY KEY (id)
);