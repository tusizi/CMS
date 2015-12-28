package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.model.Attachment;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tusizi on 2015/12/28.
 */
public class MultipartRequestWrapper extends HttpServletRequestWrapper {
    private Map allParams;
    public MultipartRequestWrapper(HttpServletRequest request) {
        super(request);
        //首先判断是否是Multipart编码类型
        //如果是multipart编码类型，就逐个从request中取出各个表单域
        //如果表单域是普通表单域，则将它的值取出放到allParams中
        //如果表单域是文件，则
            //1,把文件存储到磁盘的某个目录中
            //2.把文件的相关信息（名称，路径，类型）包装成Attachement[]类型
            //3.把包装好的Attachmentp[]类型放到allParams中
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if(!isMultipart){//不是isMultipart？？？
               allParams= request.getParameterMap();
            }else {
                allParams = new HashMap();
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    //得到表单域的名称
                    String name = item.getFieldName();
                    //得到表单域的值(这是一个输入流)
                    InputStream stream = item.openStream();
                    //如果是普通表单域
                    if (item.isFormField()) {
                        String value = Streams.asString(stream, request.getCharacterEncoding());
                        addFieldsAndValuesToMap(name,value);
                    } else {//如果是文件
                        if (stream.available() != 0) {//如果文件域没有选择文件，则忽略处理
                            String fileName = item.getName();//得到文件名称
                            if (fileName != null) {
                                //因为在IE下面，上传的路径还包括有此文件在客户端机器的路径
                                //所以要把文件路径去掉，只取文件名
                                fileName = FilenameUtils.getName(fileName);
                            }
                            //将上传文件的输入流输出到磁盘的文件上
                            Streams.copy(stream, new FileOutputStream(Attachment.ATTACHMENT_DIR+fileName), true);
                            Attachment attachment = new Attachment();
                            attachment.setContentType(item.getContentType());
                            attachment.setName(fileName);
                            attachment.setUploadTime(new Date());
                            addFieldsAndValuesToMap(name,attachment);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //????????????????????????????
    private void addFieldsAndValuesToMap(String name,String value){
        String[] fieldValues = (String[]) allParams.get(name);
        if (fieldValues==null){
            allParams.put(name,new String[] {value});
        }else{
            //比如原来数组是[1,2]
            //扩充后的数组是[1,2,null]
            fieldValues= Arrays.copyOf(fieldValues,fieldValues.length+1);
            fieldValues[fieldValues.length-1]=value;
            allParams.put(name,fieldValues);
        }
    }
    private void addFieldsAndValuesToMap(String name,Attachment value){
        Attachment[] fieldValues = (Attachment[]) allParams.get(name);
        if (fieldValues==null){
            allParams.put(name,new Attachment[] {value});
        }else{
            //比如原来数组是[1,2]
            //扩充后的数组是[1,2,null]
            fieldValues= Arrays.copyOf(fieldValues,fieldValues.length+1);
            fieldValues[fieldValues.length-1]=value;
            allParams.put(name,fieldValues);
        }
    }

    @Override
    public String getParameter(String name) {
        String[] value = (String[])allParams.get(name);
        if (value!=null){
            return value[0];
        }
        return null;
    }

    @Override
    public Map getParameterMap() {
        return allParams;
    }
}
