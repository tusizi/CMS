package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by tusizi on 2015/11/13.
 */
public class ArticleDaoForOracleImpl extends ArticleDaoImpl {
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


}

