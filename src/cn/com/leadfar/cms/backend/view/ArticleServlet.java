package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.BeanFactory;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/11/18.
 */
public class ArticleServlet extends BaseServlet {
    private ArticleDao articleDao;
    //在这个方法中执行查询工作
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offset = 0;
        int pagesize = 5;
        //希望从request中获取pager.offset
        try {
            offset = Integer.parseInt(request.getParameter("pager.offset"));
        }catch (Exception ignore){}
        //如果从request中传递过来pagesize,那么就需要更新http session中的pagesize
        if(request.getParameter("pagesize") != null){
            request.getSession().setAttribute("pagesize",
                    Integer.parseInt(request.getParameter("pagesize"))
            );
        }
        //希望从http session中获取pagesize

        Integer ps= (Integer)request.getSession().getAttribute("pagesize");
        if (ps==null){
            pagesize=5;
            request.getSession().setAttribute("pagesize",pagesize);
        }else {
            pagesize=ps;
        }
        //从界面中获取title参数
        String title = request.getParameter("title");
        PageVO pv = articleDao.findArticles(title, offset, pagesize);
        request.setAttribute("pv", pv);
        System.out.println(pv);
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
    }

    //添加文章
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从request中获取参数
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
        String author = request.getParameter("author");
        String keyword = request.getParameter("keyword");
        String intro = request.getParameter("intro");
        String type = request.getParameter("type");
        //String recommend =request.getParameter("recommend");
        //String recommend =request.getParameter("headline");
        //String channels = request.getParameter("channels");
       // String topicId = request.getParameter("topicId");
        //String adminId = request.getParameter("adminId");
        Article a =new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setSource(source);
        articleDao.addArticle(a);
        request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request,response);
    }

    //删除文章
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从界面获取删除文章的id
        // String id = request.getParameter("id");
        //从界面获得一组id 值
        String[] ids = request.getParameterValues("id");
        if (ids == null){
            //提示错误 forward 到错误页面
            request.setAttribute("error","无法删除文章，ID不允许为空");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
        }
        articleDao.delArticles(ids);
        //转向列表页面
        //request.getRequestDispatcher("/backend/SearchArticlesServlet").forward(request,response);
        response.sendRedirect(request.getContextPath()+"/backend/ArticleServlet");
    }

    //打开更新界面
    public void openUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受从界面传过来的id
        String id = request.getParameter("id");

        Article article = articleDao.findArticleById(Integer.parseInt(id));
        request.setAttribute("article",article);
        //farword到更新界面
        request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request,response);
    }

    //更新文章
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接受到更新的内容，包括（title content）
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
        Article article = new Article();
        article.setTitle(title);
        article.setSource(source);
        article.setContent(content);
        article.setId(Integer.parseInt(id));

        articleDao.updateArticle(article);
        //farword 到更新成功的界面
        request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request,response);
    }
    //本set方法，定义了一个articleDao这样的一个property
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}


