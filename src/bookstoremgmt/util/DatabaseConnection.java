package bookstoremgmt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BookManager;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "BookManager";
    private static final String PASSWORD = "ADMIN@12345";
    
    private static Connection connection = null;

    /**
     * Retrieves the active database connection or creates a new one if closed.
     *
     * @return Connection object to interact with the database.
     */
    public static Connection getConnection() {
        // Use try-catch to strictly handle connection errors
        try {
            // Check if connection is null or closed before creating a new one
            if (connection == null || connection.isClosed()) {
                // Register JDBC driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // Establish connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }
}
