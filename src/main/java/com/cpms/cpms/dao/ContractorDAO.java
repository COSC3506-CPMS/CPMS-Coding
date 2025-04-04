package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ContractorDAO {
    public void addContractor(Contractor contractor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(contractor);
        transaction.commit();
        session.close();
    }

    public Contractor getContractor(int contractorID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Contractor contractor = session.get(Contractor.class, contractorID);
        session.close();
        return contractor;
    }

    public void updateContractor(Contractor contractor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(contractor);
        transaction.commit();
        session.close();
    }

    public void deleteContractor(int contractorID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Contractor contractor = session.get(Contractor.class, contractorID);
        if (contractor != null) {
            session.delete(contractor);
        }
        transaction.commit();
        session.close();
    }

    public List<Contractor> getAllContractors() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Contractor> contractors = session.createQuery("from Contractor", Contractor.class).list();
        session.close();
        return contractors;
    }
}

