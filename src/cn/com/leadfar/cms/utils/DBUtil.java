package cn.com.leadfar.cms.utils;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.*;

/**
 * Created by tusizi on 2015/10/20.
 */
public class DBUtil {
    public static Connection getConn() {
        JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:~/cms", "sa", null);

        Connection conn = null;
        try {
            conn=  cp.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement pstmt) {
        try {
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

