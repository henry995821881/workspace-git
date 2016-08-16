package org.winter.fromwork;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class WinterContextListner implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		Object attribute = arg0.getServletContext().getAttribute("winterContainer");
		attribute =null;
		Field declaredField;
		try {
			declaredField = ApplicationBeanFactory.class.getDeclaredField("map");
			declaredField.setAccessible(true);
			declaredField.set(null, null);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ServletContext servletContext = arg0.getServletContext();
		
		
		
			Map map;
			try {
				map = new Winter().initContainer("app.xml");
				servletContext.setAttribute("winterContainer", map);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
