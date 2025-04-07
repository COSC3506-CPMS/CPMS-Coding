package com.cpms.cpms.config;

import com.cpms.cpms.entities.Project;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Update to include the Project class mapping
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // This loads the hibernate configuration
                    .addAnnotatedClass(Project.class) // Add the Project entity class here
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close the factory to release resources
        getSessionFactory().close();
    }
}
