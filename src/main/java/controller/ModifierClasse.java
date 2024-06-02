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
import business.Classe;

@WebServlet("/controller/ModifierClasse")
public class ModifierClasse extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	   

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        HttpSession session = request.getSession();
	        if(session.getAttribute("username")!=null){
	        	int id = Integer.parseInt(request.getParameter("id"));
	        	String nom = request.getParameter("name");
	            String filliere = request.getParameter("filliere");
	            String annee = request.getParameter("grade");
	            
	            Classe classe = new Classe(nom,filliere,annee);
	            try {
	            	 MaConnexion connexion= new MaConnexion();
	                 AdminDAO dao = new AdminDAO(connexion);
	                 dao.modifierClasse(classe,id);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	            response.sendRedirect("./admin/classe-liste.jsp");
	 
	        }else response.sendRedirect("../Login.jsp");
	        }
}
