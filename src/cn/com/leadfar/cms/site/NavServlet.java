package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.view.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/12/10.
 */
@WebServlet(name = "NavServlet")
public class NavServlet extends BaseServlet {
    private ArticleDao articleDao;
    private ChannelDao channelDao;
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有频道列表
        SystemContext.setOffset(0);
        SystemContext.setPagesize(Integer.MAX_VALUE);
        request.setAttribute("channels", channelDao.findChannels().getDatas());
        //查询首页头条2篇文章
        request.setAttribute("headline", articleDao.findHeadline(2));
        //查询推荐阅读的最新10篇
        request.setAttribute("recommend", articleDao.findRecommend(10));
        //查询最新发表的10篇
        SystemContext.setOffset(0);
        SystemContext.setPagesize(10);
        request.setAttribute("latest",articleDao.findArticles(null).getDatas());
        //查询频道id为1的10篇文章
        Channel c = new Channel();
        c.setId(1);
        request.setAttribute("c1",articleDao.findArticles(c,10));
        //查询频道id为2的10篇文章
        c = new Channel();
        c.setId(2);
        request.setAttribute("c2",articleDao.findArticles(c,10));
        //查询频道id为3的10篇文章
        c = new Channel();
        c.setId(3);
        request.setAttribute("c3",articleDao.findArticles(c,10));
        //查询频道id为4的10篇文章
        c = new Channel();
        c.setId(4);
        request.setAttribute("c4",articleDao.findArticles(c,10));
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
    protected void channelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据Id查询出频道下的文章
        String channelId = request.getParameter("channelId");
        //查询所有频道列表
        SystemContext.setOffset(0);
        SystemContext.setPagesize(Integer.MAX_VALUE);
        request.setAttribute("channels", channelDao.findChannels().getDatas());
        Channel c = new Channel();
        c.setId(Integer.parseInt(channelId));
        request.setAttribute("c",articleDao.findArticles(c,20));
        request.getRequestDispatcher("/channel.jsp").forward(request,response);
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}
