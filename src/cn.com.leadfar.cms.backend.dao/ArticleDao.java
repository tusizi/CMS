package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.vo.PageVO;

/**
 * Created by tusizi on 2015/11/13.
 */
public interface ArticleDao {
    public void addArticle(Article a);
    public void delArticles(String[] ids);
    public Article findArticleById(int id);
    public PageVO findArticles(String title,int offset,int pagesize);
}
