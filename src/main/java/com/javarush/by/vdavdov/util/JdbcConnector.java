package com.javarush.by.vdavdov.util;

import java.sql.*;

public class JdbcConnector {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/quest_game",
                "postgres",
                "postgres");) {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        } catch (SQLException e) {
        }
    }
}
