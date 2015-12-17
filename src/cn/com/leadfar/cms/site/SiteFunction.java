package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Article;

import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.view.InitBeanFactoryServlet;
import cn.com.leadfar.cms.utils.BeanFactory;

import javax.servlet.jsp.PageContext;

/**
 * Created by tusizi on 2015/12/17.
 */
public class SiteFunction {
    /**
     * 根据频道ID得到频道
     */
    public static Channel findChannelById(PageContext pc,String channelId){
        BeanFactory factory = (BeanFactory)pc.getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
        ChannelDao cd = (ChannelDao)factory.getBean("channelDao");
       Channel channel = (Channel) cd.findChannelById(Integer.parseInt(channelId));
        return channel;
    }
    public static Article findArticleById(PageContext pc,String articleId){
        BeanFactory factory = (BeanFactory)pc.getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
        ArticleDao articleDao = (ArticleDao)factory.getBean("articleDao");
        return articleDao.findArticleById(Integer.parseInt(articleId));
    }
}
