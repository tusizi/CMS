package cn.com.leadfar.cms.backend.test.model;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusizi on 2015/11/9.
 */
public class FindPaginatedArticleTest {
    public static void main (String args[]){
        //要从第几条记录开始查询
        int offset =0;
        //每页显示的条数
        int pagesize = 5;
        String sql = "select * from t_article limit ? , ?";//定义一个sql语句
        Connection conn = DBUtil.getConn();//连接数据库
        PreparedStatement pstmt = null;//预处理语句
        ResultSet rs = null;//定义一个结果集
        try {
            pstmt = conn.prepareStatement(sql);//将SQL放入到预处理语句里
            pstmt.setInt(1, offset);
            pstmt.setInt(2, pagesize);
            rs = pstmt.executeQuery();//预处理语句执行查询，将结果放入到结果集rs里
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH-MM-SS");
            while(rs.next()){;
                int id = rs.getInt("id");
                String  title =rs.getString("title");
                String content = rs.getString("content");
                System.out.println(id+","+title+","+content);
//                article.setSource(rs.getString("source"));
//                article.setCreatetime(rs.getTimestamp("createtime"));
//                article.setUpdatetime(rs.getTimestamp("updatetime"));
//                article.setDeploytime(rs.getTimestamp("deploytime"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(pstmt);
            DBUtil.close(conn);
        }

    }
}
