package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ArticleDao;
import cn.com.leadfar.cms.backend.model.Article;

import cn.com.leadfar.cms.utils.BeanFactory;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tusizi on 2015/11/3.
 */
@WebServlet(name = "AddArticleServlet")
public class AddArticleServlet extends BaseServlet {
    private ArticleDao articleDao;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //从request中获取参数
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
//        BeanFactory factory = (BeanFactory) getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
//        ArticleDao articleDao= (ArticleDao) factory.getBean("articleDao");
        Article a =new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setSource(source);
        articleDao.addArticle(a);
            request.getRequestDispatcher("/backend/article/add_article_success.jsp").forward(request,response);
    }
//本set方法，定义了一个articleDao这样的一个property
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
