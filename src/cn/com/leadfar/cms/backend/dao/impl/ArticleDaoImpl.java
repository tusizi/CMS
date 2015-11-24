package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusizi on 2015/11/13.
 */
public class ArticleDaoImpl implements ArticleDao {
    public void addArticle(Article a){
        //将数据插入数据库
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt =null;
        String sql = "insert into t_article (title,content,source,createtime) values (?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,a.getTitle());
            pstmt.setString(2,a.getContent());
            pstmt.setString(3,a.getSource());
            pstmt.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtil.rollback(conn);
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        }

    @Override
    public void delArticles(String[] ids) {
        //从数据库中删除文章
        Connection conn = DBUtil.getConn();
        String sql = "delete from t_article where id = ?";
        PreparedStatement pstmt = null;
        try {
            for(int i =0; i<ids.length; i++){
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(ids[i]));
                pstmt.executeUpdate();
            }
            //什么意思？？？？？？
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //为什么回滚，什么意思？？？？
            DBUtil.rollback(conn);
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
    }

    @Override
    public Article findArticleById(int id) {
        //根据id从数据库中查询出要更新的文章
        String sql = "select * from t_article where id = ?";//定义一个sql语句
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        ResultSet rs = null;//定义一个结果集
        Article article = null;
        try {
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();//预处理语句执行查询，将结果放入到结果集rs里
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
            if (rs.next()){
                article= new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setSource(rs.getString("source"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        return article;
    }

    @Override
    public PageVO findArticles(String title, int offset, int pagesize) {
        //查询文章列表
        List articles = new ArrayList();//定义一个文章列表
        String sql = "select * from t_article limit ?,?";//定义一个sql语句
        if(title != null){
            //模糊查询
            sql ="select * from t_article where title like '%"+title+"%' limit ?,?";
        }
        String sqlForTotal = "select count(*) from t_article";
        if(title!= null){
            sqlForTotal="select count(*) from t_article where title like '%"+title+"%'";
        }
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        PreparedStatement pstmtForTotal = null;
        ResultSet rsForTotal = null;
        ResultSet rs = null;//定义一个结果集
        int total = 0;
        try {
            //查询总记录数
            pstmtForTotal = conn.prepareStatement(sqlForTotal);
            rsForTotal = pstmtForTotal.executeQuery();
            while (rsForTotal.next()){
                total = rsForTotal.getInt(1);
            }
            //查询当前页的数据
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setInt(1,offset);
            pstmt.setInt(2,pagesize);
            rs = pstmt.executeQuery();//预处理语句执行查询，将结果放入到结果集rs里
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
            while(rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setSource(rs.getString("source"));
                article.setCreatetime(rs.getTimestamp("createtime"));
                article.setUpdatetime(rs.getTimestamp("updatetime"));
                article.setDeploytime(rs.getTimestamp("deploytime"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rsForTotal);
            DBUtil.close(pstmtForTotal);
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
        PageVO pv = new PageVO();
        pv.setDatas(articles);
        pv.setTotal(total);
        return pv;
    }

    @Override
    public void updateArticle(Article a) {
        //用类似下面的sql语句，对数据库的内容更新
        String sql = "update t_article set title=?, content=?, source = ?, updatetime = ? where id =?";//定义一个sql语句
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        try {
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setString(1,a.getTitle());
            pstmt.setString(2,a.getContent());
            pstmt.setString(3,a.getSource());
            pstmt.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(5, a.getId());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }
    }
}
