package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.backend.view.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/12/18.
 */
public class MemberServlet extends BaseServlet {
    public void regInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   //为什么不直接跳转
       request.getRequestDispatcher("/member/reg_input.jsp").forward(request,response);
    }
    public
}
