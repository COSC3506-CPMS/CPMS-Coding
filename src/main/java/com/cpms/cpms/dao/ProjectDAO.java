package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Project;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectDAO {

    // Adds a new project to the database
    public void addProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(project); // Save the project to the database
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction in case of failure
            }
            e.printStackTrace(); // Log the exception
        }
    }

    // Retrieves a project by its ID
    public Project getProject(int projectID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Project.class, projectID); // Retrieve project using ID
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return null;
        }
    }

    // Updates an existing project in the database
    public void updateProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(project); // Update the project in the database
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction in case of failure
            }
            e.printStackTrace(); // Log the exception
        }
    }

    // Deletes a project by its ID if it exists
    public void deleteProject(int projectID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Project project = session.get(Project.class, projectID); // Retrieve the project
            if (project != null) {
                session.delete(project); // Delete the project
                transaction.commit(); // Commit the transaction
            } else {
                transaction.rollback(); // Roll back if project not found
                System.out.println("Project with ID " + projectID + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Ensure rollback on failure
            }
            e.printStackTrace(); // Log the exception
        }
    }

    // Retrieves a list of all projects from the database
    public List<Project> getAllProjects() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Project", Project.class).list(); // HQL query to get all projects
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return null;
        }
    }
}