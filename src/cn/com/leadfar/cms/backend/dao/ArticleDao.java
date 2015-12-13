package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;

import java.util.List;

/**
 * Created by tusizi on 2015/11/13.
 */
public interface ArticleDao {
    public void addArticle(Article a);
    public void delArticles(String[] ids);
    public Article findArticleById(int id);
    public PageVO findArticles(String title);
    //查询最新文章
    public List findArticles(Channel channel, int max);
    public List findHeadline(int max);
    public List findRecommend(int max);
    public void updateArticle(Article a);
}
