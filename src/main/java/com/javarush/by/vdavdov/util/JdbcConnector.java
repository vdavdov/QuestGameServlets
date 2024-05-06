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
        User bot = userDao.createUser(new User("Bot", 4, LocalDate.now()));
        System.out.println(bot);
//        userDao.deleteUser(new User(6, "Bot", 4, LocalDate.now()));
//        User anna = userDao.updateUser(new User(8, "Anna", 4, LocalDate.now()));
//        System.out.println(anna);
    }
}
