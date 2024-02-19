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

    private static Logger logger = LogManager.getLogger(ContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.info("Initializing WebListener.");

        ServletContext ctx = sce.getServletContext();

        Map<String, String> props = new HashMap<String, String>();
        String azureDbUrl= System.getenv("AZURE_MYSQL_CONNECTIONSTRING");
        if (azureDbUrl!=null) 
            props.put("jakarta.persistence.nonJtaDataSource", "java:comp/env/jdbc/AZURE_MYSQL_CONNECTIONSTRING_DS");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultpu", props);
        ctx.setAttribute("EMFactory", emf);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        logger.info("Destroying WebListener.");

        ServletContext ctx = sce.getServletContext();
        EntityManagerFactory factory = (EntityManagerFactory) ctx.getAttribute("EMFactory");
        // logger.info("ctx.getAttribute done.");
        if(factory.isOpen()) {
            factory.close();
            logger.info("factory.close done.");
        }
    }
}
