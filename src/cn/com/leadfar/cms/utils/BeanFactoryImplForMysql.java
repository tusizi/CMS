package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.dao.impl.ArticleDaoImpl;

/**
 * Created by tusizi on 2015/11/13.
 */
public class BeanFactoryImplForMysql implements BeanFactory {
    @Override
    public ArticleDao getArticleDao() {
        return new ArticleDaoImpl();
    }
}
