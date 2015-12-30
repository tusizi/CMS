# 创建keyword和article的一张中间表
# 其中：keyword和articleId联合起来不允许重复
drop table if exists t_article_keyword;
create table t_article_keyword(
keyword varchar(255) ,
articleId integer,
unique key article_keyword(keyword,articleId)
);
