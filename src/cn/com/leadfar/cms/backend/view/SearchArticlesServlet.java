package cn.com.leadfar.cms.backend.view;

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
        //查询文章列表
        List articles = new ArrayList();//定义一个文章列表
        String sql = "select * from t_article limit ?,?";//定义一个sql语句
        if(title != null){
            //模糊查询
             sql ="select * from t_article where title like '%"+title+"%' limit ?,?";
        }
        String sqlForTotal = "select count(*) from t_article";
        if(title!= null){
            sqlForTotal="select count(*) from t_article where title like '%"+title+"%'";
        }
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        PreparedStatement pstmtForTotal = null;
        ResultSet rsForTotal = null;
        ResultSet rs = null;//定义一个结果集
        int total = 0;
        try {
            //查询总记录数
            pstmtForTotal = conn.prepareStatement(sqlForTotal);
            rsForTotal = pstmtForTotal.executeQuery();
            while (rsForTotal.next()){
                total = rsForTotal.getInt(1);
            }
            //查询当前页的数据
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setInt(1,offset);
            pstmt.setInt(2,pagesize);
            rs = pstmt.executeQuery();//预处理语句执行查询，将结果放入到结果集rs里
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
            while(rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setSource(rs.getString("source"));
                article.setCreatetime(rs.getTimestamp("createtime"));
                article.setUpdatetime(rs.getTimestamp("updatetime"));
                article.setDeploytime(rs.getTimestamp("deploytime"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rsForTotal);
            DBUtil.close(pstmtForTotal);
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        request.setAttribute("articles", articles);
        //将共有多少页total传递
        int maxPage = total / pagesize;
        if (total % pagesize !=0){
            maxPage = maxPage +1;
        }
        request.setAttribute("maxPage",maxPage);
        //传递现在是多少页currentPage
        int currentPage = offset / pagesize + 1;
        request.setAttribute("currentPage",currentPage);
        //传递共有几条记录
        request.setAttribute("total",total);
        System.out.println(articles);
        //forward到article_list.jsp
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
    }
}
