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

   