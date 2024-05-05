package com.javarush.by.vdavdov.util;

import com.javarush.by.vdavdov.model.User;

import java.sql.*;
import java.time.LocalDate;

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
        UserDao userDao = new UserDao();
        userDao.init();
        User bot = userDao.createUser(new User(7, "Bot", 4, LocalDate.now().atStartOfDay()));
        System.out.println(bot);
    }
}
