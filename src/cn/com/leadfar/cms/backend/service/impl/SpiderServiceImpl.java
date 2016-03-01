package cn.com.leadfar.cms.backend.service.impl;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.service.Spider;
import cn.com.leadfar.cms.backend.service.SpiderService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tusizi on 2016/3/1.
 */
public class SpiderServiceImpl implements SpiderService {
    private ArticleDao articleDao;
    @Override
    public List<Article> collect(String url, String[] channelIds) {
        Spider spider = Spider.getInstance(url);
         List<Article> articles = spider.collect(url);
        if (articles!=null){
            Set channels = new HashSet();
            //根据频道ID列表，创建频道对象，并设置到article对象中
            if (channelIds!=null){
                for ( String channelId:channelIds ){
                    Channel c = new Channel();
                    c.setId(Integer.parseInt(channelId));
                    channels.add(c);
                }
            }
            for (Article a:articles){
                a.setChannels(channels);
                a.setCreatetime(new Date());
                a.setType("转载");
                articleDao.addArticle(a);
            }
        }
        return articles;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
