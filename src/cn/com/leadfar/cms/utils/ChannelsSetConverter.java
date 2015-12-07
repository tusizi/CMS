package cn.com.leadfar.cms.utils;

import cn.com.leadfar.cms.backend.model.Channel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by tusizi on 2015/11/13.
 */
public class ChannelsSetConverter implements Converter {

    @Override
    public Object convert(Class aClass, Object value) {
        String[] channelIds = null;

        if(value instanceof String){
            channelIds = new String[]{(String)value};
        }
        if(value instanceof String[]){
            channelIds = (String[])value;
        }

        if(channelIds != null){
            Set channels = new HashSet();
            for(String channelId:channelIds){
                Channel c = new Channel();
                c.setId(Integer.parseInt(channelId));
                channels.add(c);
            }
            return channels;
        }
        return null;
    }
}
