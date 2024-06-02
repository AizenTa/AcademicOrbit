package controller;


import DAO.MaConnexion;
import business.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.AdminDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/AjouterAdmin")
public class AjouterAdmin extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	   

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	        	String username = request.getParameter("username");
	            String password = request.getParameter("password");
	            String name = request.getParameter("nom");
	            String lastName = request.getParameter("prenom");
	          
	            Admin admin = new Admin(username, password, name, lastName);
	            try {
	            	 MaConnexion connexion= new MaConnexion();
	                 AdminDAO dao = new AdminDAO(connexion);
	                 dao.ajouterAdmin(admin);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	    			e.printStackTrace();
	    		}
	            response.sendRedirect("./admin/admin-liste.jsp");
	 
	        }else response.sendRedirect("../Login.jsp");
	        }
}
