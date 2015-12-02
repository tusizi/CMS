package cn.com.leadfar.cms.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by tusizi on 2015/12/2.
 */
public class MyBatisUtil {
    //创建SqlSession的工厂
    static SqlSessionFactory factory = null;
   static{
        try {
            //通过配置文件，创建工厂对象
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public static SqlSession getSession(){
        return factory.openSession();
    }
}
