package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.awt.datatransfer.StringSelection;
import java.util.*;

/**
 * Created by tusizi on 2015/12/2.
 */
public class ArticleDaoForMyBatis extends BaseDao implements ArticleDao {
    @Override
    public void addArticle(Article a) {
        a.setCreatetime(new Date());
        SqlSession session = MyBatisUtil.getSession();
        try {
            //插入
            session.insert(Article.class.getName() + ".add", a);
            //考虑channels
            Set<Channel> channels = a.getChannels();
            if (channels != null) {
                for (Channel c : channels) {
                    Map param = new HashMap();
                    param.put("a", a);
                    param.put("c", c);
                    session.insert(Article.class.getName() + ".insert_channel_article", param);
                }
                //插入文章和关键字的关联
               if(a.getKeyword()!=null&& !a.getKeyword().trim().equals("")){
                   String[] keywords = a.getKeyword().split(",| ");
                   for (String kw:keywords){
                       Map param =new HashMap();
                       param.put("kw",kw);
                       param.put("a",a);
                       session.insert(Article.class.getName()+".insert_article_keyword",param);
                   }
               }
                //提交
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            //关闭
            session.close();
        }
    }

    @Override
    public void delArticles(String[] ids) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            for (String id : ids) {
                session.delete(Article.class.getName() + ".delArticles", Integer.parseInt(id));
                session.delete(Article.class.getName() + ".delChannel_Articles", Integer.parseInt(id));
                session.delete(Article.class.getName() + ".delKeyword_Article", Integer.parseInt(id));
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            //关闭
            session.close();
        }

    }

    @Override
    public Article findArticleById(int id) {
        Article article = (Article) findById(Article.class, id);
        SqlSession session= MyBatisUtil.getSession();
        List channels=session.selectList(Article.class.getName() + ".findChannelsById", id);
        article.setChannels(new HashSet<Channel>(channels));
        return article;
    }

    @Override
    public PageVO findArticles(String title) {
        Map params = new HashMap();
        if(title != null)
            params.put("title", "%" + title + "%");
        return findPaginated(Article.class.getName() + ".findArticlesByTitle",params);
    }

    @Override
    public List findHeadline(int max) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            return session.selectList(Article.class.getName()+".findHeadline",max);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            //关闭
            session.close();
        }
        return null;
    }



    @Override
    public List findRecommend(int max) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            return session.selectList(Article.class.getName()+".findRecommend",max);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            //关闭
            session.close();
        }
        return null;
    }


    @Override
    public List findArticles(Channel channel, int max ) {
        Map params = new HashMap();
        params.put("c", channel);
        SystemContext.setPagesize(max);
        SystemContext.setOffset(0);
       PageVO pageVO= findPaginated(Article.class.getName() + ".findArticlesByChannel",params);
        return pageVO.getDatas();
    }
    @Override
    public PageVO findArticles(Channel channel) {
        Map params = new HashMap();
        params.put("c", channel);
        PageVO pageVO= findPaginated(Article.class.getName() + ".findArticlesByChannel",params);
        return pageVO;
    }

    @Override
    public void updateArticle(Article a) {
        a.setUpdatetime(new Date());
        SqlSession session = MyBatisUtil.getSession();
        try {
            //Article.class.getName()得到class的全路径类名
            session.update(Article.class.getName() + ".updateArticle", a);
            session.delete(Article.class.getName() + ".delChannel_Articles", a.getId());
            Set<Channel> channels = a.getChannels();
            if (channels != null) {
                for (Channel c : channels) {
                    Map param = new HashMap();
                    param.put("a", a);
                    param.put("c", c);
                    session.insert(Article.class.getName() + ".insert_channel_article", param);
                }
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            //关闭
            session.close();
        }
    }

    //根据关键字查询相关文章
    /**
     * 查询相关文章的时候，首先根据文章的关键字，查出所有相关文章的ID，然后根据ID列表查出文章的标题来
     * @param keyword
     * @return
     */
    @Override
    public PageVO findArticlesByKeyword(String keyword) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            if (keyword == null || keyword.trim().equals("")){
                return null;
            }
            String[] keywords = keyword.split(",| ");//按照空格或者逗号隔开
            //先找出相关文章的id列表
            if (keywords!=null&&keywords.length>0){
                StringBuffer sb = new StringBuffer();
                for (int i =0;i<keywords.length;i++){
                    if (i!=0){
                        sb.append(",");
                    }
                    sb.append("'"+keywords[i]+"'");
                }
                Map params = new HashMap();
                params.put("keywords",sb.toString());
                List articleId = session.selectList(Article.class.getName()+".findArticleIdByKeyword",params);

                StringBuffer ids= new StringBuffer();
                for (int i=0;i<articleId.size();i++){
                    if (i!=0){
                        ids.append(",");
                    }
                    ids.append("'"+articleId.get(i)+"'");
                }
                params = new HashMap();
                params.put("ids",ids.toString());
                params.put("offset",0);
                params.put("pagesize",Integer.MAX_VALUE);
                List articles = session.selectList(Article.class.getName()+".findArticlesByIds",params);
                PageVO pageVO=new PageVO();
                pageVO.setDatas(articles);
                pageVO.setTotal(articleId.size());
                return pageVO;
            }
            }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            session.close();
        }
        return null;
    }
    //更新文章点击量
    @Override
    public int updateClickNumber(int articleId) {
        SqlSession session= MyBatisUtil.getSession();
        int clickNumber = 0;
        try{
            clickNumber = (Integer)session.selectOne(Article.class.getName()+".selectClickNumber",articleId);
            clickNumber=clickNumber+1;
            Article a = new Article();
            a.setId(articleId);
            a.setClickNumber(clickNumber);
            session.update(Article.class.getName()+".updateClickNumber",a);
            session.commit();
    }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return clickNumber;
    }
}
