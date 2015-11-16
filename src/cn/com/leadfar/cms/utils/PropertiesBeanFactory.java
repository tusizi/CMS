package cn.com.leadfar.cms.utils;
import java.io.IOException;
import java.util.*;

/**
 * Created by tusizi on 2015/11/16.
 */
public class PropertiesBeanFactory implements BeanFactory {
    Map beans = new HashMap();
    public PropertiesBeanFactory(){
        try {
            Properties props = new Properties();
            //读取配置文件，得到具体DAO的实现类名
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("beans.properties"));
            //根据配置文件，初始化所有的DAO对象
             Set set = props.entrySet();
           for (Iterator iterator=set.iterator();iterator.hasNext();){
               Map.Entry entry= (Map.Entry) iterator.next();
               String key = (String)entry.getKey();//DAO的名称
               String className = (String) entry.getValue();//全路径类名
               Class  clz = Class.forName(className);
               Object bean = clz.newInstance();//预先创建好的DAO对象
               beans.put(key,bean);

           }
        } catch (Exception e) {
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
    public Object getBean(String name) {

        return beans.get(name);
    }
}
