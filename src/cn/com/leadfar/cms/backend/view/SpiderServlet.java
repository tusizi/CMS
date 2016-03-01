package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.service.impl.SpiderServiceImpl;
import cn.com.leadfar.cms.backend.vo.PageVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tusizi on 2016/3/1.
 */
public class SpiderServlet extends BaseServlet {
    private ArticleDao articleDao;
    private ChannelDao channelDao;
    //用来打开文章收集页面
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询频道列表，传到页面显示
        SystemContext.setOffset(0);
        SystemContext.setPagesize(Integer.MAX_VALUE);
        PageVO pageVO = channelDao.findChannels();
        request.setAttribute("channelIds", pageVO.getDatas());
        request.getRequestDispatcher("/backend/spider/index.jsp").forward(request,response);
    }
    //进行文章收集
    protected void collect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收URL地址和频道ID列表
        String url = request.getParameter("url");
        String[] channelIds = (String[])request.getParameterMap().get("channelIds");
        SpiderServiceImpl ssi = new SpiderServiceImpl();
        ssi.setArticleDao(articleDao);
        List<Article> articles = ssi.collect(url, channelIds);
        request.setAttribute("articles",articles);
        request.getRequestDispatcher("/backend/spider/result.jsp").forward(request,response);
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}
