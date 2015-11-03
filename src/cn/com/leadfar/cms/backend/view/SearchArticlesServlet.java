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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询文章列表
        List articles = new ArrayList();//定义一个文章列表
        String sql = "select * from t_article";//定义一个sql语句
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        ResultSet rs = null;//定义一个结果集
        try {
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            rs = pstmt.executeQuery();//预处理语句执行查询，将结果放入到结果集rs里
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
            while(rs.next()){
                Article article = new Article();
                article.setTitle(rs.getString("title"));
                article.setContext(rs.getString("content"));
                article.setSource(rs.getString("source"));
                article.setCreatetime(rs.getTimestamp("createtime"));
                article.setUpdatetime(rs.getTimestamp("updatetime"));
                article.setDeploytime(rs.getTimestamp("deploytime"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        request.setAttribute("articles", articles);
        System.out.println(articles);
        //forward到article_list.jsp
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
    }
}
