package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;

import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/11/5.
 */
@WebServlet(name = "OpenUpdateArticlesServlet")
        //打开更新文章的界面
public class OpenUpdateArticleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受从界面传过来的id
            String id = request.getParameter("id");
        ArticleDao articleDao= (ArticleDao)new PropertiesBeanFactory().getBean("articleDao");
        Article article = articleDao.findArticleById(Integer.parseInt(id));
        request.setAttribute("article",article);
        //farword到更新界面
        request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request,response);
    }
}
