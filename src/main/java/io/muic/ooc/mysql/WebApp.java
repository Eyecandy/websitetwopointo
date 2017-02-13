package io.muic.ooc.mysql;

/**
 * Created by joakimnilfjord on 2/13/2017 AD.
 */

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class WebApp {
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost/ooc_test?"
            + "user=ooc&password=oocpass";
    public static void main(String args[]) throws Exception {
        MySQLJava dao = new MySQLJava(MYSQL_DRIVER, MYSQL_URL);
        dao.readData();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        String webappDirLocation = "src/main/webapp";

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}