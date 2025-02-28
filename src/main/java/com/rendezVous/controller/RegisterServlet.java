package com.rendezVous.controller;
import javax.servlet.http.HttpServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String mdps = request.getParameter("mdps");
        String telephone = request.getParameter("telephone");
        Connection conn = null;
        PreparedStatement psUser = null, psPatient = null;

        try {
            // Etablir la connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "admin");

            // Insertion dans la table 'user' pour créer un utilisateur
            String userQuery = "INSERT INTO user (nom, email, mdps, telephone) VALUES (?, ?, ?, ?)";
            psUser = conn.prepareStatement(userQuery);
            psUser.setString(1, nom);
            psUser.setString(2, email);
            psUser.setString(3, mdps);
            psUser.setString(4, telephone);
            int affectedRows = psUser.executeUpdate();
            response.sendRedirect("success.jsp");

            if (affectedRows > 0) {
                // Récupérer l'ID de l'utilisateur nouvellement créé
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
                    psPatient.executeUpdate();

                    // Rediriger vers une page de succès après l'inscription du patient
                    response.sendRedirect("success.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Rediriger vers une page d'erreur en cas de problème
        }
    }

    // La méthode doGet est ajoutée ici pour afficher le formulaire d'inscription
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la page d'inscription (formulaire) où l'utilisateur pourra entrer ses informations
        // Par exemple, afficher la page "register.jsp" contenant le formulaire d'inscription.
        RequestDispatcher dispatcher = request.getRequestDispatcher("creer.jsp");
        dispatcher.forward(request, response);
    }
}
