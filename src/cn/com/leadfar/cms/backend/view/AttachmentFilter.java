package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.model.Attachment;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by tusizi on 2015/12/30.
 */
public class AttachmentFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //判断请求的URI是否包含“upload_image”
        //如果包含“upload_image”,则从G:/Java vedio/CM/upload中读取文件，并把文件的数据流写入到response中
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        String requestURI = request.getRequestURI();
        //因为路径中可能包含有经过编码的信息，所以需要将它转化成正确的字符串
        requestURI = URLDecoder.decode(requestURI,request.getCharacterEncoding());
        //indexOf返回值，-1表示不包含这个字符串
        //某个值就表示这个字符串起始字符的索引
        //requestURI可能是cms/backend/upload_image/login.gif
        int index = requestURI.indexOf("/upload_image/");
        if (index !=-1){
            String imageName = requestURI.substring(index + "/upload_image/".length());
            byte[] image = FileUtils.readFileToByteArray(new File(Attachment.ATTACHMENT_DIR+imageName));
            response.setContentType("image/jpeg");
            response.getOutputStream().write(image);
            return;
        }
        index = requestURI.indexOf("upload_file");
        if (index !=-1){
            String fileName = requestURI.substring(index + "/upload_file/".length());
            byte[] file = FileUtils.readFileToByteArray(new File(Attachment.ATTACHMENT_DIR+fileName));
            response.setContentType("application/x-msdownload");
            response.getOutputStream().write(file);
            return;
        }

        //让其他的请求通行
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
