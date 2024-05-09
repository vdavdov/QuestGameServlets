package com.javarush.by.vdavdov.util;

import com.javarush.by.vdavdov.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    private static SessionFactory sessionFactory;

    public UserDao() {
        sessionFactory = MySessionFactory.getSessionFactory();
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.getResultList();
        }
    }

    public User getUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
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
