package cn.com.leadfar.cms.backend.model;

import java.util.Date;

/**
 * Created by tusizi on 2015/12/28.
 */
public class Attachment {
    public static  final String ATTACHMENT_DIR="G:/Java vedio/";
    private int id;
    //是哪篇文章的附件
    private int articleId;
    //文件名称（路径）
    private String name;
    //文件类型
    private String contentType;
    //文件上传时间
    private Date uploadTime;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
