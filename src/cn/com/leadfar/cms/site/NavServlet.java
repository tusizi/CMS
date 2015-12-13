package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.view.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/12/10.
 */
public class NavServlet extends BaseServlet {
    private ArticleDao articleDao;
    private ChannelDao channelDao;
    //根据Id查询出频道下的文章
    public void channelList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String channelId = request.getParameter("channelId");
        Channel c = new Channel();
        c.setId(Integer.parseInt(channelId));
        request.setAttribute("c",articleDao.findArticles(c,20));
        request.getRequestDispatcher("/channel.jsp").forward(request,response);
    }
    //首页头条
    public void headLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询首页头条2篇文章
        request.setAttribute("headline", articleDao.findHeadline(2));
        request.getRequestDispatcher("/portlet/headline.jsp").include(request,response);
    }
    //查询某个频道下的文章列表,显示在首页
    public void indexChannelArticleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String channelId = request.getParameter("channelId");
        Channel c = new Channel();
        c.setId(Integer.parseInt(channelId));
        request.setAttribute("articles",articleDao.findArticles(c, 10));
        request.getRequestDispatcher("/portlet/index_channel_article_list.jsp").include(request, response);
    }
    //推荐阅读
    public void recommendArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询推荐阅读的最新10篇
        request.setAttribute("recommend", articleDao.findRecommend(10));
        request.getRequestDispatcher("/portlet/recommend.jsp").include(request,response);
    }
    //最新发表
    public void latestArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String channelId = request.getParameter("channelId");
        Channel c=null;
        if(channelId!= null){
           c=new Channel();
            c.setId(Integer.parseInt(channelId));
        }
        request.setAttribute("latest", articleDao.findArticles(c,10));
        request.getRequestDispatcher("/portlet/latest.jsp").include(request,response);
    }
    //导航
    public void navList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SystemContext.setOffset(0);
        SystemContext.setPagesize(Integer.MAX_VALUE);
        request.setAttribute("channels",channelDao.findChannels().getDatas());
        request.getRequestDispatcher("/portlet/channel_list.jsp").include(request,response);
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}
