-- 如果cms数据库已经存在，删除
DROP DATABASE IF EXISTS cms;
# 创建数据库cms
CREATE DATABASE cms;
# 使用数据库cms
USE cms;
# 调用admin.sql等脚本
source admin.sql;
source article.sql;
source channel.sql;
source channel_article.sql;
source article_keyword.sql;
source comment.sql;
source member.sql;
source topic.sql;
