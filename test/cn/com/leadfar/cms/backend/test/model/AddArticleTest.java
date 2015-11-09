package cn.com.leadfar.cms.backend.test.model;

import cn.com.leadfar.cms.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by tusizi on 2015/11/9.
 */
public class AddArticleTest {
    public  static  void  main(String arges[]){
        //将数据插入数据库
        Connection conn = DBUtil.getConn();
        PreparedStatement pstmt =null;
        String sql = "insert into t_article (title,content,source,createtime) values (?,?,?,?)";
        try {
            for (int i =0;i<100;i++){
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,"测试文章");
                pstmt.setString(2,"测试文章");
                pstmt.setString(3,"测试文章");
                pstmt.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
                pstmt.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtil.rollback(conn);
        }finally {
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }

    }

}

