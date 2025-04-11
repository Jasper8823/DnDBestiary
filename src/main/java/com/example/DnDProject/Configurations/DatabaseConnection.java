package com.example.DnDProject.Configurations;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import com.example.DnDProject.Exceptions.ConfigurationExceptions.DatabaseConnectionException;
import com.example.DnDProject.Exceptions.ConfigurationExceptions.TransactionException;

@Component
@Transactional
public class DatabaseConnection {

    private final SessionFactory sessionFactory;

    public DatabaseConnection() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();

        // Check if sessionFactory is null
        if (this.sessionFactory == null) {
            throw new DatabaseConnectionException("SessionFactory could not be created");
        }
    }

    public void connect() {
        if (sessionFactory == null) {
            throw new DatabaseConnectionException("SessionFactory is not initialized");
        }

        Session session = null;
        Transaction transaction = null;

        try {
            // Open a session and perform a database operation
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback(); // Rollback in case of errors
                } catch (Exception rollbackException) {
                    throw new TransactionException("Failed to rollback transaction", rollbackException);
                }
            }
            throw new TransactionException("An error occurred during database operations", e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception closeException) {
                    throw new DatabaseConnectionException("Failed to close session", closeException);
                }
            }
        }
    }
}
