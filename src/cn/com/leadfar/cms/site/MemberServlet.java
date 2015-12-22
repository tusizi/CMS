package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.view.BaseServlet;
import cn.com.leadfar.cms.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/12/18.
 */
public class MemberServlet extends BaseServlet {
    private MemberDao memberDao;
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int memberId = Integer.parseInt(request.getParameter("loginMemberId"));
        Member member = (Member) memberDao.findMemberById(memberId);
        if (member==null){
            request.setAttribute("error","您还没有登录，请先登录");
            request.getRequestDispatcher("/backend/commom/error.jsp");
        }
        request.setAttribute("member",member);
        request.getRequestDispatcher("/member/index.jsp").forward(request,response);
    }
    public void regInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   //为什么不直接跳转
       request.getRequestDispatcher("/member/reg_input.jsp").forward(request,response);
    }
    public void reg (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Member member = (Member) RequestUtil.copyParam(Member.class,request);
        try {
            memberDao.addMember(member);
        }catch (Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/member/reg_Input.jsp").forward(request,response);
        }
        request.setAttribute("success","注册成功");
        request.getRequestDispatcher("/common/success.jsp").forward(request,response);
    }
    public void updateInput (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Member member = loginMember(request);
        if (member ==null){
            request.setAttribute("error","您还没有登录，请先登录");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
            return;
        }
        member= (Member) memberDao.findMemberById(member.getId());
        request.setAttribute("member",member);
        request.getRequestDispatcher("/member/update_input.jsp").forward(request,response);
    }
    public void update (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Member member = loginMember(request);
        if (member ==null){
            request.setAttribute("error","您还没有登录，请先登录");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
            return;
        }
        Member member = (Member) RequestUtil.copyParam(Member.class,request);
        memberDao.updateMember(member);
        request.setAttribute("success","更新成功");
        //¸üÐÂÒ»ÏÂsessionÖÐµÄmember¶ÔÏó£¬ÒÔ±ãÔÚÒ³ÃæÉÏÄÜ¹»ÂíÉÏ¿´µ½µ±Ç°µÇÂ¼ÓÃ»§ÏÔÊ¾ÐÕÃûµÄ¸Ä±ä
        member.setName(member.getName());
        request.getRequestDispatcher("/common/success.jsp").forward(request,response);
    }
    public void updatePasswordInput (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Member member = loginMember(request);
        if (member ==null){
            request.setAttribute("error","您还没有登录，请先登录");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
            return;
        }
        member= (Member) memberDao.findMemberById(member.getId());
        request.setAttribute("member",member);
        request.getRequestDispatcher("/member/update_password_input.jsp").forward(request,response);
    }
    public void updatePassword (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Member member = loginMember(request);
        if (member ==null){
            request.setAttribute("error","您还没有登录，请先登录");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
            return;
        }
        String id = request.getParameter("id");
        String oldPassword = request.getParameter("oldPassword");
        String newPasswor = request.getParameter("newPassword");
        try {
            memberDao.updateMemberPassword(Integer.parseInt(id), oldPassword, newPasswor);
        }catch (Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("member",member);
        request.setAttribute("success","更新成功");
        request.getRequestDispatcher("/common/success.jsp").forward(request,response);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
