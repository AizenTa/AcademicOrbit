package controller;

import DAO.MaConnexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.GradebookDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            MaConnexion connexion = new MaConnexion();
            GradebookDAO gradebookDAO = new GradebookDAO(connexion);

            boolean isValid = false;

            switch (userType) {
                case "admin":
                    isValid = gradebookDAO.validateAdmin(username, password);
                    break;
                case "professor":
                    isValid = gradebookDAO.validateProfessor(username, password);
                    break;
                case "student":
                    isValid = gradebookDAO.validateStudent(username, password);
                    break;
            }

            if (isValid) {
                HttpSession session = request.getSession();
                switch (userType) {
                case "admin":
                    session.setAttribute("userType", userType);
                    session.setAttribute("username", username);
                    response.sendRedirect("./admin/admin.jsp");
                    break;
                case "professor":
                	session.setAttribute("userType", userType);
                    session.setAttribute("username", username);
                    response.sendRedirect("./prof/dashbordProf.jsp");
                    break;
                case "student":
                	session.setAttribute("userType", userType);
                    session.setAttribute("username", username);
                    response.sendRedirect("./etudiant/dashbord.jsp");
                    break;
                }
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}