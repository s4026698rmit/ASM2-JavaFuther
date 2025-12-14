package com.groupX.ems.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public final class DatabaseConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final String JDBC_URL ="jdbc:postgresql://aws-1-ap-northeast-2.pooler.supabase.com:6543/postgres?user=postgres.pptphcevfbsotztxlzxz&password=C4ngOUqkJkMRHrwY";

    private static Connection connection;

    private DatabaseConnection() {
    }

    public static synchronized void open() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(JDBC_URL);
                LOGGER.info("Database connection opened.");
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to open database connection", e);
        }
    }

    public static synchronized Connection getInstance() {
        try {
            if (connection == null || connection.isClosed()) {
                open();
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to verify database connection", e);
        }
        return connection;
    }

    public static synchronized void close() {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.info("Database connection closed.");
            } catch (SQLException e) {
                LOGGER.error("Failed to close database connection", e);
            }
        }
    }

    public static void executeSQLFromFile(String resourcePath) {
        try (InputStream is = DatabaseConnection.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                LOGGER.warn("SQL resource not found: {}", resourcePath);
                return;
            }
            String sql = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.joining("\n"));
            if (sql.isBlank()) {
                LOGGER.info("SQL resource is empty: {}", resourcePath);
                return;
            }
            try (Statement stmt = getInstance().createStatement()) {
                stmt.execute(sql);
                LOGGER.info("Executed SQL from {}", resourcePath);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to execute SQL from file: {}", resourcePath, e);
        }
    }
}

