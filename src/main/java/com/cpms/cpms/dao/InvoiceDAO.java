package com.cpms.cpms.dao;
import com.cpms.cpms.entities.Invoice;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
public class InvoiceDAO {
   public void addInvoice(Invoice invoice) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction transaction = session.beginTransaction();
           try {
               session.save(invoice);
               transaction.commit();
           } catch (Exception e) {
               transaction.rollback();
               throw e; // Rethrow exception after rollback for proper handling
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public Invoice getInvoice(int id) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.get(Invoice.class, id);
       } catch (Exception e) {
           e.printStackTrace();
           return null; // Return null if an error occurs
       }
   }
   public List<Invoice> getAllInvoices() {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           return session.createQuery("from Invoice", Invoice.class).list();
       } catch (Exception e) {
           e.printStackTrace();
           return null; // Return null if an error occurs
       }
   }
   public void updateInvoice(Invoice invoice) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction transaction = session.beginTransaction();
           try {
               session.update(invoice);
               transaction.commit();
           } catch (Exception e) {
               transaction.rollback();
               throw e; // Rethrow exception after rollback
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void deleteInvoice(int id) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction transaction = session.beginTransaction();
           try {
               Invoice invoice = session.get(Invoice.class, id);
               if (invoice != null) {
                   session.delete(invoice);
               }
               transaction.commit();
           } catch (Exception e) {
               transaction.rollback();
               throw e; // Rethrow exception after rollback
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
