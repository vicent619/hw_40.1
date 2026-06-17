package app;

import app.config.AppConfig;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        Context ctx = tomcat.addContext("", null);
        Tomcat.addServlet(ctx, "dispatcher", dispatcherServlet);
        ctx.addServletMappingDecoded("/*", "dispatcher");

        tomcat.start();
        tomcat.getServer().await();
    }
}
