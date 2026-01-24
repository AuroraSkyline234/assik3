package utils;

import java.sql.*;
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/gameinventory";
    private static final String USER = "postgres";
    private static final String PAS = "1234";
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PAS);
        } catch (Exception e){
            System.out.print(e.getMessage());
            return null;

        }
    }
}
