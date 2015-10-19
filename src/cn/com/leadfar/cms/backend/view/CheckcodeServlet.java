package cn.com.leadfar.cms.backend.view;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by tusizi on 2015/10/16.
 */
@WebServlet(name = "CheckcodeServlet")
public class CheckcodeServlet extends HttpServlet {
    private int width;
    private  int height;
    private int number;//显示多少个字符
    private  String codes;//显示的内容

    @Override
    //将servlet的配置信息放到init里,init初始化
    public void init(ServletConfig config) throws ServletException {
        width = Integer.parseInt(config.getInitParameter("width"));
        height = Integer.parseInt(config.getInitParameter("height"));
        number = Integer.parseInt(config.getInitParameter("number"));
        codes = config.getInitParameter("codes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        //创建一张图片
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = image.createGraphics();
        //填充一个白色矩形
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        //添加一个黑框
        g.setColor(Color.black);
        g.drawRect(0,0,width-1,height-1);
        //每个字符占据的宽度
        int x =(width-1) /number;
        int y =height-4;
        //随机生成一个字符
        Random random = new Random();
        for(int i = 0; i<number; i++){
           String code = String.valueOf(codes.charAt(random.nextInt(codes.length())));
            int red =random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            g.setColor(new Color(red,green,blue));
            Font font = new Font("Arial",Font.PLAIN,random(height/2,height));
            g.setFont(font);
            g.drawString(code,i*x+1,y);
        }
        //随机生成一些点
        for(int i =0;i<50;i++){
            int red =random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(random.nextInt(width),random.nextInt(height),1,1);
        }

        OutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.flush();
        out.close();
        }


    //从max到min随机生成一个数
    //生成5~15之间的数
    private int random(int max,int min){
       int m =  new Random().nextInt(999999)%(max-min);
        return m + min;
    }

}
