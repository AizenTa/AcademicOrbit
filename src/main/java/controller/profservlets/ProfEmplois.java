package controller.profservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import DAO.AdminDAO;
import DAO.MaConnexion;

@WebServlet("/controller/profservlets/ProfEmplois")
public class ProfEmplois extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int profId = Integer.parseInt(request.getParameter("profId"));

        HttpSession session = request.getSession();

        if(session.getAttribute("username")!=null){
            try {
            	MaConnexion connexion= new MaConnexion();
                AdminDAO adminDAO = new AdminDAO(connexion);
             	String[][] timetable = adminDAO.showProfessorTimetable(profId);
                request.setAttribute("timetable", timetable);
                request.getRequestDispatcher("profEmplois.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		} 
        }else response.sendRedirect("../Login.jsp");
    }
}