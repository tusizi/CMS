package cn.com.leadfar.cms.utils;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by tusizi on 2015/11/13.
 */
public class RequestUtil {
    /**
     * 将request获得的参数放到entityClass类型的对象中去
     * @param entityClass
     * @param request
     *
     * @return
     */
 public static Object copyParam(Class entityClass ,HttpServletRequest request){
     try {
         Object entity = entityClass.newInstance();
         //取出request中的参数
         Map allParams = request.getParameterMap();
         Set entries = allParams.entrySet();
         for (Iterator iterator = entries.iterator();iterator.hasNext();){
             Map.Entry entry = (Map.Entry)iterator.next();
             String name = (String) entry.getKey();
             Object[] value = (Object[]) entry.getValue();
             if (value!=null){
                 if (value.length==1){
                     BeanUtils.copyProperty(entity,name,value[0]);
                 }else {
                     BeanUtils.copyProperty(entity,name,value);
                 }
             }
         }
         return entity;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
}
