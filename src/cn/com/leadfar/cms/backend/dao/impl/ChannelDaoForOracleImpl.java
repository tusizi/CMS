package cn.com.leadfar.cms.backend.dao.impl;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;

/**
 * Created by tusizi on 2015/11/16.
 */
public class ChannelDaoForOracleImpl implements ChannelDao{
    public void addChannel(Channel channel) {
    }

    @Override
    public void delChannels(String[] ids) {

    }

    @Override
    public Channel findChannelById(int channelId) {
        return null;
    }

    @Override
    public void updateChannel(Channel c) {

    }

    @Override
    public PageVO findChannels(int offset, int pagesize) {
        return null;
    }
}
