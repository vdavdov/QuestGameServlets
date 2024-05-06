package com.javarush.by.vdavdov.util;

import com.javarush.by.vdavdov.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    private SessionFactory sessionFactory;

    public void init() {
        this.sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    public User getUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    public User createUser(User user) {
        try (EntityManager entityManager = sessionFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
            return user;
        }
    }

    public User updateUser(User user) {
        try (EntityManager entityManager = sessionFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
            return user;
        }
    }

    public void deleteUser(User user) {
        try (EntityManager entityManager = sessionFactory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(user);
            transaction.commit();
        }
    }
}
