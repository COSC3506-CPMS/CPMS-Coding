package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Project;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProjectDAO {

    // Adds a new project to the database
    public void addProject(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
        session.close();
    }

    // Retrieves a project by its ID
    public Project getProject(int projectID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project = session.get(Project.class, projectID);
        session.close();
        return project;
    }

    // Updates an existing project in the database
    public void updateProject(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
    }

    // Deletes a project by its ID if it exists
    public void deleteProject(int projectID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, projectID);
        if (project != null) {
            session.delete(project);
        }
        transaction.commit();
        session.close();
    }

    // Retrieves a list of all projects from the database
    public List<Project> getAllProjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Project> projects = session.createQuery("from Project", Project.class).list();
        session.close();
        return projects;
    }
}
