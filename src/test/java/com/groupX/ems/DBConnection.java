package com.groupX.ems;

import com.groupX.ems.config.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DBConnection {

    @Test
    void canOpenConnection() throws Exception {
        DatabaseConnection.open();
        Connection conn = DatabaseConnection.getInstance();
        assertFalse(conn == null || conn.isClosed(), "Connection should be open");
        DatabaseConnection.close();
    }
}