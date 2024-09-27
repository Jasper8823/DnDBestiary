package com.example.DnDProject.Test;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DatabaseConnectionTest {

    private final SessionFactory sessionFactory;

    public DatabaseConnectionTest() {
        Configuration configuration = new Configuration().configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public void testConnection() {
        // Open a session and perform a database operation
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        /*// Example: Save a new Player entity to the database
        FakePlayer fakePlayer = new FakePlayer();
        fakePlayer.setName("Bents");
        fakePlayer.setLevel(13);

        session.persist(fakePlayer);

        // Commit the transaction and close the session*/
        transaction.commit();
        session.close();



        /*// Fetch and print the saved player
        session = sessionFactory.openSession();
        FakePlayer savedFakePlayer = session.get(FakePlayer.class, fakePlayer.getId());
        System.out.println("Retrieved Player: " + savedFakePlayer.getName() + ", Level: " + savedFakePlayer.getLevel());

        session.close();*/
    }
}
