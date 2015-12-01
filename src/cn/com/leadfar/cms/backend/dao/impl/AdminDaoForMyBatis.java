package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by tusizi on 2015/12/1.
 */
public class AdminDaoForMyBatis implements AdminDao {
    @Override
    public void addAdmin(Admin admin) {
        //创建SqlSession的工厂
        SqlSessionFactory factory = null;
        try {
            //通过配置文件，创建工厂对象
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //打开一个Session
        SqlSession session = factory.openSession();

        try {
            //插入
            session.insert(Admin.class.getName()+".add", admin);

            //提交
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally{
            //关闭
            session.close();
        }
    }

    @Override
    public Admin findAdminByUsername(String username) {
        return null;
    }
}
