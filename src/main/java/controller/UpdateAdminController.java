package controller;

import DAO.AdminDAO;
import DAO.MaConnexion;
import business.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/UpdateAdminController")
public class UpdateAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String currentUsername = session.getAttribute("username").toString();

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String newUsername = request.getParameter("username");
        String newPassword = request.getParameter("password");
        String oldPassword = request.getParameter("oldPassword");

        try {
            MaConnexion connexion = new MaConnexion();
            AdminDAO adminDAO = new AdminDAO(connexion);

            Admin admin = adminDAO.getAdminByUsername(currentUsername);

            // Vérifier l'ancien mot de passe
            if (admin.getPassword().equals(adminDAO.hashString(oldPassword))) {
                admin.setNom(nom);
                admin.setPrenom(prenom);
                admin.setUsername(newUsername);
                admin.setPassword(newPassword);  // Assurez-vous de hacher le nouveau mot de passe avant de le stocker

                adminDAO.updateAdmin(admin);

                session.setAttribute("username", newUsername);
                response.sendRedirect("./admin/admin.jsp");
            } else {
                response.sendRedirect("./admin/admin.jsp?message=Mot de passe incorrect");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}