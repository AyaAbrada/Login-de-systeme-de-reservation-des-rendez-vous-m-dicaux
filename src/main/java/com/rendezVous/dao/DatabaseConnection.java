package com.rendezVous.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL de la base de données MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/login";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        try {
            // Enregistrer le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retourner la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Si le driver n'est pas trouvé, lever une exception SQL
            throw new SQLException("Database driver not found", e);
        }
    }
}

