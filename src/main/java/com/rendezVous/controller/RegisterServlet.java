package com.rendezVous.controller;

import jakarta.servlet.http.HttpServlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String mdps = request.getParameter("mdps");
        String telephone = request.getParameter("telephone");
        String specialite = request.getParameter("specialite");
        String userType = request.getParameter("userType");  // "medecin" ou "patient"

        Connection conn = null;
        PreparedStatement psUser = null, psDetails = null;

        try {
            // Etablir la connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "password");

            // Insertion dans la table 'user'
            String userQuery = "INSERT INTO user (nom, email, mdps, telephone, specialite) VALUES (?, ?, ?, ?, ?)";
            psUser = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, nom);
            psUser.setString(2, email);
            psUser.setString(3, mdps);
            psUser.setString(4, telephone);
            psUser.setString(5, specialite);

            int affectedRows = psUser.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = psUser.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    // Insertion dans la table spécifique (medecin ou patient)
                    if (userType.equals("medecin")) {
                        String medecinQuery = "INSERT INTO medecin (id_user, nom, email, mdps, telephone, specialite) VALUES (?, ?, ?, ?, ?, ?)";
                        psDetails = conn.prepareStatement(medecinQuery);
                        psDetails.setInt(1, userId);
                        psDetails.setString(2, nom);
                        psDetails.setString(3, email);
                        psDetails.setString(4, mdps);
                        psDetails.setString(5, telephone);
                        psDetails.setString(6, specialite);
                    } else if (userType.equals("patient")) {
                        String patientQuery = "INSERT INTO patient (id_user, nom, email, mdps, telephone) VALUES (?, ?, ?, ?, ?)";
                        psDetails = conn.prepareStatement(patientQuery);
                        psDetails.setInt(1, userId);
                        psDetails.setString(2, nom);
                        psDetails.setString(3, email);
                        psDetails.setString(4, mdps);
                        psDetails.setString(5, telephone);
                    }
                    psDetails.executeUpdate();
                    response.sendRedirect("success.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            try {
                if (psUser != null) psUser.close();
                if (psDetails != null) psDetails.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

