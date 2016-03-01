package cn.com.leadfar.cms.backend.service;

import cn.com.leadfar.cms.backend.model.Article;

import java.util.List;

/**
 * Created by tusizi on 2016/3/1.
 */
public abstract class Spider {
    //根据URL创建想用的Spider对象
    public static Spider getInstance(String url){
        //根据URL选择不同的子类

        return null;
    }
    //收集文章
    public List<Article> collect(String url){
        //创建HttpClient

        //设置代理

        //执行收集过程
        execute();
        //获取收集到的文章

        //返回文章列表
        return null;
    }
    public abstract void execute();
}
