package com.microsoft.azure.appservice.examples.tomcatmysql;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        Map<String, String> props = new HashMap<String, String>();

        //String azureDbUrl= "jdbc:mysql://cephalin-tomcat-server.mysql.database.azure.com:3306/cephalin-tomcat-database?serverTimezone=UTC&sslmode=required&user=claftxejjs&password=83YF76TKMVO47321$";
        String azureDbUrl= System.getenv("AZURE_MYSQL_CONNECTIONSTRING");
        if (azureDbUrl!=null) 
            props.put("jakarta.persistence.nonJtaDataSource", "jdbc/AZURE_MYSQL_CONNECTIONSTRING_DS");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultpu", props);
        ctx.setAttribute("EMFactory", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        EntityManagerFactory factory = (EntityManagerFactory) ctx.getAttribute("EMFactory");
        factory.close();
    }
}
