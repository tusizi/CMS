package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/10/20.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends BaseServlet {
    private AdminDao adminDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        //系统判断验证码是否正确
        //刚刚生成的验证码串
        String sessionCode = (String) request.getSession().getAttribute("codes");//得到的不是string类型吗
        if (!sessionCode.equalsIgnoreCase(checkcode)) {
            //网页重定向forward到login.jsp页面
            //请求转发
            request.setAttribute("error", "验证码错误");
            request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
            return;
        }

        Admin admin = adminDao.findAdminByUsername(username);

        //系统判断用户名是否存在
        if (admin != null) {
            String pass = admin.getPassword();
            //密码是否正确
            if (!password.equals(pass)) {
                request.setAttribute("error", "密码不正确");
                request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("error", "用户不存在");
            request.getRequestDispatcher("/backend/login.jsp").forward(request, response);
            return;
        }
        //需要将用户的信息存放到http session中
        request.getSession().setAttribute("LOGIN_ADMIN", username);

        //判断通过，转到main.jsp页面
        //重定向
        response.sendRedirect(request.getContextPath() + "/backend/main.jsp");
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }


}
