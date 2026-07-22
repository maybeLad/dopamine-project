package it.dopamine.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MainContext implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        DataSource ds = null;

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/dopamine");

            context.setAttribute("DataSource", ds);

            Connector.setDataSource(ds);

        } catch (NamingException e) {
            System.out.println("Errore inizializzazione DataSource: " + e.getMessage());
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    	
    }
}