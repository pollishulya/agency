package com.agency.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServletConfiguration implements WebApplicationInitializer {

    public void onStartup(ServletContext container) {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMvcConfig.class);
        context.register(DatabaseConfig.class);
        //context.register(SpringSecurityConfig.class);
        container.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}

//<web-app xmlns="http://java.sun.com/xml/ns/javaee" version="2.5">
//<display-name>Hello Spring MVC</display-name>
//
//<context-param>
//<param-name>contextConfigLocation</param-name>
//<param-value>/WEB-INF/appconfig-root.xml</param-value>
//</context-param>
//
//<servlet>
//<servlet-name>dispatcher</servlet-name>
//<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//<load-on-startup>1</load-on-startup>
//</servlet>
//
//<servlet-mapping>
//<servlet-name>dispatcher</servlet-name>
//<url-pattern>/</url-pattern>
//</servlet-mapping>
//
//<listener>
//<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
//</listener>
//
//<welcome-file-list>
//<welcome-file>startPage.jsp</welcome-file>
//</welcome-file-list>
//</web-app>