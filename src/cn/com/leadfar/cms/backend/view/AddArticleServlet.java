package cn.com.leadfar.cms.backend.view;

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
        //接受请求用utf-8
        request.setCharacterEncoding("UTF-8");
        //从request中获取参数
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
        //将数据插入数据库
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt =null;
        String sql = "insert into t_article (title,content,source,createtime) values (?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setString(3,source);
            pstmt.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtil.rollback(conn);
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
            request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request,response);
    }

}
