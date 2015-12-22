package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.backend.dao.CommentDao;
import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.view.BaseServlet;
import cn.com.leadfar.cms.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by tusizi on 2015/12/17.
 */
public class CommentServlet extends BaseServlet {
    private CommentDao commentDao;
    public void comments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        List commets = commentDao.findCommentsByArticleId(Integer.parseInt(articleId));
        request.setAttribute("comments",commets);
        request.getRequestDispatcher("/portlet/comment_list.jsp").include(request,response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Comment comment = (Comment) RequestUtil.copyParam(Comment.class,request);
        commentDao.addComment(comment);
        String ref = request.getHeader("referer");
        response.sendRedirect(ref);
    }
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
