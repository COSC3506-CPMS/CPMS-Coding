package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Task;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TaskDAO {

    // Adds a new task to the database
	public void addTask(Task task) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(task);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        throw new RuntimeException("Failed to add task to the database", e);
	    }
	}

    // Retrieves a task by its ID
    public Task getTask(int taskID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task task = session.get(Task.class, taskID);
        session.close();
        return task;
    }

    // Updates an existing task in the database
    public void updateTask(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(task);
        transaction.commit();
        session.close();
    }

    // Deletes a task by its ID if it exists
    public void deleteTask(int taskID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Task task = session.get(Task.class, taskID);
        if (task != null) {
            session.delete(task);
        }
        transaction.commit();
        session.close();
    }

    // Retrieves a list of all tasks from the database
    public List<Task> getAllTasks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("from Task", Task.class).list();
        session.close();
        return tasks;
    }
}
