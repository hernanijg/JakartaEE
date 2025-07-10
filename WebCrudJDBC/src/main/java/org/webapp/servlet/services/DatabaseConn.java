package org.webapp.servlet.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
    private static String url = "jdbc:mysql://localhost:3306/java_course?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "caca";
    private static Connection conn;

    public static Connection getConn() throws SQLException {
        if(conn == null)
            conn = DriverManager.getConnection(url, username, password);

        return conn;
    }
}
