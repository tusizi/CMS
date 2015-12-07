package cn.com.leadfar.cms.backend.dao;

import cn.com.leadfar.cms.backend.model.Article;
import junit.framework.TestCase;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Date;

/**
 * Created by twer on 12/4/15.
 */
public class BeanUtilsTest extends TestCase {
    public void testBeanUtils01() throws Exception{
        Article a = new Article();
        BeanUtils.copyProperty(a,"title","这是文章标题");
        System.out.println(a.getTitle());
    }
    public void testBeanUtils02() throws Exception{
        Article a = new Article();
        ConvertUtils.register(new DateConverter(), Date.class);
        BeanUtils.copyProperty(a,"createtime","2014-12-7");
        System.out.println(a.getTitle());
    }
}
