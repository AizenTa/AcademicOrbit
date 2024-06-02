package controller;

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

@WebServlet("/controller/SupprimerAdmin")
public class SupprimerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("username")!=null){
        	String id = request.getParameter("id");
          
            try {
            	 MaConnexion connexion= new MaConnexion();
                 AdminDAO dao = new AdminDAO(connexion);
                 dao.supprimerAdmin(id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            response.sendRedirect("./admin/admin-liste.jsp");
 
        }else response.sendRedirect("../Login.jsp");
        }
	}