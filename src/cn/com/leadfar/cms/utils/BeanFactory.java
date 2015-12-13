package cn.com.leadfar.cms.utils;



/**
 * Created by tusizi on 2015/11/13.
 */
public interface BeanFactory {
//    public  ArticleDao getArticleDao();
//    public ChannelDao getChannelDao();
    public Object getBean(String name);
}
