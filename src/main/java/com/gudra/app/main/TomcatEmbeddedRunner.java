package com.gudra.app.main;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Ashritha on 11/24/2016.
 */
public class TomcatEmbeddedRunner {

        public static void main(String [] args) throws ServletException, LifecycleException, MalformedURLException {
            startServer();
        }
        public static void startServer() throws LifecycleException, ServletException, MalformedURLException {
            Tomcat tomcat = new Tomcat();
            tomcat.setHostname("127.0.0.1");
            tomcat.setPort(8080);
            String webappPath = TomcatEmbeddedRunner.class.getResource("/webapp").getPath();
            Context rootContext = tomcat.addWebapp("/dailywages-employee-db", webappPath);
            rootContext.setConfigFile(new File(webappPath+"/WEB-INF/web.xml").toURI().toURL());
            tomcat.start();
            tomcat.getServer().await();
        }
}
