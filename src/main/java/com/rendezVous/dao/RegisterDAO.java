package com.rendezVous.dao;
import java.sql.*;

public class RegisterDAO {

    // Méthode pour inscrire un utilisateur et un patient dans la base de données
    public boolean registerUserAndPatient(String nom, String email, String mdps, String telephone) {
        Connection conn = null;
        PreparedStatement psUser = null, psPatient = null;
        boolean isRegistered = false;

        try {
            // Connexion à la base de données
            conn = DatabaseConnection.getConnection();

            // Insertion dans la table 'user'
            String userQuery = "INSERT INTO user (nom, email, mdps, telephone) VALUES (?, ?, ?, ?)";
            psUser = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, nom);
            psUser.setString(2, email);
            psUser.setString(3, mdps);
            psUser.setString(4, telephone);


            // Exécution de la requête d'insertion dans la table 'user'
            int affectedRows = psUser.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = psUser.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    // Insertion dans la table 'patient'
                    String patientQuery = "INSERT INTO patient (id_user, nom, email, mdps, telephone) VALUES (?, ?, ?, ?, ?)";
                    psPatient = conn.prepareStatement(patientQuery);
                    psPatient.setInt(1, userId);
                    psPatient.setString(2, nom);
                    psPatient.setString(3, email);
                    psPatient.setString(4, mdps);
                    psPatient.setString(5, telephone);

                    // Exécution de la requête d'insertion dans la table 'patient'
                    psPatient.executeUpdate();

                    // Si l'insertion a réussi, on marque l'inscription comme réussie
                    isRegistered = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psUser != null) psUser.close();
                if (psPatient != null) psPatient.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isRegistered;
    }
}
