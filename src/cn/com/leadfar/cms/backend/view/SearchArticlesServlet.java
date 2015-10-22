package cn.com.leadfar.cms.backend.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/10/22.
 */
@WebServlet(name = "SearchArticlesServlet")
public class SearchArticlesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询文章列表

        //forward到article_list.jsp
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request,response);
    }
}
