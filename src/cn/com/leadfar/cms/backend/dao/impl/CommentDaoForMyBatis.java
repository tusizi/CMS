package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.CommentDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Comment;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by tusizi on 2015/12/2.
 */
public class CommentDaoForMyBatis extends BaseDao implements CommentDao {

    @Override
    public void addComment(Comment c) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            c.setCommentTime(new Date());
            session.insert(Comment.class.getName()+"add",c);
            //每增加一条评论，leaveNumber就加一
            int leaveNumber =(Integer) session.selectOne(Article.class.getName()+".selectLeaveNumber",c.getArticleId());
            Article a = new Article();
            a.setId(c.getArticleId());
            a.setLeaveNumber(leaveNumber + 1);
            session.update(Article.class.getName()+".updateLeaveNumber",a);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void updateComment(Comment c) {

    }

    @Override
    public void delComments(String[] ids) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            for (String id:ids){
                Comment c = (Comment) session.selectOne(Comment.class.getName() + ".findById", id);
                int leaveNumber =(Integer) session.selectOne(Article.class.getName()+".selectLeaveNumber",c.getArticleId());
                Article a=  new Article();
                a.setId(c.getArticleId());
                a.setLeaveNumber(leaveNumber-1);
                session.update(Article.class.getName()+".updateLeaveNumber",a);
                session.delete(Comment.class.getName() + ".del", id);
            }
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public Comment findCommentById(int id) {
        return null;
    }

    @Override
    public List findCommentsByArticleId(int articleId) {
        SqlSession session = MyBatisUtil.getSession();
        try{
           return session.selectList(Comment.class.getName() + ".findByArticleId", articleId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public PageVO findAllComments() {
        Map params = new HashMap();
        return findPaginated(Comment.class.getName()+".findAllComments",params);
    }
}
