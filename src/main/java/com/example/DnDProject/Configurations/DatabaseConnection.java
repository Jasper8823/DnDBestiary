package com.example.DnDProject.Configurations;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DatabaseConnection {

    private final SessionFactory sessionFactory;

    public DatabaseConnection() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public void Connect() {
        // Open a session and perform a database operation
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
}
