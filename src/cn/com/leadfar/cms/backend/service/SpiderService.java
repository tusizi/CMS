package cn.com.leadfar.cms.backend.service;

import cn.com.leadfar.cms.backend.model.Article;

import java.util.List;

/**
 * Created by tusizi on 2016/3/1.
 */
public interface SpiderService {
    public List<Article> collect(String url,String[]channelIds);
}
