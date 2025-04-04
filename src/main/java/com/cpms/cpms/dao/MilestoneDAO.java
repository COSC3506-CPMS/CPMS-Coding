package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MilestoneDAO {
    
    // Adds a new milestone to the database
    public void addMilestone(Milestone milestone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(milestone);
        transaction.commit();
        session.close();
    }

    // Retrieves a milestone by its ID
    public Milestone getMilestone(int milestoneID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Milestone milestone = session.get(Milestone.class, milestoneID);
        session.close();
        return milestone;
    }

    // Updates an existing milestone in the database
    public void updateMilestone(Milestone milestone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(milestone);
        transaction.commit();
        session.close();
    }

    // Deletes a milestone by its ID
    public void deleteMilestone(int milestoneID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Milestone milestone = session.get(Milestone.class, milestoneID);
        if (milestone != null) {
            session.delete(milestone);
        }
        transaction.commit();
        session.close();
    }

    // Retrieves all milestones from the database
    public List<Milestone> getAllMilestones() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Milestone> milestones = session.createQuery("from Milestone", Milestone.class).list();
        session.close();
        return milestones;
    }
}
