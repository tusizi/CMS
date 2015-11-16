package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ArticleDao;

import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/11/4.
 */
@WebServlet(name = "DelArticlesServlet")
public class DelArticlesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从界面获取删除文章的id
       // String id = request.getParameter("id");
        //从界面获得一组id 值
        String[] ids = request.getParameterValues("id");
        if (ids == null){
            //提示错误 forward 到错误页面
            request.setAttribute("error","无法删除文章，ID不允许为空");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
        }
        ArticleDao articleDao=  new PropertiesBeanFactory().getArticleDao();
        articleDao.delArticles(ids);
        //转向列表页面
        request.getRequestDispatcher("/backend/SearchArticlesServlet").forward(request,response);
    }
}
