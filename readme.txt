��̨����demo
在几乎所有的后台管理有关的jsp和servlet中，判断HTTP SERVLET里是否有LOGIN_ADMIN这个Attribute，
如果没有则转向登录页面，如果有则不转向。

定义一个LoginFilter,并配置它匹配/backend/*,即在/backend/下面的所有请求，均经过这个LoginFilter

url-pattern 的定义只有三种形式：
1.精确匹配
如：/backend/main.jsp
    /backend/left.jsp
    /backend/ArticleServlet
2.目录匹配
如：
   /backend/*
3、扩展名匹配
如：
   *.jsp

   url-pattern不支持以下方式，如：
   /backend/*.jsp
   使用这个表达式的意图是匹配/backend/下面的所有的jsp,url-pattern不支持
   ----------------------------------------------------------------------------
   利用正则表达式对/backend/下的请求进行二次过滤，以便只对servlet和jsp进行拦截过滤

   查询文章列表

   添加文章

   逐条删除文章

   批量删除文章

   完成频道管理功能，--id,name,description

   完成分页处理的功能  limit

   引入page-taglib分页

   将分页处理的JSP代码封装在一个通用的JSP中

   将数据库操作的逻辑封装到dao（data access object）中

   利用简单工厂模式，将创建DAO的职责转移到简单工厂  ----但是这种设计方式违反了OCP设计原则

   利用工厂设计模式，避免了BeanFactory违反OCP设计原则
   --好处在于可以通过扩展来增加BeanFactory的能力
   --坏处在于客户端依赖于具体的工厂实现，而且客户端每多一个需求，就要创建一个新的具体的工厂类

   利用抽象工厂创建多个产品（所谓产品在这个项目里就是DAO）
   --假如要创建多个DAO，那么就定义一个抽象工厂，在抽象工厂中定义有哪些dao需要创建

   利用配置文件来避免客户端依赖于众多的具体的工厂实现
