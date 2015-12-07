package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.backend.dao.ChannelDao;
import cn.com.leadfar.cms.backend.model.Channel;
import cn.com.leadfar.cms.backend.vo.PageVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChannelServlet extends BaseServlet {

    private ChannelDao channelDao;

    // �����������ִ�в�ѯ����
    @Override
    protected void execute(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        int offset = 0;
        int pagesize = 5;

        // ϣ���request�л��offset����
        try {
            offset = Integer.parseInt(request.getParameter("pager.offset"));
        } catch (Exception ignore) {
        }

        // ����request���ݹ�����pagesize������ô����Ҫ����http session�е�pagesize��ֵ
        if (request.getParameter("pagesize") != null) {
            request.getSession().setAttribute("pagesize",
                    Integer.parseInt(request.getParameter("pagesize")));
        }

        // ϣ���http session�л��pagesize��ֵ�����û�У�������ȱʡֵΪ5
        Integer ps = (Integer) request.getSession().getAttribute("pagesize");
        if (ps == null) {
            request.getSession().setAttribute("pagesize", pagesize);
        } else {
            pagesize = ps;
        }

        PageVO pv = channelDao.findChannels(offset, pagesize);

        request.setAttribute("pv", pv);

        //forward��channel_list.jsp
        request.getRequestDispatcher("/backend/channel/channel_list.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //��request�л�ȡ����
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Channel c = new Channel();
        c.setName(name);
        c.setDescription(description);
        channelDao.addChannel(c);

        //forward���ɹ�ҳ��
        request.getRequestDispatcher("/backend/channel/add_channel_success.jsp").forward(request, response);
    }

    //�򿪸��½���
    public void updateInput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //���մӽ��洫�ݹ�����ID
        String id = request.getParameter("id");
        Channel c = channelDao.findChannelById(Integer.parseInt(id));
        request.setAttribute("channel", c);
        //forward������ҳ��
        request.getRequestDispatcher("/backend/channel/update_channel.jsp").forward(request, response);

    }

    //ִ�и��²���
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //���ȣ��ӽ������Ƶ���Ļ���Ϣ��������ID����ơ�������
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Channel c = new Channel();
        c.setId(Integer.parseInt(id));
        c.setName(name);
        c.setDescription(description);
        channelDao.updateChannel(c);

        //forward�����³ɹ���ҳ��
        request.getRequestDispatcher("/backend/channel/update_channel_success.jsp").forward(request, response);

    }

    //ִ��ɾ�����
    public void del(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //�ӽ������ID����
        String[] ids = request.getParameterValues("id");

        if (ids == null) {
            //��ʾ����(forward������ҳ��)
            request.setAttribute("error", "�޷�ɾ��Ƶ����ID������Ϊ��");
            request.getRequestDispatcher("/backend/common/error.jsp").forward(request, response);
            return;
        }

        channelDao.delChannels(ids);

        //ת���б�ҳ��
        response.sendRedirect(request.getContextPath() + "/backend/ChannelServlet");
        //request.getRequestDispatcher("/backend/ChannelServlet").forward(request, response);
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}
