package cn.com.leadfar.cms.backend.view;

import cn.com.leadfar.cms.SystemContext;
import cn.com.leadfar.cms.utils.BeanFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tusizi on 2015/11/17.
 */

public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BeanFactory factory = (BeanFactory) getServletContext().getAttribute(InitBeanFactoryServlet.INIT_FACTORY_NAME);
		System.out.println("调用了BaseServlet中的service方法");
		//this是多态的，this是具体的子类对象
		//利用反射机制调用this对象中的相关的setters方法
		Method[] methods = this.getClass().getMethods();
		for (Method m:methods){
			if(m.getName().startsWith("set")){
				//System.out.println(m.getName());
				//ArticleDao
				String propertyName = m.getName().substring(3);
				StringBuffer sb = new StringBuffer(propertyName);
				sb.replace(0,1,(propertyName.charAt(0)+"").toLowerCase());
				//articleDao
				propertyName = sb.toString();
				//约定：setters方法所决定的属性（propertyName）名,要与配置文件中相应的对象名一致
				Object bean = factory.getBean(propertyName);
				try {
					//将依赖注入客户端
					m.invoke(this,bean);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			//取出offset,pagesize,设置到TheadLocal中
			SystemContext.setOffset(getOffset(req));
			SystemContext.setPagesize(getPagesize(req));
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart){//如果是上传文件
				req = new MultipartRequestWrapper(req);
			}
			//执行父类的职责，根据请求时get还是post，决定调用doGet还是doPost方法
			super.service(req, resp);
		}finally {
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//首先获取方法参数
		String method = request.getParameter("method");
		//如果客户端不传递method参数，默认调用execute方法
		if (method ==null || method.trim().equals("")){
			execute(request,response);
			System.out.println("execute");
		}else {
			//根据method的取值调用相关的方法
			try {
				Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
				//将请求相应的转发
				m.invoke(this,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//什么也不做
	}
	public int getOffset(HttpServletRequest request){
		int offset = 0;
		//希望从request中获取pager.offset
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}
		return offset;
	}
	public int getPagesize(HttpServletRequest request){
		int pagesize = 5;
		//如果从request中传递过来pagesize,那么就需要更新http session中的pagesize
		if (request.getParameter("pagesize") != null) {
			request.getSession().setAttribute("pagesize",
					Integer.parseInt(request.getParameter("pagesize"))
			);
		}
		//希望从http session中获取pagesize
		Integer ps = (Integer) request.getSession().getAttribute("pagesize");
		if (ps == null) {
			pagesize = 5;
			request.getSession().setAttribute("pagesize", pagesize);
		} else {
			pagesize = ps;
		}
		return pagesize;
	}

}

