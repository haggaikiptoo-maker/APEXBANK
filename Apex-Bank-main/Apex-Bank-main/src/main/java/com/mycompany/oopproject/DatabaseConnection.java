package com.mycompany.oopproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:apex_bank.db";

    // Establishes a fresh connection to the database file
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Automatically runs scripts to build tables when the application starts
    public static void initializeDatabase() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL,"
                + "full_name TEXT NOT NULL,"
                + "balance REAL DEFAULT 0.0"
                + ");";

        String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions ("
                + "transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL,"
                + "type TEXT NOT NULL,"
                + "amount REAL NOT NULL,"
                + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + "FOREIGN KEY(username) REFERENCES users(username)"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(createUsersTable);
            stmt.execute(createTransactionsTable);
            System.out.println("Database tables initialized successfully!");
            
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }
}
