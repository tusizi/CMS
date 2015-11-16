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
@WebServlet(name = "UpdateArticlesServlet")
        //更新文章功能
public class UpdateArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        ArticleDao articleDao= (ArticleDao)new PropertiesBeanFactory().getBean("articleDao");
        articleDao.updateArticle(article);
        //farword 到更新成功的界面
        request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request,response);
    }

}
