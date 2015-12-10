package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Article;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;
import cn.com.leadfar.cms.utils.BeanFactory;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;
import junit.framework.TestCase;

import java.util.*;

/**
 * Created by tusizi on 2015/12/1.
 */
public class AticleDaoTest extends TestCase {
    public void testAddArticle(){
        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        Article a = new Article();
        Random r =new Random();
        a.setTitle("测试文章"+r.nextInt(99999));
        a.setContent("测试内容" + r.nextInt(99999));
        a.setHeadline(true);
        a.setCreatetime(new Date());
        articleDao.addArticle(a);
    }
//    将文章放在某个频道里，测试文章和频道表即t_channel_article表
    public void testAddArticle01(){
        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        Article a = new Article();
        Random r =new Random();
        a.setTitle("测试文章"+r.nextInt(9999));
        a.setContent("测试内容" + r.nextInt(9999));
        a.setHeadline(true);
        //设置文章属于哪些频道
        //先建立一个channel Set
        Set channels = new HashSet();
        Channel c = new Channel();
        //将ID放入c中
        c.setId(1);
        //将c放入channels中
        channels.add(c);
        //将channels放入a中
        a.setChannels(channels);
        articleDao.addArticle(a);
    }
    //    将文章放在某些频道里，测试文章和频道表即t_channel_article表
    public void testAddArticle02(){
        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        Article a = new Article();
        Random r =new Random();
        a.setTitle("测试文章"+r.nextInt(9999));
        a.setContent("测试内容" + r.nextInt(9999));
        a.setHeadline(true);
        //设置文章属于哪些频道
        //先建立一个channel Set
        Set channels = new HashSet();
        Channel c = new Channel();
        Channel c2 = new Channel();
        //将ID放入c中
        c.setId(1);
        c2.setId(7);
        //将c放入channels中
        channels.add(c);
        channels.add(c2);
        //将channels放入a中
        a.setChannels(channels);
        articleDao.addArticle(a);
    }
    public void testDelArticle(){
        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        articleDao.delArticles( new String[]{"1"});
    }
    public void testFindArticleById(){
        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        Article article = articleDao.findArticleById(4);
        System.out.println(article.getId());

    }
    public void testFindArticlesByTitle(){
        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        PageVO pageVO = articleDao.findArticles("1");
        System.out.println("总记录数:"+pageVO.getTotal());
        List datas = pageVO.getDatas();
        for(Iterator iterator = datas.iterator();iterator.hasNext();){
            Article a = (Article) iterator.next();
            System.out.println(a.getId()+a.getTitle());
        }
    }
//    public void testFindArticlesByChnnel(){
//        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
//        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
//        Channel c = new Channel();
//        c.setId(1);
//        PageVO pageVO = articleDao.findArticles(c);
//        System.out.println("总记录数:"+pageVO.getTotal());
//        List datas = pageVO.getDatas();
//        for(Iterator iterator = datas.iterator();iterator.hasNext();){
//            Article a = (Article) iterator.next();
//            System.out.println(a.getId()+a.getTitle());
////        }
//    }
//
//    public void testUpdateArticle(){
//        BeanFactory factory = new PropertiesBeanFactory("beans.properties");
//        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
//        Article a =new Article();
//        a.setId(1);
//        a.setTitle("测试更新文章");
//        a.setContent("测试更新文章内容");
//        articleDao.updateArticle(a);
//    }
}
