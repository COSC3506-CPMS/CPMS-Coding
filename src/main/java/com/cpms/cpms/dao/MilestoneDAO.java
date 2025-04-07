package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MilestoneDAO {

    // Adds a new milestone to the database
    public void addMilestone(Milestone milestone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(milestone); // Save the milestone object
            transaction.commit();
            System.out.println("Milestone added successfully: " + milestone.getMilestoneName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if error occurs
            e.printStackTrace();
            throw new RuntimeException("Failed to add milestone to the database.", e);
        }
    }

    // Retrieves a milestone by its ID
    public Milestone getMilestone(int milestoneID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Milestone.class, milestoneID); // Retrieve the milestone
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve milestone with ID: " + milestoneID, e);
        }
    }

    // Updates an existing milestone in the database
    public void updateMilestone(Milestone milestone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(milestone); // Update the milestone object
            transaction.commit();
            System.out.println("Milestone updated successfully: " + milestone.getMilestoneName());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if error occurs
            e.printStackTrace();
            throw new RuntimeException("Failed to update milestone: " + milestone.getMilestoneName(), e);
        }
    }

    // Deletes a milestone by its ID
    public void deleteMilestone(int milestoneID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Milestone milestone = session.get(Milestone.class, milestoneID); // Retrieve the milestone
            if (milestone != null) {
                session.delete(milestone); // Delete the milestone if it exists
                System.out.println("Milestone deleted successfully: " + milestoneID);
            } else {
                System.out.println("Milestone with ID " + milestoneID + " does not exist.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if error occurs
            e.printStackTrace();
            throw new RuntimeException("Failed to delete milestone with ID: " + milestoneID, e);
        }
    }

    // Retrieves all milestones from the database
    public List<Milestone> getAllMilestones() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Milestone", Milestone.class).list(); // Fetch all milestones
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all milestones from the database.", e);
        }
    }
}