package com.cpms.cpms.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class HibernateUtilTest {

    @Test
    public void testSessionFactoryNotNull() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Assert.assertNotNull("SessionFactory should not be null", factory);
    }

    @Test
    public void testOpenSession() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Assert.assertTrue("Session should be open", session.isOpen());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @AfterClass
    public static void tearDown() {
        HibernateUtil.shutdown();
    }
}
