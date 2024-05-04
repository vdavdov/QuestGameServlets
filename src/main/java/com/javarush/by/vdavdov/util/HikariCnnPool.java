package com.javarush.by.vdavdov.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCnnPool {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds = new HikariDataSource(config);

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/quest_game");
        config.setUsername("postgres");
        config.setPassword("postgres");
        ds = new HikariDataSource(config);
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    private HikariCnnPool() {}
}
