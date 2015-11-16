package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.ChannelDao;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tusizi on 2015/11/16.
 */
public class PropertiesBeanFactory implements BeanFactory {
    Properties props = new Properties();
    public PropertiesBeanFactory(){
        try {
            //读取配置文件，得到具体DAO的实现类名
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("beans.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public ArticleDao getArticleDao() {
//        String className = props.getProperty("articleDao");
//
//        Class clz = null;
//        try {
//            //创建一个className的类
//            clz = Class.forName(className);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            //将这个clz类实例化
//            return (ArticleDao)clz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public ChannelDao getChannelDao() {
//       String className = props.getProperty("channelDao");
//        Class clz = null;
//        try {
//            clz = Class.forName(className);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            return (ChannelDao)clz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public Object getBean(String name) {String className = props.getProperty("articleDao");

        Class clz = null;
        try {
            //创建一个className的类
            clz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //将这个clz类实例化
            return (ArticleDao)clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
