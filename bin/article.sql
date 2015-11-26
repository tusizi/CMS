drop table if exists t_article;
create table t_aricle(
  id integer not null auto_increment,
  title varchar(200),
  source varchar(255),
  content longtext,
  keyword varchar(255),
  intro varchar(2000),
  type varchar(50),
  author varchar(100),
  recommend boolean,
  headline boolean,
  leaveNumber integer,
  clickNumber integer,
  adminId integer,
  topicId integer,
  createtime datetime,
  updatetime datetime,
  deploytime datetime,
  primary key (id)
);