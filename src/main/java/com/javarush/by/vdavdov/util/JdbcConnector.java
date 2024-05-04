package com.javarush.by.vdavdov.util;

import java.sql.*;

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
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/quest_game",
                "postgres",
                "postgres")) {

        } catch (SQLException e) {

        }
    }
}
