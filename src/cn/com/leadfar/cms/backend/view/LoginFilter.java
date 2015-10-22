package cn.com.leadfar.cms.backend.view;

import com.sun.net.httpserver.HttpsServer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;

/**
 * Created by tusizi on 2015/10/22.
 */
public class LoginFilter implements javax.servlet.Filter{
        private String filterPattern;
    @Override
    public void init(FilterConfig Config) throws ServletException {
         filterPattern = Config.getInitParameter("filterPattern");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //判断HTTP SESSION中是否有LOGIN_ADMIN
        String loginAdmin = (String) request.getSession().getAttribute("LOGIN_ADMIN");
        String page = requestURI.substring(request.getContextPath().length());
        //字符串是否匹配filterPattern
        if (page.matches(filterPattern)){
            if (loginAdmin == null && !page.equals("/backend/login.jsp") && !page.equals("/backend/loginServlet")  ){
                //重定向到login.jsp页面
                response.sendRedirect(request.getContextPath()+"/backend/login.jsp");
            }
        }
        //继续向下执行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
