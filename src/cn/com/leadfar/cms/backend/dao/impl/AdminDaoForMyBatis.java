package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.AdminDao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
/**
 * Created by tusizi on 2015/12/1.
 */
public class AdminDaoForMyBatis implements AdminDao {
    @Override
    public void addAdmin(Admin admin) {
        //打开一个Session
        SqlSession session = MyBatisUtil.getSession();
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
        Admin admin = null;

        //打开一个Session
        SqlSession session = MyBatisUtil.getSession();
        try {
            admin = (Admin)session.selectOne(Admin.class.getName()+".findAdminById",username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //关闭
            session.close();
        }
        return admin;
    }
}
