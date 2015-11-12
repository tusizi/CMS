package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

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
        //用类似下面的sql语句，对数据库的内容更新
        String sql = "update t_article set title=?, content=?, source = ?, updatetime = ? where id =?";//定义一个sql语句
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        try {
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setString(3,source);
            pstmt.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(5, Integer.parseInt(id));
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }

        //farword 到更新成功的界面
        request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request,response);
    }

}
