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
        String specialite = request.getParameter("specialite"); // Get the specialty if available

        Connection conn = null;
        PreparedStatement psUser = null, psPatient = null, psDoctor = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "admin");

            // Insert into 'user' table
            String userQuery = "INSERT INTO user (nom, email, mdps, telephone) VALUES (?, ?, ?, ?)";
            psUser = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            psUser.setString(1, nom);
            psUser.setString(2, email);
            psUser.setString(3, mdps);
            psUser.setString(4, telephone);

            int affectedRows = psUser.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = psUser.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    // Insert into 'patient' or 'doctor' table based on user role
                    if (specialite != null && !specialite.isEmpty()) {
                        // If specialty is provided, treat this user as a doctor
                        String doctorQuery = "INSERT INTO doctor (id_user, specialite) VALUES (?, ?)";
                        psDoctor = conn.prepareStatement(doctorQuery);
                        psDoctor.setInt(1, userId);
                        psDoctor.setString(2, specialite);
                        psDoctor.executeUpdate();
                    } else {
                        // Otherwise, insert into 'patient' table
                        String patientQuery = "INSERT INTO patient (id_user, nom, email, mdps, telephone) VALUES (?, ?, ?, ?, ?)";
                        psPatient = conn.prepareStatement(patientQuery);
                        psPatient.setInt(1, userId);
                        psPatient.setString(2, nom);
                        psPatient.setString(3, email);
                        psPatient.setString(4, mdps);
                        psPatient.setString(5, telephone);
                        psPatient.executeUpdate();
                    }

                    response.sendRedirect("success.jsp"); // Redirect to success page after registration
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page if something goes wrong
        } finally {
            try {
                if (psUser != null) psUser.close();
                if (psPatient != null) psPatient.close();
                if (psDoctor != null) psDoctor.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
