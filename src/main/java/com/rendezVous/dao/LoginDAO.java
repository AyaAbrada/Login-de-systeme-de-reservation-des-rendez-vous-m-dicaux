package com.rendezVous.dao;
import com.rendezVous.dao.*;
import java.sql.*;

public class LoginDAO {

    // Vérifie si l'utilisateur existe dans la base de données
    public boolean checkLogin(String email, String mdps) {
        String query = "SELECT * FROM user WHERE email = ? AND mdps = ?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, mdps);
            ResultSet rs = ps.executeQuery();

            // Si un utilisateur est trouvé avec ces informations de connexion, renvoyer vrai
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'erreur
        }
    }

    // Récupérer l'id de l'utilisateur par son email (utile pour la gestion de session)
    public int getUserId(String email) {
        String query = "SELECT id FROM user WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retourne -1 si aucun utilisateur n'est trouvé
    }
}
