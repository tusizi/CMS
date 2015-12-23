package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.view.BaseServlet;
import cn.com.leadfar.cms.backend.vo.PageVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    //查询某个频道下所有文章，并分页显示
    public void channelIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String channelId = request.getParameter("channelId");
        Channel c= null;
        if (channelId!=null){
           c=(Channel)channelDao.findChannelById(Integer.parseInt(channelId));
            request.setAttribute("channel",c);
        }
        //查询最新发表的文章
        request.setAttribute("pageVO",articleDao.findArticles(c));
        request.getRequestDispatcher("/portlet/channel_index.jsp").include(request, response);
    }
    //根据id查询文章
    public void articleDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        Article a= new Article();
        request.setAttribute("article",a = articleDao.findArticleById(Integer.parseInt(articleId)));
        //????System.out.println(a.getChannels() );
        request.getRequestDispatcher("/portlet/article_detail.jsp").include(request, response);
    }
    //根据keywords查询相关文章
    public void keywords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String articleId = request.getParameter("articleId");
        Article a = articleDao.findArticleById(Integer.parseInt(articleId));
        PageVO pageVO=articleDao.findArticlesByKeyword(a.getKeyword());
        request.setAttribute("keyArticle",pageVO.getDatas());
        request.getRequestDispatcher("/portlet/keyword.jsp").include(request, response);
    }
    //文章点击量计算
    public void click(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        //首先从servletContext中取出一个map
        Map visitors= (Map) getServletContext().getAttribute("visitors");
        if (visitors==null){
            visitors = new ConcurrentHashMap();//啥意思？
            getServletContext().setAttribute("visitors",visitors);
        }
        //得到客户端的IP地址
        String clickIp = request.getRemoteAddr();
        String key = articleId+"_"+clickIp;//以文章ID和客户端IP作为key
        Date lastVisitTime = (Date) visitors.get(key);
        Article a = articleDao.findArticleById(articleId);
        int clickNumber = a.getClickNumber();//旧的点击量
        if (lastVisitTime==null||!withinOneHour(lastVisitTime)){
             clickNumber = articleDao.updateClickNumber(articleId);
            visitors.put(key,new Date());
        }
        //表示返回去的是一段javascript代码
        response.setContentType("text/javascript");
        //返回的内容是....相当于引入了一个js文件
        response.getWriter().println("document.write('"+clickNumber+"')");
    }
    /**
     * 没有访问记录，或者在一个小时之内没有访问需要再次记录访问量
     * 否则无需再次记录访问量
     * 是否是在一个小时内
     */
    public boolean withinOneHour(Date lastVisitTime){
        //获得现在的时间
        Calendar now = Calendar.getInstance();
        //获得上次访问的时间
        Calendar last = Calendar.getInstance();
        last.setTime(lastVisitTime);
        last.add(Calendar.HOUR_OF_DAY,1);
        if (last.before(now)){
            return false;
        }
        return true;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}