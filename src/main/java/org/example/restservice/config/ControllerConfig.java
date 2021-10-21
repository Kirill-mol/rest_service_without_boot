package org.example.restservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Kirill Mololkin Kirill-mol 21.10.2021
 */
public class ControllerConfig implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		var root = new XmlWebApplicationContext();

		root.setServletContext(servletContext);


		root.setServletContext(servletContext);
		root.refresh();


		servletContext.addListener(new ContextLoaderListener(root));

		DispatcherServlet dv =
				new DispatcherServlet(new GenericWebApplicationContext());

		ServletRegistration.Dynamic appServlet = servletContext.addServlet("test-mvc", dv);
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/*");
	}
}
