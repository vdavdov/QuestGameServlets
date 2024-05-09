package com.javarush.by.vdavdov.util;

import com.javarush.by.vdavdov.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {
    private SessionFactory sessionFactory;
    private static MySessionFactory instance;

    private MySessionFactory() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new MySessionFactory();
        }
        return instance.sessionFactory;
    }
}
