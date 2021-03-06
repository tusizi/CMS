package cn.com.leadfar.cms.backend.dao;
import cn.com.leadfar.cms.backend.model.Admin;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;
import junit.framework.TestCase;

import java.util.Random;

/**
 * Created by tusizi on 2015/12/1.
 */
public class AdminDaoTest extends TestCase {
    static PropertiesBeanFactory factory = new PropertiesBeanFactory("beans.properties");
    public void testAddAdmin(){
        AdminDao adminDao = (AdminDao) factory.getBean("adminDao");
        Admin admin = new Admin();
        Random r = new Random();
        admin.setUsername("测试用户"+r.nextInt());
        admin.setPassword("测试密码"+r.nextInt());
        adminDao.addAdmin(admin);
    }

    public void testFindAdminByUsername(String username){
        AdminDao adminDao = (AdminDao) factory.getBean("adminDao");
        Admin a = adminDao.findAdminByUsername("admin");
        System.out.println(a.getUsername()+","+a.getPassword());
    }
}
