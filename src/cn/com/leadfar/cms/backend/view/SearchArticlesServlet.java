package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * Created by tusizi on 2015/10/22.
 */
@WebServlet(name = "SearchArticlesServlet")
public class SearchArticlesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        ArticleDao articleDao = new ArticleDaoImpl();
        PageVO pv = articleDao.findArticles(title, offset, pagesize);
        request.setAttribute("pv", pv);
//        //将共有多少页total传递
//        int maxPage = total / pagesize;
//        if (total % pagesize !=0){
//            maxPage = maxPage +1;
//        }
//        request.setAttribute("maxPage",maxPage);
//        //传递现在是多少页currentPage
//        int currentPage = offset / pagesize + 1;
//        request.setAttribute("currentPage",currentPage);
//        //传递共有几条记录
        //forward到article_list.jsp
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
    }
}
