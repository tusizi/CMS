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
    /**
     * 根据attachmentId删除附件
     * @param attachmentId
     */
    public void delAttachmentById(int attachmentId);
    public Article findArticleById(int id);
    public PageVO findArticles(String title);
    public PageVO findArticles(Channel channel);
    public PageVO findArticlesByKeyword(String keyword);
    //查询最新文章
    public List findArticles(Channel channel, int max);
    public List findHeadline(int max);
    public List findRecommend(int max);
    public void updateArticle(Article a);
    public int updateClickNumber(int articleId);
}
