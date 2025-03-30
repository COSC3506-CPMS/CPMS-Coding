package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class WorkerDAO {
    public void addWorker(Worker worker) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(worker);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Worker getWorker(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Worker.class, id);
        }
    }

    public List<Worker> getAllWorkers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Worker", Worker.class).list();
        }
    }

    public void updateWorker(Worker worker) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(worker);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteWorker(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Worker worker = session.get(Worker.class, id);
            if (worker != null) {
                session.delete(worker);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
