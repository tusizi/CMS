package cn.com.leadfar.cms.site;

import cn.com.leadfar.cms.backend.dao.MemberDao;
import cn.com.leadfar.cms.backend.model.Member;
import cn.com.leadfar.cms.backend.view.BaseServlet;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by tusizi on 2015/12/17.
 */
public class MemberLoginServlet extends BaseServlet {
    private MemberDao memberDao;
    private int width;
    private int height;
    private int number;//显示多少个字符
    private String codes;//显示的内容

    @Override//将servlet的配置信息放到init里,init初始化
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        width = Integer.parseInt(config.getInitParameter("width"));
        height = Integer.parseInt(config.getInitParameter("height"));
        number = Integer.parseInt(config.getInitParameter("number"));
        codes = config.getInitParameter("codes");
    }
    //生成验证码
    public void checkcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("checkcode");
        response.setContentType("image/jpeg");
        //创建一张图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = image.createGraphics();
        //填充一个白色矩形
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        //添加一个黑框
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        //每个字符占据的宽度
        int x = (width - 1) / number;
        int y = height - 4;
        //随机生成一个字符
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            String code = String.valueOf(codes.charAt(random.nextInt(codes.length())));
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            Font font = new Font("Arial", Font.PLAIN, random(height / 2, height));
            g.setFont(font);
            g.drawString(code, i * x + 1, y);
            sb.append(code);
        }
        //将验证码放到http session中
        request.getSession().setAttribute("codes", sb.toString());
        //随机生成一些点
        for (int i = 0; i < 50; i++) {
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
        }

        OutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.flush();
        out.close();
    }
    //从max到min随机生成一个数
    //生成5~15之间的数
    private int random(int max, int min) {
        int m = new Random().nextInt(999999) % (max - min);
        return m + min;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(request, response);
        System.out.println("member logins");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        //系统判断验证码是否正确
        //刚刚生成的验证码串
        String sessionCode = (String) request.getSession().getAttribute("codes");//得到的不是string类型吗
//        if (!sessionCode.equalsIgnoreCase(checkcode)) {
//            //网页重定向forward到login.jsp页面
//            //请求转发
//            request.setAttribute("error", "验证码错误");
//            request.getRequestDispatcher("/backend/common/login.jsp").forward(request, response);
//            return;
//        }

        Member member = memberDao.findMemberByNickname(nickname);

        //系统判断用户名是否存在
        if (member != null) {
            String pass = member.getPassword();
            //密码是否正确
            if (!password.equals(pass)) {
                request.setAttribute("error", "密码不正确");
                request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
                return;
            }
        } else {
            request.setAttribute("error", "用户不存在");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
            return;
        }
        //需要将用户的信息存放到http session中
        request.getSession().setAttribute("LOGIN_MEMBER", member);
        //判断通过，转到main.jsp页面
        //重定向
        String ref = request.getHeader("referer");
        response.sendRedirect(ref);
    }
    //退出系统
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清空http session 销毁session  结束回话
        request.getSession().invalidate();
        //返回login.jsp
        String ref = request.getHeader("referer");
        response.sendRedirect(ref);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao=memberDao;
    }
}
