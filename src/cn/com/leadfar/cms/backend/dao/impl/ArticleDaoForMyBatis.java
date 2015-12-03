package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

/**
 * Created by tusizi on 2015/12/2.
 */
public class ArticleDaoForMyBatis implements ArticleDao {
    @Override
    public void addArticle(Article a) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            //插入
            session.insert(Article.class.getName()+".add",a);
            //考虑channels
            Set<Channel> channels=a.getChannels();
            if (channels !=null){
                for (Channel c:channels){
                    Map param = new  HashMap();
                    param.put("a",a);
                    param.put("c",c);
                    session.insert(Article.class.getName()+".insert_channel_article",param);
                }
            //提交
            session.commit();
            }
        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            //关闭
            session.close();
        }
    }

    @Override
    public void delArticles(String[] ids) {
        SqlSession session = MyBatisUtil.getSession();
        try{
            for (String id:ids){
            session.delete(Article.class.getName()+".delArticles",Integer.parseInt(id));
            session.delete(Article.class.getName()+".delChannel_Articles",Integer.parseInt(id));
        }
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            //关闭
            session.close();
        }

        }

    @Override
    public Article findArticleById(int id) {
        SqlSession session = MyBatisUtil.getSession();
        Article article = null;
        try{
            article = (Article)session.selectOne(Article.class.getName()+".findArticleById",id);
            }catch (Exception e) {
            e.printStackTrace();
        } finally{
            //关闭
            session.close();
        }
        return article;
        }



    @Override
    public PageVO findArticles(String title, int offset, int pagesize) {
        SqlSession session = MyBatisUtil.getSession();
        int total =0;
        List datas = null;
        try{
            Map map =new  HashMap();
            map.put("title","%"+title+"%");
            map.put("offset",offset);
            map.put("pagesize",pagesize);
            //查询当前页面的文章数
            datas = session.selectList(Article.class.getName()+".findArticlesByTitle",map);
            //查询总记录数
            total = (Integer)session.selectOne(Article.class.getName()+".findArticlesByTitle-count",map);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            //关闭
            session.close();
        }
        PageVO pageVO =new PageVO();
        pageVO.setDatas(datas);
        pageVO.setTotal(total);
        return pageVO;
    }

    @Override
    public PageVO findArticles(Channel channel, int offset, int pagesize) {
        SqlSession session = MyBatisUtil.getSession();
        int total =0;
        List datas = null;
        try{
            Map map =new  HashMap();
            map.put("c",channel);
            map.put("offset",offset);
            map.put("pagesize",pagesize);
            //查询当前页面的文章数
            datas = session.selectList(Article.class.getName()+".findArticlesByChannel",map);
            //查询总记录数
            total = (Integer)session.selectOne(Article.class.getName()+".findArticlesByChannel-count",map);
            session.commit();
        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            //关闭
            session.close();
        }
        PageVO pageVO =new PageVO();
        pageVO.setDatas(datas);
        pageVO.setTotal(total);
        return pageVO;
    }

    @Override
    public void updateArticle(Article a) {
        SqlSession session = MyBatisUtil.getSession();
        try{
            //Article.class.getName()得到class的全路径类名
            session.update(Article.class.getName()+".updateArticle",a);
            session.delete(Article.class.getName()+".delChannel_Articles",a.getId());
            Set<Channel> channels=a.getChannels();
            if (channels !=null){
                for (Channel c:channels){
                    Map param = new  HashMap();
                    param.put("a",a);
                    param.put("c",c);
                    session.insert(Article.class.getName()+".insert_channel_article",param);
                }
            session.commit();
            }
        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            //关闭
            session.close();
        }
    }
}
