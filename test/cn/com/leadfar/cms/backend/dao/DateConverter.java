package cn.com.leadfar.cms.backend.dao;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by twer on 12/4/15.
 */
//public class DateConverter implements Converter {
//    private SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-mm-dd");
//    @Override
//    public <T> T convert(Class<T> aClass, Object o) {
//        if (aClass!= Date.class){
//            return null;
//        }
//        try {
//            if (o instanceof String){
//                String v = (String) o;
//                return simpleDateFormat.parse(v);}
//        }catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
