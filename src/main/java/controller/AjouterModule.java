package controller;


import DAO.AdminDAO;
import DAO.MaConnexion;
import business.Module;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/controller/AjouterModule")
public class AjouterModule extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	   
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	            String name = request.getParameter("name");
	            int nbr_heures = Integer.parseInt(request.getParameter("nbr_heures"));
	            String[] classIds = request.getParameterValues("classes");
	            String[] profIds = request.getParameterValues("profs");
	            Module module = new Module(name ,nbr_heures);
	            try {
	            	 MaConnexion connexion= new MaConnexion();
	                 AdminDAO dao = new AdminDAO(connexion);
	                 dao.ajouterModule(module,classIds,profIds);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	    			e.printStackTrace();
	    		}
	            response.sendRedirect("./admin/module-liste.jsp");
	 
	        }else response.sendRedirect("../Login.jsp");
	        }
}