package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContractorDAO {

    // Add a new contractor to the database
    public void addContractor(Contractor contractor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(contractor); // Save contractor object to the database
            transaction.commit(); // Commit the changes
            System.out.println("Contractor added successfully!");
        } catch (Exception e) {
            System.err.println("Error adding contractor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get a contractor by their ID
    public Contractor getContractor(int contractorID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Contractor contractor = session.get(Contractor.class, contractorID); // Fetch contractor by ID
            if (contractor == null) {
                System.out.println("Contractor with ID " + contractorID + " not found.");
            }
            return contractor;
        } catch (Exception e) {
            System.err.println("Error fetching contractor with ID " + contractorID + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing contractor
    public void updateContractor(Contractor contractor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(contractor); // Update contractor details
            transaction.commit(); // Commit changes
            System.out.println("Contractor updated successfully!");
        } catch (Exception e) {
            System.err.println("Error updating contractor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete a contractor by ID
    public void deleteContractor(int contractorID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Contractor contractor = session.get(Contractor.class, contractorID);
            if (contractor != null) {
                session.delete(contractor); // Delete contractor
                System.out.println("Contractor with ID " + contractorID + " deleted successfully!");
            } else {
                System.out.println("No contractor found with ID " + contractorID);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Error deleting contractor with ID " + contractorID + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve all contractors
    public List<Contractor> getAllContractors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Contractor", Contractor.class).list(); // HQL query for all contractors
        } catch (Exception e) {
            System.err.println("Error retrieving all contractors: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}