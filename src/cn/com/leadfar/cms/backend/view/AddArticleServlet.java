package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by tusizi on 2015/11/3.
 */
@WebServlet(name = "AddArticleServlet")
public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从request中获取参数
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
        ArticleDao articleDao = new ArticleDaoImpl();
        Article a =new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setSource(source);
        articleDao.addArticle(a);
            request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request,response);
    }

}
