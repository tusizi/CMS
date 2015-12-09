package cn.com.leadfar.cms.backend.dao.impl;

import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.leadfar.cms.backend.vo.PageVO;
import org.apache.ibatis.session.SqlSession;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.utils.MyBatisUtil;

public class ChannelDaoForMyBatis extends BaseDao implements ChannelDao {

    public void addChannel(Channel c) {
        add(c);
    }

    public void delChannels(String[] ids) {
       del(Channel.class,ids);
    }

    public Channel findChannelById(int channelId) {
       return (Channel)findById(Channel.class,channelId);
    }

    public PageVO findChannels() {
        Map params = new HashMap();
        return  findPaginated(Channel.class.getName() + ".findPaginated",params);
    }

    public void updateChannel(Channel c) {
       update(c);
    }

}
