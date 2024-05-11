package com.javarush.by.vdavdov.util;

import com.javarush.by.vdavdov.model.User;
import jakarta.persistence.NamedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class JdbcConnector {
    public static final String SQL_SELECT_BY_ID =
            """
                    SELECT *
                    FROM users
                    WHERE id = ?
                    """;

    public static final String UPDATE_BY_ID =
            """
                    UPDATE users
                    SET name=?
                    WHERE id=?
                    """;

    public static final String INSERT_INTO =
            """
                    INSERT INTO users
                    VALUES (?, ?)
                    """;
    public static void main(String[] args) throws SQLException {
        Session session = MySessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User where id = 1", User.class);
        User user = query.uniqueResult();
        user.setName("Gregory");
        System.out.println(session.isDirty());
        transaction.commit();
        session.close();
    }
}
