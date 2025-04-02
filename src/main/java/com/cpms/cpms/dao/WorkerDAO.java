package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class WorkerDAO {

    // Adds a new worker to the database.
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

    // Retrieves a worker by their ID.
    public Worker getWorker(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Worker.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null on exception
        }
    }

    // Retrieves all workers from the database.
    public List<Worker> getAllWorkers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Worker", Worker.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null on exception
        }
    }

    // Updates an existing worker in the database.
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

    // Deletes a worker by their ID.
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