package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.vo.PageVO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by tusizi on 2015/10/20.
 */

public class MemberAdminServlet extends BaseServlet {
    private MemberDao memberDao;
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("pv", memberDao.findAllMembers());
        request.getRequestDispatcher("/backend/member/member_list.jsp").forward(request,response);
    }
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String[] ids = request.getParameterValues("id");
        if (ids ==null){
            request.setAttribute("error","请选择删除项");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
            return;
        }
        memberDao.delMembers(ids);
        response.sendRedirect(request.getContextPath()+"/backend/MemberAdminServlet");
    }
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
