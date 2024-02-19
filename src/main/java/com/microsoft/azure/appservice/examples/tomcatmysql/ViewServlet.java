package com.microsoft.azure.appservice.examples.tomcatmysql;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.azure.appservice.examples.tomcatmysql.models.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = "/")
public class ViewServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(ViewServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("GET /");

        EntityManagerFactory emf = (EntityManagerFactory) req.getServletContext().getAttribute("EMFactory");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            List<Student> students = em.createQuery("SELECT a FROM Student a", Student.class).getResultList();
            transaction.commit();
            req.setAttribute("studentRecords", students);
        } catch (Exception e) {
            if(transaction != null && transaction.isActive())
                transaction.rollback();
            throw e;
        } finally {
            em.close();
        }

        req.getRequestDispatcher("/WEB-INF/views/viewStudents.jsp").forward(req, resp);
    }
}
