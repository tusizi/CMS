package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.CommentDao;
import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/10/20.
 */

public class CommentAdminServlet extends BaseServlet {
    private CommentDao commentDao;
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("pv",commentDao.findAllComments() );
        request.getRequestDispatcher("/backend/comment/comment_list.jsp").forward(request, response);
    }
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String[] ids = request.getParameterValues("id");
        if (ids ==null){
            request.setAttribute("error","请选择删除项");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request,response);
            return;
        }
        commentDao.delComments(ids);
        response.sendRedirect(request.getContextPath()+"/backend/CommentAdminServlet");
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
