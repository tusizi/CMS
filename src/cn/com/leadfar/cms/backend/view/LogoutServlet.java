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
@WebServlet(name = "LayoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清空http session 销毁session  结束回话
        request.getSession().invalidate();
        //返回login.jsp
        response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
    }
}
