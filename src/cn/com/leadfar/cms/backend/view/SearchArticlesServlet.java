package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusizi on 2015/10/22.
 */
@WebServlet(name = "SearchArticlesServlet")
public class SearchArticlesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询文章列表
        List articles = new ArrayList();
        for(int i =0 ; i<10;i++){
            Article a = new Article();
            a.setTitle("这是文章标题");
            a.setContext("这是文章内容"+i);
            articles.add(a);
        }
        request.setAttribute("articles",articles);
        //forward到article_list.jsp
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request,response);
    }
}
