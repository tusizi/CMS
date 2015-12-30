drop table if exists t_article;
create table t_article(
  id integer not null auto_increment,
  articleId INTEGER ,
  contentType varchar(100),
  name varchar(200),
  uploadtime datetime,
  primary key (id)
);