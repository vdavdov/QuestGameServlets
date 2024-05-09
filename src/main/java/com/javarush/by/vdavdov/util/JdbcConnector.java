package com.javarush.by.vdavdov.util;

import com.javarush.by.vdavdov.model.User;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.sql.*;
import java.time.LocalDate;
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
        session.beginTransaction();
        MutationQuery mutationQuery = session.createMutationQuery("update User set level = level + 1");
        mutationQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
