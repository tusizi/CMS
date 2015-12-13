package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tusizi on 2015/11/18.
 */
public class ArticleServlet extends BaseServlet {
    private ArticleDao articleDao;
    private ChannelDao channelDao;

    //在这个方法中执行查询工作
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从界面中获取title参数
        String title = request.getParameter("title");
        PageVO pv = articleDao.findArticles(title);
        request.setAttribute("pv", pv);
        System.out.println(pv);
        request.getRequestDispatcher("/backend/article/article_list.jsp").forward(request, response);
    }

    //添加文章
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("add");
        Article a = (Article)RequestUtil.copyParam(Article.class, request);
        System.out.println("addArticle");
        articleDao.addArticle(a);
        request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request, response);
    }

    //用来打开添加文章的界面
    public void addInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemContext.setOffset(0);
        SystemContext.setPagesize(Integer.MAX_VALUE);
        PageVO pageVO = channelDao.findChannels();
        request.setAttribute("channelIds", pageVO.getDatas());
        request.getRequestDispatcher("/backend/article/add_article.jsp").forward(request, response);
    }

    //删除文章
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从界面获取删除文章的id
        // String id = request.getParameter("id");
        //从界面获得一组id 值
        String[] ids = request.getParameterValues("id");
        if (ids == null) {
            //提示错误 forward 到错误页面
            request.setAttribute("error", "无法删除文章，ID不允许为空");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
        }
        articleDao.delArticles(ids);
        //转向列表页面
        //request.getRequestDispatcher("/backend/SearchArticlesServlet").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/backend/ArticleServlet");
    }

    //打开更新界面
    public void openUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受从界面传过来的id
        String id = request.getParameter("id");
        Article article = articleDao.findArticleById(Integer.parseInt(id));
        request.setAttribute("article", article);
        SystemContext.setOffset(0);
        SystemContext.setPagesize(Integer.MAX_VALUE);
        PageVO pageVO = channelDao.findChannels();
        request.setAttribute("channelIds", pageVO.getDatas());
        //farword到更新界面
        request.getRequestDispatcher("/backend/article/update_article.jsp").forward(request, response);
    }

    //更新文章
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Article a  = (Article) RequestUtil.copyParam(Article.class,request);
        articleDao.updateArticle(a);
        //farword 到更新成功的界面
        request.getRequestDispatcher("/backend/article/update_article_success.jsp").forward(request, response);
    }

    //本set方法，定义了一个articleDao这样的一个property
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}


