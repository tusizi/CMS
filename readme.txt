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

   将数据库操作的逻辑封装到dao（data access object）中duix

   利用简单工厂模式，将创建DAO的职责转移到简单工厂  ----但是这种设计方式违反了OCP设计原则

   利用工厂设计模式，避免了BeanFactory违反OCP设计原则
   --好处在于可以通过扩展来增加BeanFactory的能力
   --坏处在于客户端依赖于具体的工厂实现，而且客户端每多一个需求，就要创建一个新的具体的工厂类

   利用抽象工厂创建多个产品（所谓产品在这个项目里就是DAO）
   --假如要创建多个DAO，那么就定义一个抽象工厂，在抽象工厂中定义有哪些dao需要创建

   利用配置文件来避免客户端依赖于众多的具体的工厂实现

   将抽象工厂中的方法定义为更加通用的getBean方法，以及实现预先初始化DAO对象
   DAO对象不包含状态，只是纯粹的操作，就是多个线程同时访问一个DAO对象，也完全没有问题，
   所以没必要每次访问DAO都要创建一个DAO对象
   因此，在PropertiesBeanFactory中初始化所有的bean,每次getBean，只是直接返回一个已经初始化好的对象即可。

   在一个InitBeanFactroyServlet中初始化工厂对象，这个servlet不处理任何从页面传递过去的请求，他仅仅负责初始化

   利用DI（Dependency Injection）-依赖注入

   数据库初始化脚本及其运行
   先修改init.bat文件中的用户名和密码，再从命令行进入本目录（bin目录），执行init.bat脚本即可

   定义ArticleDao接口，并实现这个接口，然后对实现进行测试
    下一步：
    iBATIS - 针对JDBC进行封装
    apache-commons-beanutils - 针对request参数进行封装
    分页查询的封装 - 利用TheadLocal设计模式
