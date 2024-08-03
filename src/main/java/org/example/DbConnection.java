package org.example;

import java.sql.*;

public class DbConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/test";
    private static final String user = "root";
    private static final String password = "root";


    public static Connection getdbConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
