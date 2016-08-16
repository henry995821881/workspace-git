package org.winter.fromwork;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class DispatchCommandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DefaultActionMapping mapping = (DefaultActionMapping) ApplicationBeanFactory.getBean("defaultActionMapping");

		String requestURI = request.getRequestURI();
		ServletContext context = request.getSession().getServletContext();

		String contextPath = context.getContextPath();

		// Action annotation = this.getClass().getAnnotation(Action.class);
		// System.out.println(annotation);
		Object[] actionAndMethod = mapping.getActionAndMethod(requestURI, contextPath);

		if (actionAndMethod == null) {
			System.out.println("actionAndMethod is null");
			return;
		}
		Object obj = actionAndMethod[0];
		Method method = (Method) actionAndMethod[1];
		Enumeration<String> parameterNames = request.getParameterNames();
		Map<String, Object> parameterMap = new HashMap<>();
		while (parameterNames.hasMoreElements()) {
			String paramterName = (String) parameterNames.nextElement();
			String[] values = request.getParameterValues(paramterName);
			if (values != null && values.length == 1) {
				parameterMap.put(paramterName, values[0]);
			} else {
				parameterMap.put(paramterName, values);
			}
		}

		Object jspUrl = null;
		try {
			jspUrl = method.invoke(obj, new Object[] { request, response, parameterMap });
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (jspUrl != null) {

			request.getRequestDispatcher(jspUrl.toString()).forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

	@Override
	public void destroy() {

		super.destroy();
	}

}
