CREATE TABLE  t_article(
id INTEGER NOT NULL auto_increment,
title VARCHAR(200),
content longtext,
source VARCHAR(255),
createtime datetime,
updatetime datetime,
deploytime datetime,
PRIMARY KEY(id)
);
