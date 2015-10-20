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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tusizi on 2015/10/20.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        String username =  request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        //系统判断验证码是否正确
        //刚刚生成的验证码串
            String sessionCode = (String) request.getSession().getAttribute("codes");//得到的不是string类型吗
            if(!sessionCode.equalsIgnoreCase(checkcode)){
                //网页重定向forward到login.jsp页面
                request.setAttribute("error","验证码错误");
                request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
                return;
            }
        //系统判断用户名是否存在，密码是否正确
        String sql = "select * from t_cms where username = ?";
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            String pass = rs.getString("password");
            //系统判断用户名是否存在
            if(rs.next()){
                //密码是否正确
                if(!password.equals(pass)){
                    request.setAttribute("error","密码不正确");
                    request.getRequestDispatcher(request.getContextPath()+"/backend/login.jsp");
                    return;
                }else{
                    request.setAttribute("error","用户不存在");
                    request.getRequestDispatcher(request.getContextPath()+"/backend/login.jsp");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }


        //判断通过，撞到main.jsp页面
        response.sendRedirect(request.getContextPath()+"/backend/main.jsp");

    }

}
