package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.utils.BeanFactory;
import cn.com.leadfar.cms.utils.ChannelsSetConverter;
import cn.com.leadfar.cms.utils.PropertiesBeanFactory;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Set;

/**
 * Created by tusizi on 2015/11/17.
 */
public class InitBeanFactoryServlet extends HttpServlet {
    public static final String INIT_FACTORY_NAME ="_my_bean_factory";
    @Override
    public void init(ServletConfig config) throws ServletException {
        BeanFactory factory = null;
        String configLocation = config.getInitParameter("configLocation");
        if(configLocation ==null){
            factory = new PropertiesBeanFactory();
        }else{
            factory = new PropertiesBeanFactory(configLocation);
        }
        config.getServletContext().setAttribute(INIT_FACTORY_NAME,factory);
        //初始化BeanUtils的转换器
        ConvertUtils.register(new ChannelsSetConverter(), Set.class);
    }
}
