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

/**
 * Created by tusizi on 2015/11/5.
 */
@WebServlet(name = "OpenUpdateArticlesServlet")
        //打开更新文章的界面
public class OpenUpdateArticleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受从界面传过来的id
            String id = request.getParameter("id");
        //根据id从数据库中查询出要更新的文章
        String sql = "select * from t_article where id = ?";//定义一个sql语句
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        ResultSet rs = null;//定义一个结果集
        try {
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();//预处理语句执行查询，将结果放入到结果集rs里
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
           if (rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setSource(rs.getString("source"));
               request.setAttribute("article", article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }

        //farword到更新界面
        request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request,response);
    }
}
