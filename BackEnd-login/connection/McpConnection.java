package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class McpConnection
{
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\copyMCP\\src\\main\\java\\org\\example\\mcp1.db";

    public static Connection getconnection() {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
